package com.example.taqvim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        val window = window
        window.statusBarColor = ContextCompat.getColor(this, R.color.app_dark_wite_2)
        window.navigationBarColor = ContextCompat.getColor(this,R.color.app_dark_wite_2)


        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        val country = sharedPreference.getString("country_name", null)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)


    }
}