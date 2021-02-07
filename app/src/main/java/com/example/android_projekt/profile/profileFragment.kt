package com.example.android_projekt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android_projekt.profile.DataBaseHandler
import com.example.android_projekt.profile.Profile
import com.example.android_projekt.profile.ProfileUpdateFragment
import com.example.android_projekt.R
import com.example.android_projekt.favourites.FDataBaseHandler


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val updateButton = view.findViewById<Button>(R.id.btn_profile)

        val profileResult = view.findViewById<TextView>(R.id.text_profile)

        val imgButton = view.findViewById<Button>(R.id.btn_update_profilepic)

        val favoriteResults = view.findViewById<TextView>(R.id.text_results_favorites)

        val fdb = context?.let { FDataBaseHandler(context = it) }
        val fdata = fdb?.readDataFavorites()
        favoriteResults.text = ""

        if(fdata != null){
            for(i in 0..(fdata.size-1)){
                favoriteResults.append("\n" + fdata.get(i).name)
            }
        }

        if (favoriteResults.text == "") {
            favoriteResults.append("You don't have any item on your favorite list yet!")
        }

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

        val profilePicture = requireView().findViewById<ImageView>(R.id.profile_picture)

        if (data != null) {
            for (i in 0 until data.size - 1) {

                profileResult.append("\nName: " + data.get(i).name + "\nAdress: "
                        + data.get(i).address + "\nPhone Number:  " + data.get(i).phone_number + "\nEmail:  " + data.get(i).email + '\n')

                var imgUri = Uri.parse(data[i].img)
                profilePicture!!.setImageURI(imgUri)
            }
        }
        if(profileResult.text == "") {
            profileResult.append("Nincs bevitt adat. \n\n tolts fel adatot!")
        }

        imgButton.setOnClickListener(){
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,456)
        }

        return view

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val profilePicture = view!!.findViewById<ImageView>(R.id.profile_picture)

        if (requestCode == 456) {
            profilePicture!!.setImageURI(data?.data)
            val dataPic = data?.data.toString()

            var db = context?.let { DataBaseHandler(context = it) }

            var data = db?.readData()
            if(id>1){
                db!!.deleteData()
            }
            if (data != null) {
                for (i in 0..(data.size-1)) {
                    val name = data.get(i).name
                    val adress = data.get(i).address
                    val phone_number = data.get(i).phone_number
                    val email = data.get(i).email
                    val profile = Profile(name, adress, phone_number, email, dataPic)

                    db?.insertData(profile)
                }
            }
        }
    }
}



