package com.example.android_projekt.favourites


class Favourites {
    val fid: Int = 0
    var id: Int = 0
    var name: String? = ""

    constructor(id: Int, name: String?){
        this.id = id
        this.name = name
    }
    constructor(){ }

}