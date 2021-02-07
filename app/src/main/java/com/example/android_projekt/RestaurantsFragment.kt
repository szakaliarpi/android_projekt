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
import androidx.lifecycle.ViewModelProvider

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


