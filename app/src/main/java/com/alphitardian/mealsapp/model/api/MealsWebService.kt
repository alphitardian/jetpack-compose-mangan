package com.alphitardian.mealsapp.model.api

import com.alphitardian.mealsapp.model.response.MealCategoriesResponse
import com.alphitardian.mealsapp.model.response.MealCategoryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsWebService {
    private var api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    suspend fun getCategories(): MealCategoriesResponse {
        return api.getCategories()
    }

    suspend fun getMealCategory(name : String) : MealCategoryResponse {
        return api.getCategoryMeals(name)
    }

}