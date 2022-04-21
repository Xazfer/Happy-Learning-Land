package com.example.happylearningland

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.happylearningland.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*

enum class ProviderType {
    BASIC,
    GOOGLE
}

class MainActivity : AppCompatActivity() {

    // FirebaseAuth
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var listTask: List<String>

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        db = Firebase.database.reference
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference
        checkUser()

        // Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        val uid = auth.currentUser!!.uid.toString()
        listTask = listOf("task1", "task2", "task3", "task4", "task5")
        setUp(email ?: "", provider ?: "")

        // Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        uploadCharacter()


        // Obtener imágenes
        // Botón de Character 1
        binding.btnCharacter1.setOnClickListener {
            updateDatabase("characters/character_11.png", 0, uid, listTask)
            val intent = Intent(this, CapsuleFragment::class.java)
            // send new screen
            startActivity(intent)
            finish()
        }

        // Botón de Character 2
        binding.btnCharacter2.setOnClickListener {
            updateDatabase("characters/character_12.png", 0, uid, listTask)
            val intent = Intent(this, CapsuleFragment::class.java)
            // send new screen
            startActivity(intent)
            finish()
        }

        // Botón de Character 3
        binding.btnCharacter3.setOnClickListener {
            updateDatabase("characters/character_21.png", 0, uid, listTask)
            val intent = Intent(this, CapsuleFragment::class.java)
            // send new screen
            startActivity(intent)
            finish()
        }

        // Botón de Character 4
        binding.btnCharacter4.setOnClickListener {
            updateDatabase("characters/character_22.png", 0, uid, listTask)
            val intent = Intent(this, CapsuleFragment::class.java)
            // send new screen
            startActivity(intent)
            finish()
        }
    }

    // Función de cargar datos DB
    private fun updateDatabase(character : String, coins : Int, email : String,  tasks : List<String>) {
        val player = User(character, coins, tasks)
        val id = email
        db.child("player").child(id).get().addOnSuccessListener {
            if (it.value == null) {
                Log.w("texto inexistente", "datos no encontrados")
                db.child("player").child(id).setValue(player)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Datos subidos correctamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Datos no subidos", Toast.LENGTH_SHORT).show()
                    }
            } else {
                db.child("player").child(id).child("character").setValue(character)
                Log.w("texto existente", "datos encontrados ${it.value}")
            }
        }.addOnFailureListener {
            Log.w("texto inexistente", "datos no encontrados")
        }
    }

    // Función de subir personaje a la BD
    private fun uploadCharacter() {
        var path1 = storageReference.child("characters/character_11.png")
        var path2 = storageReference.child("characters/character_12.png")
        var path3 = storageReference.child("characters/character_21.png")
        var path4 = storageReference.child("characters/character_22.png")
        var localFile1 = File.createTempFile("tempImage1", "png")
        var localFile2 = File.createTempFile("tempImage2", "png")
        var localFile3 = File.createTempFile("tempImage3", "png")
        var localFile4 = File.createTempFile("tempImage4", "png")

        path1.getFile(localFile1).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile1.absolutePath)
            binding.btnCharacter1.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Toast.makeText(this, "No se pudo recuperar la imagen", Toast.LENGTH_SHORT).show()
        }

        path2.getFile(localFile2).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile2.absolutePath)
            binding.btnCharacter2.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Toast.makeText(this, "No se pudo recuperar la imagen", Toast.LENGTH_SHORT).show()
        }

        path3.getFile(localFile3).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile3.absolutePath)
            binding.btnCharacter3.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Toast.makeText(this, "No se pudo recuperar la imagen", Toast.LENGTH_SHORT).show()
        }

        path4.getFile(localFile4).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile4.absolutePath)
            binding.btnCharacter4.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Toast.makeText(this, "No se pudo recuperar la imagen", Toast.LENGTH_SHORT).show()
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