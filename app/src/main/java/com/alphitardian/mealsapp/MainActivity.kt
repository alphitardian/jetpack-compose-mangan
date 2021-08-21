package com.alphitardian.mealsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.alphitardian.mealsapp.model.response.CategoryResponse
import com.alphitardian.mealsapp.ui.category.MealCategoriesScreen
import com.alphitardian.mealsapp.ui.category.detail.MealCategoryDetailScreen
import com.alphitardian.mealsapp.ui.theme.MealsAppTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainApp()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category", builder = {
        composable(route = "category") {
            MealCategoriesScreen(navController = navController)
        }
        composable(
            route = "category/detail/{categoryName}",
            arguments = listOf(navArgument("categoryName") {
                type = NavType.StringType
            })
        ) {

            MealCategoryDetailScreen(
                categoryName = it.arguments!!.getString("categoryName").toString(),
                navController = navController
            )
        }
    })
}

