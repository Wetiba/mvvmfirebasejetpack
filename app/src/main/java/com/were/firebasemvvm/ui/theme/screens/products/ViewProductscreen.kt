package com.were.firebasemvvm.ui.theme.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.were.firebasemvvm.ui.theme.blue
import com.were.firebasemvvm.ui.theme.blue1


@Composable
fun ViewProductsScreen(navController: NavHostController) {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(blue, blue1)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        val productRepository = productviewmodel(navController, context)
        val emptyProductState = remember { mutableStateOf(Product("", "", "", "")) }
        val emptyProductsListState = remember { mutableStateListOf<Product>() }

        val products = productRepository.viewProducts(emptyProductState, emptyProductsListState)

        // Header Section
        Text(
            text = "All Products",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Products List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductItem(
                    name = product.name,
                    quantity = product.quantity,
                    price = product.price,
                    id = product.id,
                    navController = navController,
                    productRepository = productRepository
                )
            }
        }
    }
}

@Composable
fun ProductItem(
    name: String,
    quantity: String,
    price: String,
    id: String,
    navController: NavHostController,
    productRepository: productviewmodel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = newwhite)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Product Details
            Text(text = "Name: $name", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "Quantity: $quantity", fontSize = 14.sp)
            Text(text = "Price: $price", fontSize = 14.sp, color = green1)

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { productRepository.deleteProduct(id) },
                    colors = ButtonDefaults.buttonColors(containerColor = red),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Delete", color = Color.White)
                }

                Button(
                    onClick = { navController.navigate("$ROUTE_UPDATE_PRODUCT/$id") },
                    colors = ButtonDefaults.buttonColors(containerColor = green1),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Update", color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun ViewProductsPreview() {
    ViewProductsScreen(rememberNavController())
}