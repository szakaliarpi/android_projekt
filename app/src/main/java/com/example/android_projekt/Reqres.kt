package com.example.android_projekt

data class Reqres(
    val current_page: Int,
    val per_page: Int,
    val restaurants: List<Restaurants>,
    val total_entries: Int
)