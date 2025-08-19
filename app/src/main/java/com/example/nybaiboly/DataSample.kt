package com.example.nybaiboly

import android.content.Context

object DataSample {

    private const val SHARED_PREFS_NAME = "preferences"

    fun savePreferences(context: Context, isModeSombre: Boolean, isSetting: Boolean, languageSelect: String, bibleFontSize: Int) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isModeSombre", isModeSombre)
        editor.putBoolean("isSetting", isSetting)
        editor.putString("languageSelect", languageSelect)
        editor.putInt("bibleFontSize", bibleFontSize)
        editor.apply()
    }

    fun saveTestament(context: Context, idTestament: Int, idLivre: Int, idChapitre: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("id_testament", idTestament)
        editor.putInt("id_livre", idLivre)
        editor.putString("id_chapitre", idChapitre)
        editor.apply()
    }

    fun retrievePreferences(context: Context): Quadruple<Boolean, Boolean, String, Int> {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val isModeSombre = sharedPreferences.getBoolean("isModeSombre", false)
        val isSetting = sharedPreferences.getBoolean("isSetting", false)
        val languageSelect = sharedPreferences.getString("languageSelect", "Default Language") ?: "Default Language"
        val bibleFontSize = sharedPreferences.getInt("bibleFontSize", 5)
        return Quadruple(isModeSombre, isSetting, languageSelect, bibleFontSize)
    }

    fun retrieveTestament(context: Context): Triple<Int, Int, String?> {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val idTestament = sharedPreferences.getInt("id_testament", 0)
        val idLivre = sharedPreferences.getInt("id_livre", 0)
        val idChapitre = sharedPreferences.getString("id_chapitre", "")
        return Triple(idTestament, idLivre, idChapitre)
    }

    data class Quadruple<A, B, C, D>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D
    )


}
