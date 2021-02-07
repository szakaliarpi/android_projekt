package com.example.android_projekt.profile

class Profile {
    var id = 0
    var name = ""
    var address = ""
    var phone_number = 0
    var email = ""
    var img = ""

    constructor(name: String, adress: String, phone_number: Int, email: String, img: String){
        this.name = name
        this.address = adress
        this.phone_number = phone_number
        this.email = email
        this.img = img
    }

    constructor(){

    }
}