package com.alphitardian.mealsapp.ui.mealdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.alphitardian.mealsapp.ui.composable.CircularLoadingIndicator
import com.alphitardian.mealsapp.ui.composable.SimpleTopAppBar

@Composable
fun MealDetailScreen(id: String, navController: NavController?) {

    val viewModel: MealDetailViewModel = viewModel()
    viewModel.getMealDetail(id)
    val mealDetails = viewModel.mealDetails.value
    val loading = viewModel.loading.value

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(name = mealDetails?.name.orEmpty(), actionBackNavigation = {
                navController?.popBackStack()
            })
        }
    ) {
        if (loading) {
            CircularLoadingIndicator(isDisplay = loading)
        } else {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Image(
                    painter = rememberImagePainter(data = mealDetails?.imageUrl),
                    contentDescription = mealDetails?.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(text = mealDetails?.name.orEmpty(), style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = mealDetails?.area.orEmpty(), style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Instruction", fontWeight = FontWeight.Bold)
                    Text(text = mealDetails?.instructions.orEmpty())
                }
            }
        }
    }
}