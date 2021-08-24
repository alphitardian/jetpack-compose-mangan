package com.alphitardian.mealsapp.model.response

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("strMeal")
    val mealName : String,
    @SerializedName("strMealThumb")
    val imageUrl: String,
    @SerializedName("idMeal")
    val id: String,
)
