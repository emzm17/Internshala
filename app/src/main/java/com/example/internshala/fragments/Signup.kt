package com.example.internshala.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.internshala.R


class Signup : Fragment() {
    private lateinit var rEmail: EditText
    private lateinit var rConfirmPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var rUserName: EditText
    private lateinit var rPassword: EditText
    private var username = ""
    private var password = ""
    private var email = ""
    private var cPassword = ""
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    override fun onAttach(context: Context) {
        preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        editor = preferences.edit()
        super.onAttach(context)
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ( activity as AppCompatActivity).supportActionBar?.title="SignUp"
       val view=inflater.inflate(R.layout.fragment_signup, container, false)
        rUserName = view.findViewById(R.id.rUsername)
        rPassword = view.findViewById(R.id.rPass)
        rEmail = view.findViewById(R.id.rEmail)
        rConfirmPassword = view.findViewById(R.id.rConPass)
        buttonRegister = view.findViewById(R.id.register)


          buttonRegister.setOnClickListener {
          username = rUserName.text.toString()
          password = rPassword.text.toString()
          cPassword = rConfirmPassword.text.toString()
          email = rEmail.text.toString()

          if (username.isEmpty() || email.isEmpty() || password.isEmpty() || cPassword.isEmpty()) {
             Toast.makeText(activity,"All fields are required",Toast.LENGTH_LONG).show()
          } else if (password != cPassword) {
              Toast.makeText(activity,"Password didn't match",Toast.LENGTH_LONG).show()
          } else if (password.length < 7) {
              Toast.makeText(activity, "Password is too short", Toast.LENGTH_LONG).show()
          }
          else{
               setup()
               activity?.supportFragmentManager!!.beginTransaction().replace(R.id.nav_container,Login()).commit()
          }

      }

        return view
    }

    private fun setup() {
        editor.putString("userName", username)
        editor.putString("password", password)
        editor.apply()

        Toast.makeText(activity,"User Registered",Toast.LENGTH_SHORT).show()

    }


}