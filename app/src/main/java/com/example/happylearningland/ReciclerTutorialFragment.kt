package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.happylearningland.databinding.FragmentLettersBinding
import com.example.happylearningland.databinding.FragmentReciclerTutorialBinding


class ReciclerTutorialFragment : Fragment(R.layout.fragment_recicler_tutorial) {
    private lateinit var binding: FragmentReciclerTutorialBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReciclerTutorialBinding.bind(view)

    }

}