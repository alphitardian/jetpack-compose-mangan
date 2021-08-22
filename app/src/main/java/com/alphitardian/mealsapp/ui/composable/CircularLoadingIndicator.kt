package com.alphitardian.mealsapp.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularLoadingIndicator(isDisplay: Boolean) {
    if (isDisplay) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
}