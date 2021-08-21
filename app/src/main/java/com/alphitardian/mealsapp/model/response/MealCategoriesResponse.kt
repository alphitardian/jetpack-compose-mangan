package com.alphitardian.mealsapp.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MealCategoriesResponse(
    @SerializedName("categories")
    val categories: List<CategoryResponse>
)

@Parcelize
data class CategoryResponse(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strCategoryDescription")
    val desc: String,
    @SerializedName("strCategoryThumb")
    val imageUrl: String
) : Parcelable