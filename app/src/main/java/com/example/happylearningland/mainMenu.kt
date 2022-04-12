package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentMainMenuBinding
import kotlinx.android.synthetic.main.fragment_main_menu.*

class mainMenu : Fragment(R.layout.fragment_main_menu) {

    private lateinit var binding:FragmentMainMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos de la view
        binding = FragmentMainMenuBinding.bind(view)

        //Navegacion a main screen  con madandado de parametros
        btnNAV.setOnClickListener {
            val bundle = bundleOf("texto" to "welcome my friend")
            findNavController().navigate(R.id.action_mainMenu_to_mainScreenFragment, bundle)
        }

    }
}