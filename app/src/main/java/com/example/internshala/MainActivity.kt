package com.example.internshala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.internshala.fragments.Dashboard
import com.example.internshala.fragments.Signup
import com.example.internshala.fragments.Workshop

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          supportFragmentManager.beginTransaction().replace(R.id.nav_container,Workshop()).commit()
    }
}