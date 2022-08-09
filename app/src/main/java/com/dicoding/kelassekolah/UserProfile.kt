package com.dicoding.kelassekolah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class UserProfile : AppCompatActivity() {
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}