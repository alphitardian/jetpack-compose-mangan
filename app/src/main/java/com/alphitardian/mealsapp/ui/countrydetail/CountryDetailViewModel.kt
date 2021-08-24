package com.alphitardian.mealsapp.ui.countrydetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphitardian.mealsapp.model.MealRepository
import com.alphitardian.mealsapp.model.response.CountryMealsResponse
import com.alphitardian.mealsapp.model.response.MealResponse
import kotlinx.coroutines.launch

class CountryDetailViewModel(private val repository: MealRepository = MealRepository()) : ViewModel() {

    val countryMealsState : MutableState<List<MealResponse>> = mutableStateOf(emptyList())
    var loading = mutableStateOf(true)

    fun getCountryMeals(country : String) {
        viewModelScope.launch {
            val meals = repository.getCountryMeals(country).meals
            countryMealsState.value = meals
            loading.value = false
        }
    }
}