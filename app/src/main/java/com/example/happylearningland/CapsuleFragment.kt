package com.example.happylearningland

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.happylearningland.databinding.FragmentMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CapsuleFragment : AppCompatActivity() {
    private lateinit var binding: FragmentMainMenuBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capsule_fragment)

    }
}