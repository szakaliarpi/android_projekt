package com.example.android_projekt.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_projekt.databinding.FragmentRestaurantsBinding

class   RestaurantsFragment : Fragment() {

    private val viewModel: RestaurantsViewModel by lazy{
        ViewModelProvider(this).get(RestaurantsViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val view : View = inflater.inflate(R.layout.fragment_restaurants, container, false)
        val binding = FragmentRestaurantsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
        //return view
    }

}


