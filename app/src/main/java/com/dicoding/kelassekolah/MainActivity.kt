package com.dicoding.kelassekolah

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvEvents: RecyclerView
    private lateinit var btnChangeLayout: Button
    private var list: ArrayList<Event> = arrayListOf()
    private lateinit var languageSharedPref: LanguageSharedPref
    private lateinit var appLanguageCode: String

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        languageSharedPref = LanguageSharedPref(applicationContext)
        appLanguageCode = languageSharedPref.getLanguage()
        setAppLanguage(appLanguageCode)

        Log.d("MainActivity", appLanguageCode)

        createApp()

        rvEvents = findViewById(R.id.rv_events)
        rvEvents.setHasFixedSize(true)
        btnChangeLayout = findViewById(R.id.action_button_change_layout)
        btnChangeLayout.setOnClickListener(this)

        list.addAll(EventsData.listData)
        showRecyclerCardview()
    }

    private fun createApp() {
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun showRecyclerList() {
        rvEvents.layoutManager = LinearLayoutManager(this)
        val listEventAdapter = ListEventAdapter(list, this)
        rvEvents.adapter = listEventAdapter
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            androidx.appcompat.R.id.home -> {
                onBackPressed()
            }

            R.id.icPersonWhite -> {
                val userProfileIntent = Intent(this@MainActivity, UserProfile::class.java)
                startActivity(userProfileIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_button_change_layout -> {
                Log.d("ClassGallery", "Action Button Change Layout Clicked")
                setNextIcon()
            }
        }
    }

    private fun setNextIcon() {
        when (btnChangeLayout.text) {
            "Grid" -> {
                showRecyclerGrid()
                btnChangeLayout.text = getString(R.string.cardview)
                btnChangeLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_view_sidebar_24_gray, 0, 0, 0)
            }
            "CardView" -> {
                showRecyclerCardview()
                btnChangeLayout.text = getString(R.string.list)
                btnChangeLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_view_list_24_gray, 0, 0, 0)
            }
            "List" -> {
                showRecyclerList()
                btnChangeLayout.text = getString(R.string.grid)
                btnChangeLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_view_module_24_gray, 0, 0, 0)
            }
        }
    }

    private fun showRecyclerCardview() {
        rvEvents.layoutManager = LinearLayoutManager(this)
        val cardViewEventAdapter = CardViewEventAdapter(list, this)
        rvEvents.adapter = cardViewEventAdapter
    }

    private fun showRecyclerGrid() {
        rvEvents.layoutManager = GridLayoutManager(this, 2)
        val gridEventAdapter = GridEventAdapter(list, this)
        rvEvents.adapter = gridEventAdapter
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
}