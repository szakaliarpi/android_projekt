package com.example.android_projekt.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_projekt.model.Restaurants


class DetailViewModelFactory(
    private val rest: Restaurants,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(rest, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}