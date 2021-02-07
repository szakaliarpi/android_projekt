package com.example.android_projekt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import java.net.CacheResponse
import javax.security.auth.callback.Callback

class RestaurantsViewModel: ViewModel() {
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init{
        getRealEstateProperties()
    }

    private fun getRealEstateProperties() {
        RestaurantsApi.retrofitService.getProperties().enqueue(object: retrofit2.Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " +  t.message
            }

        })


        _response.value = "Set the  API Response here."

    }


}