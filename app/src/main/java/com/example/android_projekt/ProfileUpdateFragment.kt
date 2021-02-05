package com.example.android_projekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text
import android.content.Context
import android.util.Log
import android.widget.*
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.graphics.Bitmap


class ProfileUpdateFragment : Fragment() {

    var isMyBoolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_profile_update, container, false)

        val insertButton: Button = view.findViewById<Button>(R.id.btn_insert)

        val nameText: EditText = view.findViewById<EditText>(R.id.name_id)
        val addressText: EditText = view.findViewById<EditText>(R.id.adress_id)
        val emailText: EditText = view.findViewById<EditText>(R.id.email_id)
        val phoneNumberText: EditText = view.findViewById<EditText>(R.id.phone_number_id)


        var db = context?.let { DataBaseHandler(context = it) }

            fun insert() {

                var data = db?.readData()
                var imgUri = ""
                if (data != null) {
                    for (i in 0..(data.size - 1)) {
                        imgUri = data.get(i).img
                    }
                }

                if (nameText.text.toString().length > 0 &&
                    addressText.text.toString().length > 0 &&
                    phoneNumberText.text.toString().length > 0 &&
                    emailText.text.toString().length > 0) run {
                    var profile = Profile(
                        nameText.text.toString(),
                        addressText.text.toString(),
                        phoneNumberText.text.toString().toInt(),
                        emailText.text.toString(),
                        imgUri
                    )

                    db!!.deleteData()

                    db?.insertData(profile)
                    db?.insertData(profile)
                }
                }

        insertButton.setOnClickListener({
            if(id>1){
                //db!!.deleteData()
            }
            insert()
        })
        return view
    }
}




