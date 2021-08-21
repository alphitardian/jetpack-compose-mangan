package com.alphitardian.mealsapp.ui.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphitardian.mealsapp.model.MealRepository
import com.alphitardian.mealsapp.model.response.CategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealCategoryViewModel(private val repository: MealRepository = MealRepository()) :
    ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMealCategories()
            mealCategoryState.value = meals
        }
    }

    val mealCategoryState: MutableState<List<CategoryResponse>> =
        mutableStateOf(emptyList())

    private suspend fun getMealCategories(): List<CategoryResponse> {
        return repository.getMealCategories().categories
    }
}