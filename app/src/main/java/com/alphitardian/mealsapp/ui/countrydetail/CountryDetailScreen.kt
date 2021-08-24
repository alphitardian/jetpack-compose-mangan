package com.alphitardian.mealsapp.ui.countrydetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alphitardian.mealsapp.ui.composable.CircularLoadingIndicator
import com.alphitardian.mealsapp.ui.composable.MealCard
import com.alphitardian.mealsapp.ui.composable.SimpleTopAppBar

@ExperimentalFoundationApi
@Composable
fun CountryDetailScreen(country: String, navController: NavHostController?) {
    val viewModel: CountryDetailViewModel = viewModel()
    viewModel.getCountryMeals(country)
    val countryMeals = viewModel.countryMealsState.value
    val loading = viewModel.loading.value

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(name = country, actionBackNavigation = {
                navController?.popBackStack()
            })
        }
    ) {
        if (loading) {
            CircularLoadingIndicator(isDisplay = loading)
        } else {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(countryMeals) {
                    MealCard(meal = it, actionClick = {
                        navController?.navigate("meal/${it.id}")
                    }, actionLongClick = {})
                }
            }
        }
    }
}