package com.example.happylearningland

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetAlimentationBinding.bind(view)
        arguments?.let {
            producto = it.getString("Producto")
        }
        when(producto){
            "Galleta" -> {
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_galleta)
                mediaPlayer= MediaPlayer.create(context, R.raw.galletas)
                mediaPlayer?.start()
            }
            "Pan" -> {
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_pan)
                mediaPlayer= MediaPlayer.create(context, R.raw.pan)
                mediaPlayer?.start()
            }
            "Papitas"->{
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_sabritas)
                mediaPlayer= MediaPlayer.create(context, R.raw.papitas)
                mediaPlayer?.start()
            }
            "Agua"->{
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_agua)
                mediaPlayer= MediaPlayer.create(context, R.raw.agua)
                mediaPlayer?.start()
            }
            "Leche"->{
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_leche)
                mediaPlayer= MediaPlayer.create(context, R.raw.leche)
                mediaPlayer?.start()
            }
            "Huevo"->{
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_huevos)
                mediaPlayer= MediaPlayer.create(context, R.raw.huevos)
                mediaPlayer?.start()
            }
            "Arroz"->{
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_arroz)
                mediaPlayer= MediaPlayer.create(context, R.raw.arroz)
                mediaPlayer?.start()
            }
            "Fruta"->{
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_fruta)
                mediaPlayer= MediaPlayer.create(context, R.raw.fruta)
                mediaPlayer?.start()
            }
            "Verdura"->{
                binding.textoProducto.text = producto
                binding.textoInfo.isVisible = false
                binding.btnSalud.isVisible = false
                imgAlimento.setImageResource(R.drawable.tienda_verdura)
                mediaPlayer= MediaPlayer.create(context, R.raw.verduras)
                mediaPlayer?.start()
            }
            "Agua2"->{
                binding.textoProducto.text = "Agua simple"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.agua_simple)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "AguaLimon"->{
                binding.textoProducto.text = "Agua de limon"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.agua_limon)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "AguaNaranja"->{
                binding.textoProducto.text = "Agua de naranja"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.agua_naranja)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "JugoNaranja"->{
                binding.textoProducto.text = "Jugo de naranja"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.jugo_naranja)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "Leche2"->{
                binding.textoProducto.text = "Leche"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.leche)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "LecheChocolate"->{
                binding.textoProducto.text = "Leche con chocolate"
                binding.textoInfo.text = "No es muy recomendable"
                imgAlimento.setImageResource(R.drawable.leche_cafe)
                btnSalud.setImageResource(R.drawable.neutral)
            }
            "Refresco"->{
                binding.textoProducto.text = "Refresco"
                binding.textoInfo.text = "No es muy recomendable"
                imgAlimento.setImageResource(R.drawable.refresco)
                btnSalud.setImageResource(R.drawable.sad)
            }
            "RefrescoCola"->{
                binding.textoProducto.text = "Refresco de cola"
                binding.textoInfo.text = "Puedes tomar algo mas sano"
                imgAlimento.setImageResource(R.drawable.refresco_cola)
                btnSalud.setImageResource(R.drawable.sad)
            }
            "Verdura2"->{
                binding.textoProducto.text = "Verduras"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.verduras)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "Fruta2"->{
                binding.textoProducto.text = "Frutas"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.frutas)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "Huevo2"->{
                binding.textoProducto.text = "Huevos"
                binding.textoInfo.text = "No es muy saludable"
                imgAlimento.setImageResource(R.drawable.huevos)
                btnSalud.setImageResource(R.drawable.neutral)
            }
            "Pescado"->{
                binding.textoProducto.text = producto
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.pescado)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "Pasta"->{
                binding.textoProducto.text = producto
                binding.textoInfo.text = "No es muy saludable"
                imgAlimento.setImageResource(R.drawable.pasta)
                btnSalud.setImageResource(R.drawable.neutral)
            }
            "Sandwich"->{
                binding.textoProducto.text = producto
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.sandwich)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "Pizza"->{
                binding.textoProducto.text = producto
                binding.textoInfo.text = "Puedes comer algo mas sano"
                imgAlimento.setImageResource(R.drawable.pizza)
                btnSalud.setImageResource(R.drawable.sad)
            }
            "Pastel"->{
                binding.textoProducto.text = producto
                binding.textoInfo.text = "Puedes comer algo mas sano"
                imgAlimento.setImageResource(R.drawable.pastel)
                btnSalud.setImageResource(R.drawable.sad)
            }
            "Galleta2"->{
                binding.textoProducto.text = "Galletas"
                binding.textoInfo.text = "Puedes comer algo mas sano"
                imgAlimento.setImageResource(R.drawable.galletas)
                btnSalud.setImageResource(R.drawable.sad)
            }
        }
    }

}