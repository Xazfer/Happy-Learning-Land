package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.happylearningland.databinding.FragmentMainScreenBinding
import com.example.happylearningland.databinding.FragmentTaskBinding


class TaskFragment : Fragment(R.layout.fragment_task) {
    private lateinit var binding: FragmentTaskBinding
    private var nombre:String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentTaskBinding.bind(view)
        //obtencion de argumentos
        arguments?.let {
            nombre = it.getString("texto")
            binding.text.text = nombre
        }
    }

}