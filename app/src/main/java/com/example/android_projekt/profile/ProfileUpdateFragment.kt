package com.example.android_projekt.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.*
import com.example.android_projekt.R
import com.example.android_projekt.profile.DataBaseHandler


class ProfileUpdateFragment : Fragment() {

    var isMyBoolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_profile_update, container, false)

        val insertButton = view.findViewById<Button>(R.id.btn_insert)

        val nameText = view.findViewById<EditText>(R.id.name_id)
        val addressText = view.findViewById<EditText>(R.id.adress_id)
        val emailText = view.findViewById<EditText>(R.id.email_id)
        val phoneNumberText = view.findViewById<EditText>(R.id.phone_number_id)


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




