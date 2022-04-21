package com.example.happylearningland

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.happylearningland.databinding.ActivityCheckEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class CheckEmailActivity : AppCompatActivity() {

    // ViewBinding
    private lateinit var binding: ActivityCheckEmailBinding
    private lateinit var auth: FirebaseAuth

    // ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_email)

        binding = ActivityCheckEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Conexión con la BD
        auth = FirebaseAuth.getInstance()

        // validar usuario
        val user = auth.currentUser

        // Configure progress dialog SignUp
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setMessage("Creando cuenta...")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnCheckEmail.setOnClickListener {
            val profileUpdates = userProfileChangeRequest {  }
            // show progress
            progressDialog.show()
            user!!.updateProfile(profileUpdates).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (user.isEmailVerified) {
                        Log.d(TAG, "createUserWithEmail:success")
                        // get current user
                        val firebaseUser = auth.currentUser
                        val email = firebaseUser!!.email
                        Toast.makeText(this, "Cuenta creada con correo $email", Toast.LENGTH_SHORT).show()

                        // Carga de pantalla
                        reload()
                    } else {
                        Toast.makeText(this, "Por favor verifique su correo.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                // sign up success
                progressDialog.dismiss()
                reload()
            } else {
                sendEmailVerification()
            }
        }
    }

    private fun sendEmailVerification() {
        // get user info
        val firebaseUser = auth.currentUser
        firebaseUser!!.sendEmailVerification().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Se envió un correo de verificación.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun reload() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

}