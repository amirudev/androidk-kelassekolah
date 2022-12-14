package com.dicoding.kelassekolah

import android.content.Context
import android.content.SharedPreferences

class LanguageSharedPref(context: Context) {
    val prefName: String = "com.dicoding.kelassekolah.languagepref"
    val sharedPref: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun getLanguage(): String {
        return sharedPref.getString(prefName, "en")!!
    }

    fun setLanguage(langCode: String) {
        sharedPref.edit().putString(prefName, langCode).apply()
    }
}