package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentLettersBinding


class LettersFragment : Fragment(R.layout.fragment_letters) {
    private lateinit var binding: FragmentLettersBinding
    private var nombre:String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentLettersBinding.bind(view)
        //recuperacion de argumentos
        arguments?.let {
            nombre = it.getString("texto")
            binding.text.text = nombre
        }
    }



}