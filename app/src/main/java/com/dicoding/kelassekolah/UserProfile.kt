package com.dicoding.kelassekolah

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import java.util.*

class UserProfile : AppCompatActivity(), View.OnClickListener {
    private lateinit var notificationManager: NotificationManager
    private lateinit var checkBoxTaskNotification: CheckBox
    private lateinit var languageSharedPref: LanguageSharedPref
    private lateinit var appLanguageCode: String

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createApp()

        checkBoxTaskNotification = findViewById(R.id.cb_task_notification)
        checkBoxTaskNotification.setOnClickListener(this)

        notificationManager = NotificationManager(this@UserProfile)

        languageSharedPref = LanguageSharedPref(this)
        appLanguageCode = languageSharedPref.getLanguage()
        setAppLanguage(appLanguageCode)

        radioCheckOnLanguage(appLanguageCode)
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

    private fun onMultipleOptionClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.cb_task_notification -> {
                    if (checked) {
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
                }
            }
        }
    }

    fun onRadioLanguageOptionsClicked(view: View) {
        if (view is RadioButton) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.r_indonesian -> {
                    if (checked) {
                        setAppLanguage("in")

                        languageSharedPref.setLanguage("in")
                    }

                    Log.d("UserProfile", "Language changed, Indonesian")

                }
                R.id.r_english -> {
                    if (checked) {
                        setAppLanguage("en")

                        languageSharedPref.setLanguage("en")
                    }

                    Log.d("UserProfile", "Language changed, English")

                }
                R.id.r_japanese -> {
                    if (checked) {
                        setAppLanguage("jp")

                        languageSharedPref.setLanguage("jp")
                    }
                }
            }
        }
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

        createApp()

        radioCheckOnLanguage(langCode)
    }

    private fun radioCheckOnLanguage(langCode: String) {
        val elementId: Int = when (langCode) {
            "in" -> R.id.r_indonesian
            "en" -> R.id.r_english
            "jp" -> R.id.r_japanese
            else -> R.id.r_indonesian
        }

        findViewById<RadioGroup>(R.id.rg_language).check(elementId)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cb_task_notification -> {
                onMultipleOptionClicked(checkBoxTaskNotification)
            }
        }
    }
}