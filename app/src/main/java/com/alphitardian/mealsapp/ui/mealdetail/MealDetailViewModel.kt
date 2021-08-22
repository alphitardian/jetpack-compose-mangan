package com.alphitardian.mealsapp.ui.mealdetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphitardian.mealsapp.model.MealRepository
import com.alphitardian.mealsapp.model.response.MealDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealDetailViewModel(private val repository: MealRepository = MealRepository()) : ViewModel() {

    val mealDetails : MutableState<MealDetail?> = mutableStateOf(null)

    var loading = mutableStateOf(true)

    fun getMealDetail(id : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val detail = repository.getMealDetail(id).meals
            mealDetails.value = detail[0]
            loading.value = false
        }
    }
}