package com.example.android_projekt.detail

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_projekt.model.Restaurants


class DetailViewModel(rest: Restaurants, app: Application): AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<Restaurants>()

    val selectedProperty: LiveData<Restaurants>
        get() = _selectedProperty

    init {
        _selectedProperty.value = rest
    }
}