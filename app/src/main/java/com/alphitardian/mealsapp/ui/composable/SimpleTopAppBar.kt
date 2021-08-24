package com.alphitardian.mealsapp.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTopAppBar(
    name: String,
    actionBackNavigation: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back Icon",
                modifier = Modifier
                    .clickable(onClick = actionBackNavigation)
                    .padding(8.dp)
            )
        },
        backgroundColor = Color.White
    )
}