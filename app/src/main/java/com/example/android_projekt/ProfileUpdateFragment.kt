package com.example.android_projekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text
import android.content.Context
import android.widget.TextView


class ProfileUpdateFragment : Fragment() {

    var isMyBoolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_profile_update, container, false)

        val vari: TextView = view?.findViewById<TextView>(R.id.vari)

        return view
    }
}

