package com.dicoding.kelassekolah

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class LanguageSharedPref(activity: Activity) {
    val sharedPref: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
    val prefName: String = "com.dicoding.kelassekolah.languagepref"

    fun getLanguage(): String {
        return sharedPref.getString(prefName, "in") ?: "in"
    }

    fun setLanguage(langCode: String) {
        sharedPref.edit().putString(prefName, langCode).apply()
    }
}