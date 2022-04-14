package com.example.happylearningland

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.util.PatternsCompat
import com.example.happylearningland.databinding.ActivityLoginBinding
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
import java.util.regex.Pattern.compile

class Login : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)

    // ViewBinding
    private lateinit var binding: ActivityLoginBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    // Firebase Analytics
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    // ProgressDialog
    private lateinit var progressDialog:ProgressDialog
    private lateinit var progressDialog2:ProgressDialog

    // constants
    private companion object {
        private const val RC_SIGN_IN = 9001
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    // Data
    private var emailV = ""
    private var passwordV = ""
    private var emailsV = ""
    private var passwordsV = ""
    private var passwordsV2 = ""
    private var nameV = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_login)

        // Configure progress dialog SignIn
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setMessage("Iniciando sesión...")
        progressDialog.setCanceledOnTouchOutside(false)

        // Configure progress dialog SignUp
        progressDialog2 = ProgressDialog(this)
        progressDialog2.setTitle("Espere por favor")
        progressDialog2.setMessage("Creando cuenta...")
        progressDialog2.setCanceledOnTouchOutside(false)

        // Conexión con la BD
        auth = FirebaseAuth.getInstance()

        // Revisión de si el usuario existe o no
        checkUser()

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

        // Google Analytics
        // Obtain the FirebaseAnalytics instance.
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase completa")
        analytics.logEvent("MainActivity", bundle)

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

    private fun validateDataSignIn() {
        // get data
        emailV = binding.email.text.toString().trim()
        passwordV = binding.password.text.toString().trim()

        // validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(emailV).matches()) {
            // invalid email format
            binding.email.error = "Formato de correo electrónico inválido"
        }
        else if (TextUtils.isEmpty(emailV)) {
            binding.email.error = "Por favor, ingrese su correo"
        }
        else if (TextUtils.isEmpty(passwordV)) {
            // no password entered
            binding.password.error = "Por favor, ingrese su contraseña"
        }
        else if (passwordV.length < 6) {
            // password length
            binding.password.error = "La contraseña debe tener al menos 6 caracteres"
        }
        else {
            firebaseSignIn()
        }
    }

    private fun validateDataSignUp() {
        // get data
        nameV = binding.nombre.text.toString().trim()
        emailsV = binding.emails.text.toString().trim()
        passwordsV = binding.passwords.text.toString().trim()
        passwordsV2 = binding.passwords2.text.toString().trim()

        // validate data
        if (TextUtils.isEmpty(nameV)) {
            // name isn't entered
            binding.nombre.error = "Por favor, ingrese su nombre"
        }
        else if (TextUtils.isEmpty(emailsV)) {
            binding.emails.error = "Por favor, ingrese su correo"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailsV).matches()) {
            // invalid email format
            binding.emails.error = "Formato de correo inválido"
        }
        else if (TextUtils.isEmpty(passwordsV)) {
            // no password entered
            binding.passwords.error = "Por favor, ingrese su contraseña"
        }
        else if (passwordsV.length < 6) {
            // password length
            binding.passwords.error = "La contraseña debe tener al menos 6 caracteres"
        }
        else if (TextUtils.isEmpty(passwordsV2)) {
            // no password entered
            binding.passwords2.error = "Por favor, ingrese su contraseña"
        }
        else if (passwordsV2.length < 6) {
            // password length
            binding.passwords2.error = "La contraseña debe tener al menos 6 caracteres"
        }
        else if ((passwordsV != passwordsV2) && (passwordsV2 != passwordsV)) {
            // password coincidate
            binding.passwords.error = "La contraseña no coincide"
            binding.passwords2.error = "La contraseña no coincide"
        }
        else {
            firebaseSignUp()
        }
    }

    private fun firebaseSignIn() {
        // show progress
        progressDialog.show()
        // sign in
        auth.signInWithEmailAndPassword(emailV, passwordV).addOnSuccessListener {
            // sign in success
            progressDialog.dismiss()
            // get user info
            val firebaseUser = auth.currentUser
            val email = firebaseUser!!.email
            Toast.makeText(this, "Conectado como $email", Toast.LENGTH_SHORT).show()
            // open MainActivity
            startActivity(Intent(this, MainActivity::class.java)) // add @Login
            finish()
        }.addOnFailureListener { e ->
            // sign in failed
            progressDialog.dismiss()
            Toast.makeText(this, "Error de inicio de sesión debido a ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun firebaseSignUp() {
        // show progress
        progressDialog2.show()
        // sign up
        auth.createUserWithEmailAndPassword(nameV, emailsV).addOnSuccessListener {
            // sign up success
            progressDialog2.dismiss()
            // get current user
            val firebaseUser = auth.currentUser
            val email = firebaseUser!!.email
            Toast.makeText(this, "Cuenta creada con correo $email", Toast.LENGTH_SHORT).show()

            // open MainActivity
            startActivity(Intent(this, MainActivity::class.java)) // add @Login
            finish()
        }.addOnFailureListener { e ->
            // sign up failed
            progressDialog2.dismiss()
            Toast.makeText(this, "El registro falló debido a ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkUser() {
        // check if user is logged in or not
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            // user is already logged in
            // start MainActivity
            startActivity(Intent(this@Login, MainActivity::class.java)) // add @Login
            finish()
        }
    }

    // Setup
    // Función de autenticación de correo y contraseña
    private fun setUp() {
        // Pantalla Sign In sin presionar botón
        btnSignIn.setOnClickListener {
            // before loggin in, validate data
            validateDataSignIn()

            /*if ((email.text!!.isNotEmpty()) && (password.text!!.isNotEmpty())) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful) {
                        //signIn() // add
                        showMain(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }*/
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
                // before loggin in, validate data
                validateDataSignUp()

                if ((email.text!!.isNotEmpty()) && (password.text!!.isNotEmpty())) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful) {
                            //signIn() // add
                            showMain(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
                }
            }
        }

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
                // before loggin in, validate data
                validateDataSignIn()

                if ((email.text!!.isNotEmpty()) && (password.text!!.isNotEmpty())) {
                    auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful) {
                            //signIn() // add
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
            /*try {
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
            }*/
            try {
                val account = task.getResult(ApiException::class.java)
                // Google Sign In was successful, authenticate with Firebase
                firebaseAuthWithGoogleAccount(account)

                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    //add
                    /*val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("full name", account?.displayName)
                        putExtra("email", account?.email)
                    }*/

                    auth.signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showMain(account.email ?: "", ProviderType.GOOGLE)
                            signIn() // add
                        } else {
                            //signIn() // add
                            showAlert()
                        }
                    }
                }
            } catch (e: ApiException) {
                //Log.w("Error", "Google sign in failed", e) // add
                // Google Sign In failed, update UI appropriately
                showAlert()
            }
        }
    }
    // [END onactivityresult]

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?){
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        auth.signInWithCredential(credential).addOnSuccessListener { authResult -> Log.d(TAG, "firebaseAuthWithGoogleAccount: Conectado")
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
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Cuenta creada... \n$email")
                Toast.makeText(this@Login, "Conectado... \n$email", Toast.LENGTH_SHORT).show()
            }
            else {
                // existing user - LoggedIn
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Usuario existente... \n$email")
                Toast.makeText(this@Login, "Conectado... \n$email", Toast.LENGTH_SHORT).show()
            }

            // start MainActivity
            startActivity(Intent(this@Login, MainActivity::class.java))
            finish()
        }
            .addOnFailureListener { e ->
                // Login failed
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Error de inicio de sesión debido a ${e.message}")
                Toast.makeText(this@Login, "Error de inicio de sesión debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // [START signin]
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signin]

}