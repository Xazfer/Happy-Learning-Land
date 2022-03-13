package com.example.happylearningland

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signUp.setOnClickListener {
            signUp.background = resources.getDrawable(R.drawable.switch_trcks,null)
            signUp.setTextColor(resources.getColor(R.color.textColor,null))
            signIn.background = null
            signUpLayout.visibility = View.VISIBLE
            signInLayout.visibility = View.GONE
            signIn.setTextColor(resources.getColor(R.color.pink,null))
        }
        signIn.setOnClickListener {
            signUp.background = null
            signUp.setTextColor(resources.getColor(R.color.pink, null))
            signIn.background = resources.getDrawable(R.drawable.switch_trcks,null)
            signUpLayout.visibility = View.GONE
            signInLayout.visibility = View.VISIBLE
            signIn.setTextColor(resources.getColor(R.color.textColor, null))
        }
        btnSignIn.setOnClickListener{
            startActivity(Intent(this@Login, MainActivity::class.java))
        }
    }
}