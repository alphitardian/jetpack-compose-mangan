package com.alphitardian.mealsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.alphitardian.mealsapp.ui.category.MealCategoriesScreen
import com.alphitardian.mealsapp.ui.categorydetail.MealCategoryDetailScreen
import com.alphitardian.mealsapp.ui.country.MealCountriesScreen
import com.alphitardian.mealsapp.ui.countrydetail.CountryDetailScreen
import com.alphitardian.mealsapp.ui.mealdetail.MealDetailScreen
import com.alphitardian.mealsapp.ui.search.MealsSearchScreen
import com.alphitardian.mealsapp.ui.theme.MealsAppTheme

@ExperimentalMaterialApi
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

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category", builder = {
        // Main
        composable(route = "category") {
            MealCategoriesScreen(navController = navController)
        }
        composable(route = "country") {
            MealCountriesScreen(navController = navController)
        }
        composable(route = "search") {
            MealsSearchScreen(navController = navController)
        }

        // Detail
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
        composable(
            route = "country/detail/{countryName}",
            arguments = listOf(navArgument("countryName") {
                type = NavType.StringType
            })
        ) {
            CountryDetailScreen(
                country = it.arguments!!.getString("countryName").toString(),
                navController = navController
            )
        }
        composable(
            route = "meal/{id}", arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            MealDetailScreen(
                id = it.arguments!!.getString("id").toString(),
                navController = navController
            )
        }
    })
}

