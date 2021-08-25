package com.alphitardian.mealsapp.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphitardian.mealsapp.model.MealRepository
import com.alphitardian.mealsapp.model.response.MealDetail
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: MealRepository = MealRepository()) : ViewModel() {
    var textFieldState = MutableLiveData("")
    var mealSearchState: MutableState<List<MealDetail>> = mutableStateOf(emptyList())
    var loading = mutableStateOf(true)

    init {
        viewModelScope.launch {
            val meals = repository.getSearchMeals("").meals
            mealSearchState.value = meals
            loading.value = false
        }
    }

    fun onTextChanged(value: String) {
        textFieldState.value = value
    }

    suspend fun getSearchMeals(name: String) {
        viewModelScope.launch {
            val meals = repository.getSearchMeals(name).meals
            mealSearchState.value = meals
            loading.value = false
        }
    }
}