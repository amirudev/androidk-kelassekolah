package com.dicoding.kelassekolah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView

class StudentTaskActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var cvTaskItem: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_task)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        cvTaskItem = findViewById(R.id.cv_task_item)
        cvTaskItem.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cv_task_item -> {
                val studentTaskActivityIntent = Intent(this@StudentTaskActivity, TaskDetail::class.java)
                startActivity(studentTaskActivityIntent)
            }
        }
    }
}