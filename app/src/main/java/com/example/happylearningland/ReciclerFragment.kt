package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentReciclerBinding


class ReciclerFragment : Fragment(R.layout.fragment_recicler) {
    private lateinit var binding: FragmentReciclerBinding
    private var nombre:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentReciclerBinding.bind(view)
        //obtencion de argumentos
    }
}