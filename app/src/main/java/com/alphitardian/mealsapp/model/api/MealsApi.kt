package com.alphitardian.mealsapp.model.api

import com.alphitardian.mealsapp.model.response.*
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    suspend fun getCategories(): MealCategoriesResponse    // suspend = async

    @GET("filter.php")
    suspend fun getCategoryMeals(@Query("c") name: String): MealCategoryResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") id : String) : MealDetailResponse

    @GET("list.php?a=list")
    suspend fun getCountries() : CountriesResponse

    @GET("filter.php")
    suspend fun getCountryMeals(@Query("a") country : String) : CountryMealsResponse

    @GET("search.php")
    suspend fun getSearchMeals(@Query("s") name : String) : MealDetailResponse
}