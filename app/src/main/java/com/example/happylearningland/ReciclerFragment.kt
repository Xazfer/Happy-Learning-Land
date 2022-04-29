package com.example.happylearningland

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentReciclerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ReciclerFragment : Fragment(R.layout.fragment_recicler) {
    private lateinit var binding: FragmentReciclerBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>

    private var nombre:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentReciclerBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)

        binding.btnInicio.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Inicio")
            findNavController().navigate(R.id.action_nav_reciclerFragment_to_nav_mainScreenFragment, bundle)
        }
        binding.instrucciones.setOnClickListener {
            mediaPlayer= MediaPlayer.create(context, R.raw.instruccionesreciclaje)
            mediaPlayer?.start()
        }
        binding.btnTutorial.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Tutorial")
            findNavController().navigate(R.id.action_nav_reciclerFragment_to_reciclerTutorialFragment, bundle)
        }
        binding.btnEasy.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Fácil")
            findNavController().navigate(R.id.action_nav_reciclerFragment_to_reciclerEasyFragment, bundle)
        }
        binding.btnHard.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Difícil")
            findNavController().navigate(R.id.action_nav_reciclerFragment_to_reciclerHardFragment, bundle)
        }
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
                Log.w("data", "datos recuperados $us")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("data", "datos no recuperados", error.toException())
            }

        }
        data.addValueEventListener(listener)
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