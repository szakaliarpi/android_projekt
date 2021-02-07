package com.example.android_projekt.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_projekt.RestaurantsApi
import retrofit2.Call
import retrofit2.Response
import com.example.android_projekt.model.Restaurants

class RestaurantsViewModel: ViewModel() {
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init{
        getRealEstateProperties()
    }

    private fun getRealEstateProperties() {
        RestaurantsApi.retrofitService.getProperties().enqueue(object: retrofit2.Callback<List<Restaurants>>{
            override fun onFailure(call: Call<List<Restaurants>>, t: Throwable) {
                _response.value = "Failure: " +  t.message
            }
            override fun onResponse(
                call: Call<List<Restaurants>>,
                response: Response<List<Restaurants>>
            ) {
                _response.value = "Success:  + ${response.body()?.size} properties retriever"
            }

        })

        _response.value = "API Response: "
    }
}