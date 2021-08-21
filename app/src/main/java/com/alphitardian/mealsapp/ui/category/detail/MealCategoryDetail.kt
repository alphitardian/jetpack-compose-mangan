package com.alphitardian.mealsapp.ui.category.detail

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alphitardian.mealsapp.model.response.CategoryResponse

@Composable
fun MealCategoryDetailScreen(categoryName: String, navController: NavHostController?) {
    val viewModel : CategoryDetailViewModel = viewModel()
    viewModel.getCategory(categoryName)
    val mealCategory = viewModel.mealCategoryState.value

    LazyColumn {
        items(mealCategory) {
            Text(text = it.mealName)
        }
    }
}