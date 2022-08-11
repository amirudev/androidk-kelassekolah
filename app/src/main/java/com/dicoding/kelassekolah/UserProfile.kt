package com.dicoding.kelassekolah

import android.content.Context
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
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import java.util.*

class UserProfile : AppCompatActivity(), View.OnClickListener {
    private lateinit var notificationManager: NotificationManager

    private lateinit var checkBoxTaskNotification: CheckBox

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createApp()

        checkBoxTaskNotification = findViewById(R.id.cb_task_notification)
        checkBoxTaskNotification.setOnClickListener(this)

        notificationManager = NotificationManager(this@UserProfile)
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
                            "Notifikasi Tugas",
                            "Tugas Trigonometri Matematika XI akan habis tenggat waktunya dalam 6 Jam lagi (Contoh)"
                        )
                    } else {
                        Toast.makeText(
                            this@UserProfile,
                            "Notifikasi Tugas tidak akan ditampilkan",
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

                        createApp()
                    }

                    Log.d("UserProfile", "Language changed, Indonesian")

                }
                R.id.r_english -> {
                    if (checked) {
                        setAppLanguage("en")

                        createApp()
                    }

                    Log.d("UserProfile", "Language changed, English")

                }
                R.id.r_japanese -> {
                    if (checked) {
                        setAppLanguage("jp")

                        createApp()
                    }
                }
            }
        }
    }

    private fun setAppLanguage(langCode: String) {
        val config = resources.configuration
        val languageToLoad: String = langCode;
        val locale: Locale = Locale(languageToLoad)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cb_task_notification -> {
                onMultipleOptionClicked(checkBoxTaskNotification)
            }
        }
    }
}