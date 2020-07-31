package com.ramachandran.androidproficiencytest.api

import com.ramachandran.androidproficiencytest.network.model.DropBoxUserContent
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    companion object{
        const val BASE_URL : String = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    }
    @GET("facts.json")
    fun getUserContent() : Call<DropBoxUserContent>
}