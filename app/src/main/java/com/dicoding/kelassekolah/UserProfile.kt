package com.dicoding.kelassekolah

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import java.util.*

class UserProfile : AppCompatActivity(), View.OnClickListener {
    private lateinit var notificationManager: NotificationManager
    private lateinit var checkBoxTaskNotification: CheckBox
    private lateinit var checkBoxDarkTheme: CheckBox
    private lateinit var languageSharedPref: LanguageSharedPref
    private lateinit var appLanguageCode: String

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        languageSharedPref = LanguageSharedPref(applicationContext)
        appLanguageCode = languageSharedPref.getLanguage()
        setAppLanguage(appLanguageCode)

        createApp()
        radioCheckOnLanguage(appLanguageCode)

        checkBoxTaskNotification = findViewById(R.id.cb_task_notification)
        checkBoxTaskNotification.setOnClickListener(this)
        checkBoxDarkTheme = findViewById(R.id.cb_dark_theme)
        checkBoxDarkTheme.setOnClickListener(this)

        notificationManager = NotificationManager(this@UserProfile)

        checkCurrentDarkMode()
    }

    private fun createApp() {
        setContentView(R.layout.activity_user_profile)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)

        return super.onCreateOptionsMenu(menu)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            androidx.appcompat.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.icClassWhite -> {
                val studentTaskActivity = Intent(this@UserProfile, StudentTaskActivity::class.java)
                startActivity(studentTaskActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onOptionsNotification(view: CheckBox) {
        if (view.isChecked) {
            notificationManager.startNotification(
                getString(R.string.task_notification),
                getString(R.string.mathematics_trigonometry_task_xi_deadline)
            )
        } else {
            Toast.makeText(
                this@UserProfile,
                getString(R.string.task_notification_would_not_appear),
                Toast.LENGTH_SHORT
            ).show()
        }

        Log.d("UserProfile", "Task Notification")
    }

    private fun onOptionsDarkMode(view: CheckBox) {
        if (view.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Log.d("UserProfile", "R DarkMode")
    }

    fun onRadioLanguageOptionsClicked(view: View) {
        if (view is RadioButton) {
            val langCode: String = when (view.id) {
                R.id.r_indonesian -> "in"
                R.id.r_english -> "en"
                R.id.r_japanese -> "ja"
                else -> "en"
            }

            setAppLanguage(langCode)
            languageSharedPref.setLanguage(langCode)
            createApp()
            radioCheckOnLanguage(langCode)

            dialogConfirmRestartAppOnLanguageChanged()
        }
    }

    private fun dialogConfirmRestartAppOnLanguageChanged() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.restart_app))
            .setMessage(getString(R.string.language_changing_need_to_restart_app))

            .setPositiveButton(getString(R.string.restart)) {
                dialog, which ->
                    Toast.makeText(this, getString(R.string.restarting_app), Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(applicationContext, MainActivity::class.java)
                )
                System.exit(0)
            }

            .setNegativeButton(getString(R.string.restart_later)) {
                dialog, which -> Toast.makeText(this, getString(R.string.this_app_need_restart), Toast.LENGTH_SHORT).show()
            }

            .show()
    }

    private fun setAppLanguage(langCode: String) {
        val config = resources.configuration
        val languageToLoad: String = langCode
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            createConfigurationContext(config)
        }

        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun radioCheckOnLanguage(langCode: String) {
        val elementId: Int = when (langCode) {
            "in" -> R.id.r_indonesian
            "en" -> R.id.r_english
            "ja" -> R.id.r_japanese
            else -> R.id.r_indonesian
        }

        findViewById<RadioGroup>(R.id.rg_language).check(elementId)
    }

    private fun checkCurrentDarkMode() {
//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
//            checkBoxDarkTheme.isChecked = true
//
//            Log.d("UserProfile","checkBoxDarkTheme true")
//        }

        val currentNightMode = (resources.configuration.uiMode
                and Configuration.UI_MODE_NIGHT_MASK)

        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                checkBoxDarkTheme.isChecked = false
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                checkBoxDarkTheme.isChecked = true
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                checkBoxDarkTheme.isChecked = false
            }
        }

        Log.d("UserProfile", checkBoxDarkTheme.isChecked.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cb_task_notification -> {
                onOptionsNotification(checkBoxTaskNotification)
            }

            R.id.cb_dark_theme -> {
                onOptionsDarkMode(checkBoxDarkTheme)
            }
        }
    }
}