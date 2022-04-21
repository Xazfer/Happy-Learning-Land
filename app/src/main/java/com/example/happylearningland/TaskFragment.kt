package com.example.happylearningland

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentTaskBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class TaskFragment : Fragment(R.layout.fragment_task) {
    private lateinit var binding: FragmentTaskBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentTaskBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)

        binding.btnInicio.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Inicio")
            findNavController().navigate(R.id.action_nav_taskFragment_to_nav_mainScreenFragment, bundle)
        }
        // Reproduccion de audio
        binding.ayuda.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.ayudatareas)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.ayudatareas)
                mediaPlayer?.start()
            }
        }

        binding.tarea.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.realizartarea)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.realizartarea)
                mediaPlayer?.start()
            }
        }

        binding.dientes.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.lavardientes)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.lavardientes)
                mediaPlayer?.start()
            }
        }

        binding.cuarto.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.limpiarcuarto)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.limpiarcuarto)
                mediaPlayer?.start()
            }
        }

        binding.limpiar.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.limpiarcasa)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.limpiarcasa)
                mediaPlayer?.start()
            }
        }

        binding.basura.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.tirarbasura)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.tirarbasura)
                mediaPlayer?.start()
            }
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