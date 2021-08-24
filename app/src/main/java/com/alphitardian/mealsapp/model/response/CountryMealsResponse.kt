package com.alphitardian.mealsapp.model.response

import com.google.gson.annotations.SerializedName

data class CountryMealsResponse(
    @SerializedName("meals")
    val meals : List<MealResponse>
)