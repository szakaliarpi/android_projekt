package com.example.android_projekt

class Profile {
    var id = 0
    var name = ""
    var adress = ""
    var phone_number = 0
    var email = ""
    var img = ""

    constructor(name: String, adress: String, phone_number: Int, email: String, img: String){
        this.name = name
        //this.img = img
        this.adress = adress
        this.phone_number = phone_number
        this.email = email
    }
}