package com.example.happylearningland

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentAlimentacionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class AlimentacionFragment : Fragment(R.layout.fragment_alimentacion) {

    private lateinit var binding: FragmentAlimentacionBinding
    private lateinit var db: DatabaseReference
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>

    private var nombre:String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentAlimentacionBinding.bind(view)

        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        // Recuperación de imagen del personaje que fue seleccionado
        getData(db)

    }

    // Función de obtener datos
    private fun getData(data : DatabaseReference) {
        val current = auth.currentUser
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val us = snapshot.child("player").child(current!!.uid).getValue<User>()
                imageCharacter = us!!.character
                coins = us.coins
                tasks = us.tasks
                binding.coins.text = us.coins.toString()
                getImage(imageCharacter)
                Log.w("data", "datos recuperados $us")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("data", "datos no recuperados", error.toException())
            }

        }
        data.addValueEventListener(listener)
    }

    // Función de obtención de personaje para colocarlo en su contenedor
    private fun getImage(path : String) {
        var pathImage = storageReference.child(path)
        var localFileImage = File.createTempFile("tempImage", "png")

        pathImage.getFile(localFileImage).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFileImage.absolutePath)
            binding.containerCharacter.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Log.w("image", "imagen no recuperados")
        }
    }

    /*
    // Actualización de coins
    // Función de subir datos DB
    private fun updateCoins(coins : Int, email: String) {
        val id = email
        db.child("player").child(id).get().addOnSuccessListener {
            if (it.value == null) {
                Log.w("texto inexistente", "datos no encontrados")
            } else {
                db.child("player").child(id).child("coins").setValue(coins)
                Log.w("texto existente", "datos encontrados ${it.value}")
            }
        }.addOnFailureListener {
            Log.w("texto inexistente", "datos no encontrados")
        }
    }

     */
}