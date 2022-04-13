package com.example.happylearningland

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    // Firebase Analytics
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    // constants
    private companion object {
        private const val RC_SIGN_IN = 9001
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Conexión con la BD
        auth = FirebaseAuth.getInstance()

        // Revisión de si el usuario existe o no
        checkUser()

        // Google Analytics
        // Obtain the FirebaseAnalytics instance.
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase completa")
        analytics.logEvent("MainActibity", bundle)

        btnSignIn.setOnClickListener{
            startActivity(Intent(this@Login, MainActivity::class.java))
        }

        // Conexión de Gmail
        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclient_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // [END config_signin]

        val btnGoogle = findViewById<ImageView>(R.id.google_btn)
        btnGoogle.setOnClickListener{
            signIn()
        }

        // Configuración de validación de email y contraseña
        // Setup
        setUp()
        // Session
        session()

    }

    private fun checkUser() {
        // check if user is logged in or not
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            // user is already logged in
            // start MainActivity
            startActivity(Intent(this@Login, MainActivity::class.java))
            finish()
        }

    }

    override fun onStart() {
        super.onStart()

        loginLayout.visibility = View.VISIBLE
    }

    // Session
    // Función para recuperar si se tiene guardado un email y provider
    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            loginLayout.visibility = View.INVISIBLE
            showMain(email, ProviderType.valueOf(provider))
        }
    }

    // Setup
    // Función de autenticación de correo y contraseña
    private fun setUp() {
        // Inicio de sesión
        signIn.setOnClickListener {
            // Configuración del switch de botones del login apartado del SignIn
            signUp.background = null
            signUp.setTextColor(resources.getColor(R.color.pink, null))
            signIn.background = resources.getDrawable(R.drawable.switch_trcks,null)
            signUpLayout.visibility = View.GONE
            signInLayout.visibility = View.VISIBLE
            signIn.setTextColor(resources.getColor(R.color.textColor, null))

            btnSignIn.setOnClickListener {
                if (email.text!!.isNotEmpty() && password.text!!.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful) {
                            showMain(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
                }
            }
        }

        // Registro
        signUp.setOnClickListener {
            // Configuración del switch de botones del login apartado del SignUp
            signUp.background = resources.getDrawable(R.drawable.switch_trcks,null)
            signUp.setTextColor(resources.getColor(R.color.textColor,null))
            signIn.background = null
            signUpLayout.visibility = View.VISIBLE
            signInLayout.visibility = View.GONE
            signIn.setTextColor(resources.getColor(R.color.pink,null))

            btnSignIn.setOnClickListener {
                if (email.text!!.isNotEmpty() && password.text!!.isNotEmpty()) {
                    auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful) {
                            showMain(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
                }
            }
        }
    }

    // showAlert
    // Alerta de autenticación del usuario
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al autenticar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // showMain
    // Función para pasarle el email y contraseña autenticado
    private fun showMain(email: String, provider: ProviderType) {
        val mainIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(mainIntent)
    }

    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: Google SignIn intent result")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                // Google Sign In was successful, authenticate with Firebase
                firebaseAuthWithGoogleAccount(account)
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("full name", account?.displayName)
                    putExtra("email", account?.email)
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Error", "Google sign in failed", e)
            }
            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    auth.signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showMain(account.email ?: "", ProviderType.GOOGLE)
                        } else {
                            showAlert()
                        }
                    }
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                showAlert()
            }
        }
    }
    // [END onactivityresult]

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?){
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        auth.signInWithCredential(credential).addOnSuccessListener { authResult -> Log.d(TAG, "firebaseAuthWithGoogleAccount: LoggedIn")
            // get loggedIn user
            val firebaseUser = auth.currentUser
            // get user info
            val uid = firebaseUser!!.uid
            val email = firebaseUser.email

            Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
            Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")

            // check if user is new or existing
            if (authResult.additionalUserInfo!!.isNewUser) {
                // user is new - Account created
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Account created... \n$email")
                Toast.makeText(this@Login, "LoggedIn... \n$email", Toast.LENGTH_SHORT).show()
            }
            else {
                // existing user - LoggedIn
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing user... \n$email")
                Toast.makeText(this@Login, "LoggedIn... \n$email", Toast.LENGTH_SHORT).show()
            }

            // start MainActivity
            startActivity(Intent(this@Login, MainActivity::class.java))
            finish()
        }
            .addOnFailureListener { e ->
                // Login failed
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin Failed due to ${e.message}")
                Toast.makeText(this@Login, "Loggin Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // [START signin]
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signin]
}