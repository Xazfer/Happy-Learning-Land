package com.example.happylearningland


import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentMainScreenBinding

import kotlinx.android.synthetic.main.fragment_main_screen.*


//variable para sonido de fondo
var mediaPlayer: MediaPlayer? = null
class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private lateinit var binding: FragmentMainScreenBinding
    //variable para recuperacion de datos
    private var nombre:String? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bindeo de elementos del fragment
        binding = FragmentMainScreenBinding.bind(view)

        //obtencion de argumentos
        arguments?.let {
            nombre = it.getString("texto")
            binding.texto.text = nombre
        }

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

        //botones de navegacion
        btnRobot.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina robot")
            findNavController().navigate(R.id.action_mainScreenFragment_to_robotFragment2, bundle)
        }
        btnShop.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina tienda")
            findNavController().navigate(R.id.action_mainScreenFragment_to_shopFragment, bundle)
        }
        btnRecicler.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina reciclaje")
            findNavController().navigate(R.id.action_mainScreenFragment_to_reciclerFragment, bundle)
        }
        btnLetter.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina letras")
            findNavController().navigate(R.id.action_mainScreenFragment_to_lettersFragment, bundle)
        }
        btnAlimentacion.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina alimentación")
            findNavController().navigate(R.id.action_mainScreenFragment_to_alimentacionFragment, bundle)
        }
        btnTask.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina tareas")
            findNavController().navigate(R.id.action_mainScreenFragment_to_taskFragment, bundle)
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


}