package com.example.taqvim.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.taqvim.MainActivity
import com.example.taqvim.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val window = window;
        window.statusBarColor = ContextCompat.getColor(this, R.color.app_dark_wite);
        window.navigationBarColor = ContextCompat.getColor(this,R.color.app_color);

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",MODE_PRIVATE)
        val country = sharedPreference.getString("country_name", null)

        if (country != null){
            val intent =Intent(this,MainActivity::class.java);
            startActivity(intent)
            finish()
        }else{
            card_view.setOnClickListener {
                val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("country_name","Toshkent")
                editor.commit()
                val intent =Intent(this,MainActivity::class.java);
                startActivity(intent)
                finish()
            }
        }

    }
}