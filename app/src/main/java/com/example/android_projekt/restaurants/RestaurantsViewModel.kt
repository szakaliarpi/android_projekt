package com.example.android_projekt.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_projekt.RestaurantsApi
import retrofit2.Call
import retrofit2.Response
import com.example.android_projekt.model.Restaurants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantsViewModel: ViewModel() {
    private val _status = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<Restaurants>()

    val property: LiveData<Restaurants>

        get() = _property

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init{
        getRealEstateProperties()
    }

    private fun getRealEstateProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = RestaurantsApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                if (listResult.restaurants.size > 0) {
                    _property.value = listResult.restaurants[0]
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}