package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentShopBinding

class ShopFragment : Fragment(R.layout.fragment_shop) {
    private lateinit var binding: FragmentShopBinding
    private var nombre:String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentShopBinding.bind(view)
        //obtencion de argumentos
        /*arguments?.let {
            nombre = it.getString("texto")
            binding.text.text = nombre
        }*/
    }
}