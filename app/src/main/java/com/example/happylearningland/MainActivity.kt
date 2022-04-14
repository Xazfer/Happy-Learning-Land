package com.example.happylearningland

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.happylearningland.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

enum class ProviderType {
    BASIC,
    GOOGLE
}

class MainActivity : AppCompatActivity() {

    // FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init firebase auth
        auth = FirebaseAuth.getInstance()
        checkUser()

        // Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setUp(email ?: "", provider ?: "")

        // Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun checkUser() {
        // check user is logged in or not
        val firebaseUser = auth.currentUser
        if (auth != null) {
            // user not null, user is logged in, get user info
            /*val email = firebaseUser.email
            Toast.makeText(this, "Conectado como $email", Toast.LENGTH_SHORT).show()*/
        }
        else {
            // user is null, user is not loggedin, goto login activity
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    private fun setUp(email: String, provider: String) {

        /*
        // Borrado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()
        */
    }
}