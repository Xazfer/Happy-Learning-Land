package com.example.happylearningland


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentMainScreenBinding

import kotlinx.android.synthetic.main.fragment_main_screen.*


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

}