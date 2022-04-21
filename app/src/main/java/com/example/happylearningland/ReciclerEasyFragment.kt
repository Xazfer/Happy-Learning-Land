package com.example.happylearningland

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.happylearningland.databinding.FragmentReciclerEasyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class ReciclerEasyFragment : Fragment(R.layout.fragment_recicler_easy) {

    private lateinit var binding: FragmentReciclerEasyBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>

    private var txtTiempo: EditText? = null
    private var tvCuentaAtras: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReciclerEasyBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)

        txtTiempo = binding.txtTiempo.findViewById(R.id.txtTiempo)
        tvCuentaAtras = binding.tvCuentaAtras.findViewById(R.id.tvCuentaAtras)

    }
    fun play(view: View) {
        var tiempoSegundos = txtTiempo?.text.toString().toLong()
        var tiempoMilisegundos = tiempoSegundos * 1000
        object : CountDownTimer(tiempoMilisegundos, 1000) {
            override fun onFinish() {

            }
            override fun onTick(millisUntilFinished: Long) {
                val tiempoSegundos = (millisUntilFinished / 1000).toInt() + 1
                tvCuentaAtras?.text = tiempoSegundos.toString().padStart(2, '0')
            }
        }.start()
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


