package com.example.happylearningland

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
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

        // Ocultar barra de navegación y status bar
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

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

        // Botón de inicio
        btnInicio.setOnClickListener {
            startActivity(Intent(this, CapsuleFragment::class.java))
        }

        // Botón de Character 1
        btnCharacter1.setOnClickListener {
            val intent = Intent(this, CapsuleFragment::class.java)

            // send image
            //intent.putExtra("character1", R.drawable.character_11)

            // send new screen
            startActivity(intent)
            finish()
        }

        // Botón de Character 2
        btnCharacter2.setOnClickListener {
            val intent = Intent(this, CapsuleFragment::class.java)
            // send image
            //intent.putExtra("character2", R.drawable.character_12)

            // send new screen
            startActivity(intent)
            finish()
        }

        // Botón de Character 3
        btnCharacter3.setOnClickListener {
            val intent = Intent(this, CapsuleFragment::class.java)

            // send image
            //intent.putExtra("character3", R.drawable.character_21)

            // send new screen
            startActivity(intent)
            finish()
        }

        // Botón de Character 4
        btnCharacter4.setOnClickListener {
            val intent = Intent(this, CapsuleFragment::class.java)

            // send image
            //intent.putExtra("character4", R.drawable.character_22)

            // send new screen
            startActivity(intent)
            finish()
        }


    }

    private fun checkUser() {
        // check user is logged in or not
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
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