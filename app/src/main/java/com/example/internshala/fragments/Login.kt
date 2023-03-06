package com.example.internshala.fragments

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.internshala.R



class Login : Fragment() {
    private lateinit var lBtn: Button
    private lateinit var sBtn: Button
    private lateinit var lUsername: EditText
    private lateinit var lpass: EditText
    private lateinit var preferences: SharedPreferences
    private lateinit var lpreferences:SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var username=""
    private val msg = ""

    override fun onAttach(context: Context) {
        preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        editor = preferences.edit()
        lpreferences=context.getSharedPreferences("userFile",Context.MODE_PRIVATE)
        editor=lpreferences.edit()
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ( activity as AppCompatActivity ).supportActionBar?.title="Login"
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        lUsername = view.findViewById(R.id.lUsername)
        lpass = view.findViewById(R.id.lpass)
        lBtn = view.findViewById(R.id.lBTN)
        sBtn = view.findViewById(R.id.rBTN)

        lBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val lUserName = lUsername.text.toString()
            val lPassWord = lpass.text.toString()
            username = preferences.getString("userName", null).toString()
            val password = preferences.getString("password", null)
            val msg: String

            if (username.equals(lUserName) && password.equals(lPassWord)) {
                msg = "Login Successfully"
                setup()
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_container, Workshop()).commit()
            } else {
                msg = "Login Failed"
            }

            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
        sBtn.setOnClickListener {
            requireActivity()!!.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_container, Signup()).commit()
        }


        return view
    }
    private fun setup() {
        editor.putString("userName", username)
        editor.apply()
        Toast.makeText(activity,"User Registered",Toast.LENGTH_SHORT).show()

    }
}

