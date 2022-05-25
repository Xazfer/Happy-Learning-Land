package com.myapp.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.myapp.happylearningland.databinding.FragmentShopBinding

class ShopFragment : Fragment(R.layout.fragment_shop) {
    private lateinit var binding: FragmentShopBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentShopBinding.bind(view)
        binding.btnEnter.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Inicio")
            findNavController().navigate(R.id.action_nav_shopFragment_to_shop_Products_fragment, bundle)
        }
        binding.btnBack.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina Tienda")
            findNavController().navigate(R.id.action_nav_shopFragment_to_nav_mainScreenFragment, bundle)
        }
    }
}