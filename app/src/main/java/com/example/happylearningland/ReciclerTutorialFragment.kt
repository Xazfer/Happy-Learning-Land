package com.example.happylearningland

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentReciclerTutorialBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ReciclerTutorialFragment : Fragment(R.layout.fragment_recicler_tutorial) {
    private lateinit var binding: FragmentReciclerTutorialBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private var correct = 0
    private var incorrect = 0

    private lateinit var tasks: List<String>

    private var contador = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReciclerTutorialBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)
        imagesTrash()
        binding.btnInorganic.setOnClickListener {
            when(contador){
                1-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                2-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                3-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                4-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                5-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                6-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                    if (contador == 7) {
                        back()
                    }
                }
            }
        }
        binding.btnOrganic.setOnClickListener {
            when(contador){
                1-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                2-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                3-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                4-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                5-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                6-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                    if (contador == 7) {
                        back()
                    }
                }
            }
        }
    }

    //funcion de vuleta a niveles
    private fun back(){
        findNavController().navigate(R.id.action_reciclerTutorialFragment_to_nav_reciclerFragment)
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
    fun imagesTrash(){
        when(contador){
            1-> binding.trash.setImageResource(R.drawable.lata3)
            2-> binding.trash.setImageResource(R.drawable.cascaradehuevo)
            3-> binding.trash.setImageResource(R.drawable.lataatum)
            4-> binding.trash.setImageResource(R.drawable.lataatun2)
            5-> binding.trash.setImageResource(R.drawable.cascaradehuevo2)
            6-> binding.trash.setImageResource(R.drawable.cascaradeplatano)
        }
    }

}