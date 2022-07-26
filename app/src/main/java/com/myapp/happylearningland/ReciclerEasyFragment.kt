package com.myapp.happylearningland

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.myapp.happylearningland.databinding.FragmentReciclerEasyBinding
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
    private var correct = 0
    private var incorrect = 0
    private var uid = ""
    private lateinit var tasks: List<String>

    private var tvCuentaAtras: TextView? = null
    private var contador = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReciclerEasyBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)
        uid = auth.currentUser!!.uid

        play()
        imagesTrash()

        binding.btnInicio.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina de configuración")
            findNavController().navigate(R.id.action_reciclerEasyFragment_to_nav_reciclerFragment, bundle)
        }

        binding.btnPlastic.setOnClickListener {
            when(contador){
                1-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                2-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                3-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                4-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                5-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                6-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                7-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                8-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                9-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                    if (contador == 10) {
                        val alertDialog: AlertDialog? = activity?.let {
                            val builder = AlertDialog.Builder(it)
                            builder.apply {
                                setTitle("¡¡Felicidades!!")
                                setMessage("Obtuviste ${correct}/10 aciertos. Tus errores fueron: ${incorrect +1}")
                                setPositiveButton("Aceptar",
                                    DialogInterface.OnClickListener{ dialog, id ->
                                        val monedas = coins +2
                                        updateCoins(monedas)
                                        back()
                                    })
                            }
                            builder.create()
                        }
                        alertDialog?.show()
                    }
                }
            }
        }
        binding.btnPaper.setOnClickListener {
            when(contador){
                1-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                2-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                3-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                4-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                5-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                6-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                7-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                8-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                9-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                    if (contador == 10) {
                        val alertDialog: AlertDialog? = activity?.let {
                            val builder = AlertDialog.Builder(it)
                            builder.apply {
                                setTitle("¡¡Felicidades!!")
                                setMessage("Obtuviste ${correct}/10 aciertos. Tus errores fueron: ${incorrect +1}")
                                setPositiveButton("Aceptar",
                                    DialogInterface.OnClickListener{ dialog, id ->
                                        val monedas = coins +2
                                        updateCoins(monedas)
                                        back()
                                    })
                            }
                            builder.create()
                        }
                        alertDialog?.show()
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
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                2-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                3-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                4-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                5-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                6-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                7-> {
                    Log.e("RevisionAnswer", "CORRECTO AUMENTANDO 1")
                    contador += 1
                    correct += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.correctanswer)
                    mediaPlayer?.start()
                }
                8-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                }
                9-> {
                    Log.e("RevisionAnswer", "INCORRECTO AUMENTANDO 1")
                    contador += 1
                    incorrect += 1
                    binding.counterCorrects.text = correct.toString()
                    binding.counterQuestion.text = contador.toString()
                    imagesTrash()
                    mediaPlayer= MediaPlayer.create(context, R.raw.wronganswer)
                    mediaPlayer?.start()
                    if (contador == 10) {
                        val alertDialog: AlertDialog? = activity?.let {
                            val builder = AlertDialog.Builder(it)
                            builder.apply {
                                setTitle("¡¡Felicidades!!")
                                setMessage("Obtuviste ${correct}/10 aciertos. Tus errores fueron: ${incorrect +1}")
                                setPositiveButton("Aceptar",
                                    DialogInterface.OnClickListener{ dialog, id ->
                                        val monedas = coins +2
                                        updateCoins(monedas)
                                        back()
                                    })
                            }
                            builder.create()
                        }
                        alertDialog?.show()
                    }
                }
            }
        }
    }
    //funcion de vuleta a niveles
    private fun back(){
        findNavController().navigate(R.id.action_reciclerEasyFragment_to_nav_reciclerFragment)
    }
    //cronometro a 10 seg
    fun play(){
        val tiempo = 10
        tvCuentaAtras = binding.tvCuentaAtras.findViewById(R.id.tvCuentaAtras)
        val tiempoSegundos = tiempo.toLong()
        val tiempoMilisegundos = tiempoSegundos * 1000

        object : CountDownTimer(tiempoMilisegundos, 1000) {
            override fun onFinish() {
                //Recursividad
                play()
                contador += 1
                imagesTrash()
                incorrect += 1
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

    fun imagesTrash(){
        when(contador){
            1-> binding.trash.setImageResource(R.drawable.botellaplastico)
            2-> binding.trash.setImageResource(R.drawable.botella2)
            3-> binding.trash.setImageResource(R.drawable.caja)
            4-> binding.trash.setImageResource(R.drawable.cascaradehuevo)
            5-> binding.trash.setImageResource(R.drawable.cascaradehuevo2)
            6-> binding.trash.setImageResource(R.drawable.caja2)
            7-> binding.trash.setImageResource(R.drawable.cascaradeplatano)
            8-> binding.trash.setImageResource(R.drawable.cajajuego)
            9-> binding.trash.setImageResource(R.drawable.bolsaplastica)
        }
    }


    // Actualización de coins
    // Función de subir datos DB
    private fun updateCoins(coins : Int) {
        val id = uid
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

}


