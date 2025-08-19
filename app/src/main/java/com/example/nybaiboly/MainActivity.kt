package com.example.nybaiboly

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val element = DataSample.retrievePreferences(this)
        if (element.first) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        /*
        if (element.third == "Malagasy"){
            findViewById<TextView>(R.id.textView).text = "Ny Baiboly"
        }
        else if( element.third == "Français"){
            findViewById<TextView>(R.id.textView).text = "La Baiboly"
        }
        else {
            findViewById<TextView>(R.id.textView).text = "The Baiboly"
        }

         */
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}