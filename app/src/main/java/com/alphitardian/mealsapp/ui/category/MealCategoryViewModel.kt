package com.alphitardian.mealsapp.ui.category

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphitardian.mealsapp.model.MealRepository
import com.alphitardian.mealsapp.model.response.CategoryResponse
import kotlinx.coroutines.launch

class MealCategoryViewModel(private val repository: MealRepository = MealRepository()) :
    ViewModel() {

    val mealCategoryState: MutableState<List<CategoryResponse>> = mutableStateOf(emptyList())
    var loading = mutableStateOf(true)
    var bottomSheetValue: MutableState<CategoryResponse?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            val meals = getMealCategories()
            mealCategoryState.value = meals
            loading.value = false
        }
    }

    private suspend fun getMealCategories(): List<CategoryResponse> {
        return repository.getMealCategories().categories
    }
}