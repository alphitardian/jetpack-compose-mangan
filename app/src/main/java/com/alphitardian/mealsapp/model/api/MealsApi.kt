package com.alphitardian.mealsapp.model.api

import com.alphitardian.mealsapp.model.response.MealCategoriesResponse
import com.alphitardian.mealsapp.model.response.MealCategoryResponse
import com.alphitardian.mealsapp.model.response.MealDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    suspend fun getCategories(): MealCategoriesResponse    // suspend = async

    @GET("filter.php")
    suspend fun getCategoryMeals(@Query("c") name: String): MealCategoryResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") id : String) : MealDetailResponse
}