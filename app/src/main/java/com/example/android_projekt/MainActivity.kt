@file:Suppress("DEPRECATION")

package com.example.android_projekt

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileOutputStream
import android.os.Environment
import com.example.android_projekt.ProfileFragment
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri


class MainActivity : AppCompatActivity() {
//    private lateinit var bottomNavigationView: BottomNavigationView

    lateinit var profileFragment: ProfileFragment
    lateinit var restaurantsFragment: RestaurantsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNav)

        bottomNavigation.setOnNavigationItemReselectedListener { item ->
            when(item.itemId){
                R.id.btn_profile -> {
                    profileFragment = ProfileFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.frame_layout, profileFragment)
                        .commit()
                }

                R.id.btn_restaurants -> {
                    restaurantsFragment = RestaurantsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.frame_layout, restaurantsFragment)
                        .commit()
                }

            }
            true
        }
        /*bottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod)
        supportFragmentManager.beginTransaction().replace(R.id.container, profileFragment())

    }

    private val bottomNavMethod =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            var fragment: Fragment? = null
            when (menuItem.itemId) {
                R.id.restaurants -> fragment = restaurantFragment()
                R.id.profile -> fragment = profileFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment!!).commit()
            true
        }*/
    }
}


