package com.example.happylearningland

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.happylearningland.databinding.FragmentBottomSheetAlimentationBinding
import com.example.happylearningland.databinding.FragmentBottomSheetProductsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_alimentation.*

class BottomSheetProducts : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetProductsBinding
    var producto:String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_bottom_sheet_products, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetProductsBinding.bind(view)
        arguments?.let {
            producto = it.getString("Producto")
        }
        when(producto){
            "Galletas" -> {
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_galleta)
                mediaPlayer= MediaPlayer.create(context, R.raw.galletas)
                mediaPlayer?.start()
            }
            "Pan" -> {
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_pan)
                mediaPlayer= MediaPlayer.create(context, R.raw.pan)
                mediaPlayer?.start()
            }
            "Papitas"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_sabritas)
                mediaPlayer= MediaPlayer.create(context, R.raw.papitas)
                mediaPlayer?.start()
            }
            "Agua"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_agua)
                mediaPlayer= MediaPlayer.create(context, R.raw.agua)
                mediaPlayer?.start()
            }
            "Leche"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_leche)
                mediaPlayer= MediaPlayer.create(context, R.raw.leche)
                mediaPlayer?.start()
            }
            "Huevos"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_huevos)
                mediaPlayer= MediaPlayer.create(context, R.raw.huevos)
                mediaPlayer?.start()
            }
            "Arroz"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_arroz)
                mediaPlayer= MediaPlayer.create(context, R.raw.arroz)
                mediaPlayer?.start()
            }
            "Canasta de Frutas"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_fruta)
                mediaPlayer= MediaPlayer.create(context, R.raw.fruta)
                mediaPlayer?.start()
            }
            "Canasta de Verduras"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_verdura)
                mediaPlayer= MediaPlayer.create(context, R.raw.verduras)
                mediaPlayer?.start()
            }
        }
    }

}