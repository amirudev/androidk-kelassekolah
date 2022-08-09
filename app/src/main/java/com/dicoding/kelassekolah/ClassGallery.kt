package com.dicoding.kelassekolah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ClassGallery : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvEvents: RecyclerView
    private lateinit var btnChangeLayout: Button
    private var list: ArrayList<Event> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_gallery)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        rvEvents = findViewById(R.id.rv_events)
        rvEvents.setHasFixedSize(true)
        btnChangeLayout = findViewById(R.id.action_button_change_layout)
        btnChangeLayout.setOnClickListener(this)

        list.addAll(EventsData.listData)
        showRecyclerList()
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            androidx.appcompat.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList() {
        rvEvents.layoutManager = LinearLayoutManager(this)
        val listEventAdapter = ListEventAdapter(list, this)
        rvEvents.adapter = listEventAdapter
    }

    private fun showRecyclerGrid() {
        rvEvents.layoutManager = GridLayoutManager(this, 2)
        val gridEventAdapter = GridEventAdapter(list, this)
        rvEvents.adapter = gridEventAdapter
    }

    private fun showRecyclerCardview() {
        rvEvents.layoutManager = LinearLayoutManager(this)
        val cardViewEventAdapter = CardViewEventAdapter(list, this)
        rvEvents.adapter = cardViewEventAdapter
    }

    private fun setNextIcon() {
        when (btnChangeLayout.text) {
            "Grid" -> {
                showRecyclerGrid()
                btnChangeLayout.text = "CardView"
                btnChangeLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_view_sidebar_24_gray, 0, 0, 0)
            }
            "CardView" -> {
                showRecyclerCardview()
                btnChangeLayout.text = "List"
                btnChangeLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_view_module_24_gray, 0, 0, 0)
            }
            "List" -> {
                showRecyclerList()
                btnChangeLayout.text = "Grid"
                btnChangeLayout.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_view_list_24_gray, 0, 0, 0)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_button_change_layout -> {
                Log.d("MainActivity Gallery", "Action Button Change Layout Clicked")
                setNextIcon()
            }
        }
    }
}