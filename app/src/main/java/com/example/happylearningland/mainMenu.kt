package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.happylearningland.databinding.FragmentMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_menu.*

class mainMenu : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main_menu)

    }

    //private lateinit var binding:FragmentMainMenuBinding

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos de la view
        binding = FragmentMainMenuBinding.bind(view)

        //Navegacion a main screen  con madandado de parametros
        /*btnNAV.setOnClickListener {
            val bundle = bundleOf("texto" to "welcome my friend")
            findNavController().navigate(R.id.action_mainMenu_to_mainScreenFragment, bundle)
        }*/

    }*/
}