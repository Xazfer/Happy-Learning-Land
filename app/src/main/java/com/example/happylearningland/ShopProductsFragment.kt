package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentMainScreenBinding
import com.example.happylearningland.databinding.FragmentShopProductsFragmentBinding

class Shop_Products_fragment : Fragment(R.layout.fragment_shop_products_fragment) {
    private lateinit var binding: FragmentShopProductsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentShopProductsFragmentBinding.bind(view)

        binding.productPan.setOnClickListener {
            val producto:String = "pam"
            val bundle = bundleOf("Producto" to "Pan")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.productGalleta.setOnClickListener {
                    val bundle = bundleOf("Producto" to "Galleta")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
    }
}