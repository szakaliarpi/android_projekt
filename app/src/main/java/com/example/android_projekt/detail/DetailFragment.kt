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

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        @Suppress("UNUSED_PARAMETER")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val rest = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(rest,application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)

        binding.btnGoogleMaps.setOnClickListener( {
            val latitude = rest.lat
            val longitude = rest.lng
            // val gmmIntentUri = Uri.parse("geo:46.53871527159372,24.570544322108105")
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        })

        return binding.root
    }
}