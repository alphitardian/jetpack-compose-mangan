package com.alphitardian.mealsapp.ui.mealdetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun MealDetailScreen(id : String, navController: NavController?) {

    val viewModel : MealDetailViewModel = viewModel()
    viewModel.getMealDetail(id)
    val mealDetails = viewModel.mealDetails.value

    Text(text = mealDetails?.name.orEmpty())
}