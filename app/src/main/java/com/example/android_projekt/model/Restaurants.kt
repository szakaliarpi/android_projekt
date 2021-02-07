package com.example.android_projekt.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurants(
    val address: String?,
    val area: String?,
    val city: String?,
    val country: String?,
    val id: Int,
    @Json(name = "image_url") val ImgSrcUrl: String?,
    val lat: Double,
    val lng: Double,
    val mobile_reserve_url: String?,
    val name: String?,
    val phone: String?,
    val postal_code: String?,
    val price: Int,
    val reserve_url: String?,
    val state: String?
):Parcelable