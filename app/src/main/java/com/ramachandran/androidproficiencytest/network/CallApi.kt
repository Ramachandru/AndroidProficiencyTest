package com.ramachandran.androidproficiencytest.network

import com.ramachandran.androidproficiencytest.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CallApi {
    fun getRtrofitInstance() = Retrofit.Builder()
           .baseUrl(ApiService.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
}