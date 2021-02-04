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
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


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
        val adressText: EditText = view.findViewById<EditText>(R.id.adress_id)
        val emailText: EditText = view.findViewById<EditText>(R.id.email_id)
        val phoneNumberText: EditText = view.findViewById<EditText>(R.id.phone_number_id)

        var db = context?.let { DataBaseHandler(context = it) }

            fun insert() {
                if (nameText.text.toString().length > 0 &&
                    adressText.text.toString().length > 0 &&
                    phoneNumberText.text.toString().length > 0 &&
                    emailText.text.toString().length > 0) run {
                    var profile = Profile(
                        nameText.text.toString(),
                        adressText.text.toString(),
                        phoneNumberText.text.toString().toInt(),
                        emailText.text.toString()
                    )

                    db?.insertData(profile)
                    db?.insertData(profile)
                }
                }


            insertButton.setOnClickListener {
                if (id > 1) {
                    db!!.deleteData()
                    insert()
                } else {
                    insert()
                }
            }

        return view
        }

}




