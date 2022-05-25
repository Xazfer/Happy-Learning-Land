package com.myapp.happylearningland

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.myapp.happylearningland.databinding.ActivityAccountRecoveryBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountRecoveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountRecoveryBinding

    // Data
    private var emailRecover = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountRecoveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRecovery.setOnClickListener {
            validateEmail()
        }

    }

    private fun validateEmail() {
        // get data
        emailRecover = binding.emailRecovery.text.toString().trim()

        // validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(emailRecover).matches()) {
            // invalid email format
            binding.emailRecovery.error = "Formato de correo electrónico inválido"
        }else if (TextUtils.isEmpty(emailRecover)) {
            binding.emailRecovery.error = "Por favor, ingrese su correo"
        } else {
            sendRecovery(emailRecover)
        }
    }

    private fun sendRecovery(email : String) {
        Firebase.auth.sendPasswordResetEmail(emailRecover).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, Login::class.java)
                this.startActivity(intent)
            } else {
                Toast.makeText(this, "Ingrese un email de una cuenta autenticada.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}