package com.were.firebasemvvm.data


import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.were.firebasemvvm.models.User
import com.were.firebasemvvm.navigation.ROUTE_HOME
import com.were.firebasemvvm.navigation.ROUTE_LOGIN
import com.were.firebasemvvm.navigation.ROUTE_REGISTER


class AuthViewModel(var navController: NavHostController, var context: Context) {

    var mAuth: FirebaseAuth


    init {
        mAuth = FirebaseAuth.getInstance()

    }

    fun sign_up(email: String, pass: String, confpass: String) {
        if (email.isBlank() or pass.isBlank() or confpass.isBlank()) {
            Toast.makeText(
                context,
                "Email and Password are blank. Please Enter Details",
                Toast.LENGTH_LONG
            ).show()
            return
        } else if (pass != confpass) {
            Toast.makeText(context, "Password and Confirm Password are not same", Toast.LENGTH_LONG)
                .show()
            return

        } else {
            mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userdata = User.User(email, pass, mAuth.currentUser!!.uid)
                        val regRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users/" + mAuth.currentUser!!.uid)
                        regRef.setValue(userdata).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "${it.exception!!.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_LOGIN)
                            } else {
                                Toast.makeText(
                                    context,
                                    "${it.exception!!.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_LOGIN)
                            }
                        }
                    } else {
                        navController.navigate(ROUTE_REGISTER)
                    }
                }
        }
    }

    fun login(email: String, pass: String) {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Succeffully Logged in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_HOME)
            } else {
                Toast.makeText(context, "${it.exception!!.message}", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }
    }

    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
    }

    fun isloggedin():Boolean{
        return mAuth.currentUser !=null
    }
}