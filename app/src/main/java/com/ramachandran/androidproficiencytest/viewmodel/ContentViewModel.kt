package com.ramachandran.androidproficiencytest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramachandran.androidproficiencytest.api.ApiService
import com.ramachandran.androidproficiencytest.network.CallApi
import com.ramachandran.androidproficiencytest.network.model.DropBoxUserContent
import com.ramachandran.androidproficiencytest.network.model.Row
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentViewModel : ViewModel(){
    private var sharedData: MutableLiveData<DropBoxUserContent>? = null
    fun getCoreData() : MutableLiveData<DropBoxUserContent> {
        getParsingDatafromFeed()
        return  sharedData!!
    }
    private fun getParsingDatafromFeed(){
        sharedData = MutableLiveData()
        val callApi = CallApi()
        val apiService = callApi.getRtrofitInstance().create(ApiService::class.java)
        val call = apiService.getUserContent()
        call.enqueue(object : Callback<DropBoxUserContent>{
            override fun onFailure(call: Call<DropBoxUserContent>, t: Throwable) {
                Log.v("","Error : "+t.message)
            }

            override fun onResponse(
                call: Call<DropBoxUserContent>,
                response: Response<DropBoxUserContent>
            ) {
               sharedData!!.value = response.body()
            }

        })
    }
}