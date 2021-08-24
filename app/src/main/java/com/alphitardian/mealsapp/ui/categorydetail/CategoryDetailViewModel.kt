package com.alphitardian.mealsapp.ui.categorydetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphitardian.mealsapp.model.MealRepository
import com.alphitardian.mealsapp.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryDetailViewModel(private val repository: MealRepository = MealRepository()) : ViewModel() {

    val mealCategoryState: MutableState<List<MealResponse>> = mutableStateOf(emptyList())
    var loading = mutableStateOf(true)
    var bottomSheetValue : MutableState<MealResponse?> = mutableStateOf(null)

    fun getCategory(name : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val categoryList = repository.getCategory(name).meals
            mealCategoryState.value = categoryList
            loading.value = false
        }
    }

}