package com.example.android_projekt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import java.lang.reflect.Array.newInstance


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val updateButton = view.findViewById<Button>(R.id.btn_profile)

        val profileResult = view.findViewById<TextView>(R.id.text_profile)


        updateButton?.setOnClickListener { view ->

            val fragment = ProfileUpdateFragment()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }

        val db = context?.let { DataBaseHandler(context = it) }

        //read the data to the profile screen
        val data = db?.readData()
        profileResult.text = ""
        if (data != null) {
            for (i in 0 until data.size) {
                profileResult.append("\nName: " + data[i].name + "\n\nAdress: "
                        + data[i].adress + "\n\nPhone Number:  " + data[i].phone_number + "\n\nEmail:  " + data[i].email + '\n')
            }
        }
        if(profileResult.text == "") {
            profileResult.append("Nincs bevitt adat. \n\n tolts fel adatot!")
        }

        return view

    }
}



