package com.abs.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: MaterialToolbar
    lateinit var addition : AppCompatButton
    lateinit var subtraction : AppCompatButton
    lateinit var multi : AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            Toast.makeText(applicationContext, "sry there is no menu yet", Toast.LENGTH_SHORT).show()
        }

        addition =findViewById(R.id.buttonAdd)
        subtraction= findViewById(R.id.buttonSub)
        multi = findViewById(R.id.buttonMulti)

        addition.setOnClickListener {
            val intent = Intent(this@MainActivity , GameActivity::class.java)
            startActivity(intent)
        }

        subtraction.setOnClickListener {
            val intent = Intent(this@MainActivity , SubActivity::class.java)
            startActivity(intent)
        }

        multi.setOnClickListener {
            val intent = Intent(this@MainActivity , MultiActivity::class.java)
            startActivity(intent)
        }


    }
}