package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentAlimentacionBinding


class AlimentacionFragment : Fragment(R.layout.fragment_alimentacion) {

    private lateinit var binding: FragmentAlimentacionBinding
    private var nombre:String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentAlimentacionBinding.bind(view)
        //recuperacion de argumentos
        arguments?.let {
            nombre = it.getString("texto")
            binding.text.text = nombre
        }
    }
}