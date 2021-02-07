package com.example.android_projekt.detail


import android.os.Bundle
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android_projekt.R
import com.example.android_projekt.databinding.FragmentDetailBinding
import android.widget.ImageButton
import com.example.android_projekt.model.Restaurants
import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_detail.*



class DetailFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNUSED_PARAMETER")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val rest = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(rest, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)

        fun makePhoneCall(number: String): Boolean {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:$number"))
            startActivity(intent)
            return true

        }

        fun reload() {
            val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false)
            }
            ft.detach(this).attach(this).commit()
        }


        binding.btnGoogleMaps.setOnClickListener() {
            val latitude = rest.lat
            val longitude = rest.lng
            // val gmmIntentUri = Uri.parse("geo:46.53871527159372,24.570544322108105")
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }
        binding.btnTelephone.setOnClickListener() {
            val number = rest.phone
            number?.let { it1 -> makePhoneCall(it1) }
        }


        val db = context?.let { FDataBaseHandler(context = it) }

        if (db?.favoritesCheckedId(rest.id) == true) {
            binding.btnFavorites.text = "Remove from Favorites"
        } else {
            binding.btnFavorites.text = "Add to Favorites"
        }

        binding.btnFavorites.setOnClickListener() {
            if (binding.btnFavorites.text == "Add to Favorites") {

                val newFavorite = Favorites(
                    rest.id,
                    rest.name
                )

                db?.insertDataFavorites(newFavorite)
                btn_favorites.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark))
                Toast.makeText(context, "Item added to favorite list", Toast.LENGTH_SHORT).show()

                reload()
            }

            if (binding.btnFavorites.text == "Remove from Favorites") {
                val db = context?.let { FDataBaseHandler(context = it) }
                db!!.deleteDataFavorites(rest.id)
                btn_favorites.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark))
                Toast.makeText(context, "Item removed from favorite list", Toast.LENGTH_SHORT)
                    .show()
                reload()

            }
        }
            return binding.root
        }


}



