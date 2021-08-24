package com.alphitardian.mealsapp.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MealCategoryResponse(
    @SerializedName("meals")
    val meals: List<MealResponse>
)
