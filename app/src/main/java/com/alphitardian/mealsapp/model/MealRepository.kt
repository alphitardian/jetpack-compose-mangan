package com.alphitardian.mealsapp.model

import com.alphitardian.mealsapp.model.api.MealsWebService
import com.alphitardian.mealsapp.model.response.MealCategoriesResponse
import com.alphitardian.mealsapp.model.response.MealCategoryResponse
import com.alphitardian.mealsapp.model.response.MealDetailResponse

class MealRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMealCategories(): MealCategoriesResponse {
        return webService.getCategories()
    }

    suspend fun getCategory(name : String) : MealCategoryResponse {
        return webService.getMealCategory(name)
    }

    suspend fun getMealDetail(id : String) : MealDetailResponse {
        return webService.getMealDetail(id)
    }
}