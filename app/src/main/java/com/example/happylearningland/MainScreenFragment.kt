package com.example.happylearningland

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentMainScreenBinding
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

//variable para sonido de fondo
var mediaPlayer: MediaPlayer? = null

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var db: DatabaseReference
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>

    //variable para recuperacion de datos

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bindeo de elementos del fragment
        binding = FragmentMainScreenBinding.bind(view)

        // Botón de configuración de audio
        binding.btnSettings.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina de configuración")
            findNavController().navigate(R.id.action_nav_mainScreenFragment_to_bottomSheetSettings, bundle)
        }

        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        // Recuperación de imagen del personaje que fue seleccionado
        getData(db)

        //Reproducion de sonido en loop
        if(mediaPlayer?.isPlaying == true){
            mediaPlayer?.release()
            mediaPlayer = null
            mediaPlayer= MediaPlayer.create(context, R.raw.gamesample)
            mediaPlayer?.start()
            mediaPlayer?.isLooping = true
        }else{
            mediaPlayer= MediaPlayer.create(context, R.raw.gamesample)
            mediaPlayer?.start()
            mediaPlayer?.isLooping = true
        }

        binding.btnTask.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina tareas")

            findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_taskFragment, bundle)
        }

        //Navegacion a los fragments
        binding.bottomNavigation.setOnItemSelectedListener {
                item ->
            when(item.itemId) {
                R.id.nav_lettersFragment  -> {
                    // Respond to navigation item 1 click
                    val bundle = bundleOf("texto" to "Pagina letras")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_lettersFragment, bundle)
                    true
                }
                R.id.nav_robotFragment -> {
                    // Respond to navigation item 2 click
                    val bundle = bundleOf("texto" to "Pagina robot")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_robotFragment, bundle)
                    true
                }
                R.id.nav_shopFragment -> {
                    // Respond to navigation item 3 click
                    val bundle = bundleOf("texto" to "Pagina tienda")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_shopFragment, bundle)
                    true
                }
                R.id.nav_alimentacionFragment -> {
                    // Respond to navigation item 4 click
                    val bundle = bundleOf("texto" to "Pagina alimentación")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_alimentacionFragment, bundle)
                    true
                }
                R.id.nav_reciclerFragment -> {
                    // Respond to navigation item 5 click
                    val bundle = bundleOf("texto" to "Pagina reciclaje")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_reciclerFragment, bundle)
                    true
                }
                else -> false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
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
                binding.counterTasks.text = us.tasks.size.toString()
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
}