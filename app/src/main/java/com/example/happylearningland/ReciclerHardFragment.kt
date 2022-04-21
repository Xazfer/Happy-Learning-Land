package com.example.happylearningland

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.happylearningland.databinding.FragmentReciclerEasyBinding
import com.example.happylearningland.databinding.FragmentReciclerHardBinding

class ReciclerHardFragment : Fragment(R.layout.fragment_recicler_hard) {
    private lateinit var binding: FragmentReciclerHardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReciclerHardBinding.bind(view)
        var tiempo = binding.tiempo.text
        Log.e("Revision", tiempo.toString())
    }

}