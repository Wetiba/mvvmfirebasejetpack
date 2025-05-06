package com.were.firebasemvvm.ui.theme.screens.home
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.were.firebasemvvm.navigation.ROUTE_ADD_PRODUCT
import com.were.firebasemvvm.navigation.ROUTE_VIEW_PRODUCT
import com.were.firebasemvvm.navigation.ROUTE_VIEW_UPLOAD
import com.were.firebasemvvm.ui.theme.coral
import com.were.firebasemvvm.ui.theme.denimBlue
import com.were.firebasemvvm.ui.theme.emeraldGreen
import com.were.firebasemvvm.ui.theme.icebergBlue
import com.were.firebasemvvm.ui.theme.lightBlue
import com.were.firebasemvvm.ui.theme.sunsetOrange


@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(lightBlue, icebergBlue)
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Welcome Text
            Text(
                text = "Welcome to Home Page",
                color = coral,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Add Product Button
            ActionCard(
                title = "Add Product",
                description = "Add new products to your inventory.",
                backgroundColor = emeraldGreen,
                onClick = { navController.navigate(ROUTE_ADD_PRODUCT) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // View Product Button
            ActionCard(
                title = "View Product",
                description = "Browse and manage your products.",
                backgroundColor = denimBlue,
                onClick = { navController.navigate(ROUTE_VIEW_PRODUCT) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Upload Products Button
            ActionCard(
                title = "Upload Products",
                description = "Upload product details to the cloud.",
                backgroundColor = sunsetOrange,
                onClick = { navController.navigate(ROUTE_VIEW_UPLOAD) }
            )
        }
    }
}

@Composable
fun ActionCard(
    title: String,
    description: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onClick,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Go",
                    color = backgroundColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen(rememberNavController())
}