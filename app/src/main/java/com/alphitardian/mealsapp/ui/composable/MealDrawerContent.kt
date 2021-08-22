package com.alphitardian.mealsapp.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DrawerContent(navController: NavHostController?) {
    Column {
        Text(
            text = "Mangan.",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .clickable(onClick = {
                    navController?.navigate("category")
                })
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Category Icon",
                tint = Color.LightGray
            )
            Text(text = "Category", modifier = Modifier.padding(horizontal = 24.dp))
        }
        Row(
            modifier = Modifier
                .clickable {
                    navController?.navigate("country")
                }
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Country Icon",
                tint = Color.LightGray
            )
            Text(text = "Country", modifier = Modifier.padding(horizontal = 24.dp))
        }
    }
}