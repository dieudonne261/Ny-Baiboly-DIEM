package com.example.nybaiboly

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.media.RouteListingPreference
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.nybaiboly.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.selectedItemId = binding.bottomNavigationView.menu.getItem(1).itemId
        val (isModeSombre, isSetting, languageSelect, bibleFontSize) = DataSample.retrievePreferences(this)

        if (isModeSombre) {
            DataSample.savePreferences(this,true, isSetting,languageSelect,bibleFontSize)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else {
            DataSample.savePreferences(this,false, isSetting,languageSelect,bibleFontSize)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        if (isSetting){
            DataSample.savePreferences(this,isModeSombre, false,languageSelect,bibleFontSize)
            replaceFragment(SettingFragment())
        }
        else{
            replaceFragment(HomeFragment())
        }
/*
        if (languageSelect == "Malagasy"){
            findViewById<Toolbar>(R.id.toolbar).title = "Ny Baiboly"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(0).title = "Farany"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(1).title = "Tongasoa"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(2).title = "Fikirana"
        }
        else if (languageSelect == "Français"){
            findViewById<Toolbar>(R.id.toolbar).title = "La Baiboly"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(0).title = "Recent"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(1).title = "Bienvenue"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(2).title = "Paramètre"

        }
        else {
            findViewById<Toolbar>(R.id.toolbar).title = "The Baiboly"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(0).title = "Recent"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(1).title = "Welcome"
            findViewById<BottomNavigationView>(R.id.bottomNavigationView).menu.getItem(2).title = "Setting"

        }
*/
            /*
                    if (languageSelect == "Malagasy"){
                        findViewById<Toolbar>(R.id.toolbar).title = "Ny Baiboly"
                    }
                    else if (languageSelect == "Français"){
                        //findViewById<TextView>(R.id.textView).text = "La Baiboly"
                        findViewById<Toolbar>(R.id.toolbar).title = "La Baiboly"
                        /*findViewById<Toolbar>(R.id.signet).title = "Recent"
                        findViewById<Toolbar>(R.id.menu).title = "Bienvenue"
                        findViewById<Toolbar>(R.id.setting).title = "Parametre"*/
                        findViewById<TextInputEditText>(R.id.rechtxt).hint = "Rechercher"
                        /*findViewById<TextView>(R.id.textView2).text = "Mode sombre"
                        findViewById<TextView>(R.id.textView3).text = "Language"
                        findViewById<TextView>(R.id.textView4).text = "Taille de police"*/

                    }
                    else {
                        findViewById<TextView>(R.id.textView).text = "The Baiboly"
                    }
            */

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.signet -> replaceFragment(RecentFragment())
                R.id.menu -> replaceFragment(HomeFragment())
                R.id.setting -> replaceFragment(SettingFragment())
            }
            true
        }


    }
    private fun displayPreferences() {
        val (isSombre,isSetting, languageSelect, bibleFontSize) = DataSample.retrievePreferences(this)
        val message = "isModeSombre: $isSombre\nisSetting: $isSetting\nLanguage: $languageSelect\nBible Font Size: $bibleFontSize"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
