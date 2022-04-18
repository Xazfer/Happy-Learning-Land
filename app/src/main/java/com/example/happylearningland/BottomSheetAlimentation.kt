package com.example.happylearningland

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.happylearningland.databinding.FragmentBottomSheetAlimentationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_alimentation.*

class BottomSheetAlimentation : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetAlimentationBinding
    var producto:String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_bottom_sheet_alimentation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetAlimentationBinding.bind(view)
        arguments?.let {
            producto = it.getString("Producto")
            binding.textoInfo.text = producto
        }
        when(producto){
            "Galleta" -> {
                binding.textoInfo.text = producto
                btnSalud.setImageResource(R.drawable.galletas)
            }

            "Pan" -> binding.textoInfo.text = producto
        }
    }

}