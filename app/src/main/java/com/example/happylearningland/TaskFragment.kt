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
import java.util.*

class TaskFragment : Fragment(R.layout.fragment_task) {
    private lateinit var binding: FragmentTaskBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: MutableList<String>
    private var uid = ""
    private lateinit var listTask: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentTaskBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        uid = auth.currentUser!!.uid

        getData(db)

        setupCheck()

        listTask = listOf("task1", "task2", "task3", "task4", "task5")

        binding.task1.setOnClickListener{
            eliminateTask("task1")
        }

        binding.task2.setOnClickListener{
            eliminateTask("task2")
        }

        binding.task3.setOnClickListener{
            eliminateTask("task3")
        }

        binding.task4.setOnClickListener{
            eliminateTask("task4")
        }

        binding.task5.setOnClickListener{
            eliminateTask("task5")
        }

        binding.taskFinished.setOnClickListener{
            finishTask()
        }

        binding.btnInicio.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Inicio")
            findNavController().navigate(R.id.action_nav_taskFragment_to_nav_mainScreenFragment, bundle)
        }

        //Reproduccion de audio
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

    private fun finishTask() {
        val date = Date()
        val coin = coins + 5
        Log.w("Fecha", "La fecha de almacenamiento $date y monedas $coin")
        updateCoins(coin)
        updateDate(date)
    }

    //Funcion eliminar tarea en la lista de la tarea y actualizar en BD
    private fun eliminateTask(tk: String) {
        val task = tasks
        val num = task.indexOf(tk)
        task.remove(tk)

        Log.w("Lista", "Nueva lista $task y dato eliminado $num")

        val id = uid
        db.child("player").child(id).get().addOnSuccessListener {
            if (it.value == null) {
                Log.w("texto inexistente", "datos no encontrados")
            } else {
                db.child("player").child(id).child("tasks").setValue(task)
                Log.w("texto existente", "datos encontrados ${it.value}")
            }
        }.addOnFailureListener {
            Log.w("texto inexistente", "datos no encontrados")
        }
    }

    //Función de obtener datos
    private fun getData(data : DatabaseReference) {
        val current = auth.currentUser
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val us = snapshot.child("player").child(current!!.uid).getValue<User>()
                imageCharacter = us!!.character
                coins = us.coins
                tasks = us.tasks.toMutableList()
                val fecha = us.date
                if(fecha == null){
                    setupCheck()
                    activeCheck(tasks)
                }else{
                    reactiveTask(fecha)
                }
                binding.coins.text = us.coins.toString()
                Log.w("data", "datos recuperados $us")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("data", "datos no recuperados", error.toException())
            }

        }
        data.addValueEventListener(listener)
    }

    private fun reactiveTask(date: Date) {
        val datenow = Date()
        val daynow = datenow.date
        val daysave = date.date
        if (daynow > daysave){
            val id = uid
            db.child("player").child(id).get().addOnSuccessListener {
                if (it.value == null) {
                    Log.w("texto inexistente", "datos no encontrados")
                } else {
                    db.child("player").child(id).child("date").setValue(null)
                    db.child("player").child(id).child("tasks").setValue( listTask)
                    Log.w("texto existente", "datos encontrados ${it.value}")
                }
            }.addOnFailureListener {
                Log.w("texto inexistente", "datos no encontrados")
            }
        }else{
            setupCheck()
        }
    }

    //Funcion activar checks dependiendo de las tareas disponibles
    private fun activeCheck(tk: List<String>){
        if(tk.isEmpty()){
            binding.taskFinished.isEnabled = true
            binding.taskFinished.isChecked = false
        }else{
            tk.forEach {
                when(it){
                    "task1" ->{
                        binding.task1.isChecked = false
                        binding.task1.isEnabled = true
                    }
                    "task2" ->{
                        binding.task2.isChecked = false
                        binding.task2.isEnabled = true
                    }
                    "task3" ->{
                        binding.task3.isChecked = false
                        binding.task3.isEnabled = true
                    }
                    "task4" ->{
                        binding.task4.isChecked = false
                        binding.task4.isEnabled = true
                    }
                    "task5" ->{
                        binding.task5.isChecked = false
                        binding.task5.isEnabled = true
                    }
                }
            }
        }

    }

    //Funcion inicializar los checks
    private fun setupCheck(){
        binding.task1.isChecked = true
        binding.task1.isEnabled = false

        binding.task2.isChecked = true
        binding.task2.isEnabled = false

        binding.task3.isChecked = true
        binding.task3.isEnabled = false

        binding.task4.isChecked = true
        binding.task4.isEnabled = false

        binding.task5.isChecked = true
        binding.task5.isEnabled = false

        binding.taskFinished.isChecked = true
        binding.taskFinished.isEnabled = false
    }

    //Funcion actualización de coins en BD
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

    private fun updateDate(date : Date) {
        val id = uid
        db.child("player").child(id).get().addOnSuccessListener {
            if (it.value == null) {
                Log.w("texto inexistente", "datos no encontrados")
            } else {
                db.child("player").child(id).child("date").setValue(date)
                Log.w("texto existente", "datos encontrados ${it.value}")
            }
        }.addOnFailureListener {
            Log.w("texto inexistente", "datos no encontrados")
        }
    }

}