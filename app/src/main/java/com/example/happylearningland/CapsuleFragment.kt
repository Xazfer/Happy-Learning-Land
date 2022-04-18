package com.example.happylearningland

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.happylearningland.databinding.FragmentMainMenuBinding

class CapsuleFragment : AppCompatActivity() {
    private lateinit var binding: FragmentMainMenuBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capsule_fragment)

    }
}