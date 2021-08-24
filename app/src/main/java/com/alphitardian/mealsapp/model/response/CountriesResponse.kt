package com.alphitardian.mealsapp.model.response

import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("meals")
    val countryList : List<CountryResponse>
)

data class CountryResponse(
    @SerializedName("strArea")
    val country : String,
)