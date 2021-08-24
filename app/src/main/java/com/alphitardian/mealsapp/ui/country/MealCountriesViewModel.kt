package com.alphitardian.mealsapp.ui.country

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphitardian.mealsapp.model.MealRepository
import com.alphitardian.mealsapp.model.response.CountriesResponse
import com.alphitardian.mealsapp.model.response.CountryResponse
import kotlinx.coroutines.launch

class MealCountriesViewModel(private val repository: MealRepository = MealRepository()) : ViewModel() {

    var countriesState : MutableState<List<CountryResponse>> = mutableStateOf(emptyList())
    var loading = mutableStateOf(true)

    init {
        viewModelScope.launch {
            val countries = getCountries().countryList
            countriesState.value = countries
            loading.value = false
        }
    }

    private suspend fun getCountries() : CountriesResponse {
        return repository.getCountries()
    }
}