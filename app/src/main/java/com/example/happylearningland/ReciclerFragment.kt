package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentReciclerBinding

class ReciclerFragment : Fragment(R.layout.fragment_recicler) {
    private lateinit var binding: FragmentReciclerBinding
    private var nombre:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentReciclerBinding.bind(view)

        binding.btnInicio.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Inicio")
            findNavController().navigate(R.id.action_nav_reciclerFragment_to_reciclerTutorialFragment, bundle)
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
}