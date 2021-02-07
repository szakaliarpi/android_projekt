package com.example.android_projekt.model

data class Reqres(
    var page: Int,
    val per_page: Int,
    val restaurants: List<Restaurants>,
    val total_entries: Int
)