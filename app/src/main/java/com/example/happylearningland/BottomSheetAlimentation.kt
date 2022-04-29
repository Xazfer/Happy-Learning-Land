package com.example.happylearningland

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

        setButtons()

        when(producto){
            "Agua2"->{
                binding.textoProducto.text = "Agua simple"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.agua_simple)
                btnSalud.setImageResource(R.drawable.happy)

            }
            "AguaLimon"->{
                binding.textoProducto.text = "Agua de limón"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.agua_limon)
                btnSalud.setImageResource(R.drawable.neutral)
            }
            "AguaNaranja"->{
                binding.textoProducto.text = "Agua de naranja"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.agua_naranja)
                btnSalud.setImageResource(R.drawable.neutral)
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
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.leche_cafe)
                btnSalud.setImageResource(R.drawable.sad)
            }
            "Refresco"->{
                binding.textoProducto.text = "Refresco"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.refresco)
                btnSalud.setImageResource(R.drawable.sad)
            }
            "RefrescoCola"->{
                binding.textoProducto.text = "Refresco de cola"
                binding.textoInfo.text = "No es recomendable"
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
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.huevos)
                btnSalud.setImageResource(R.drawable.neutral)
            }
            "Pescado"->{
                binding.textoProducto.text = "Pescado"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.pescado)
                btnSalud.setImageResource(R.drawable.happy)
            }
            "Pasta"->{
                binding.textoProducto.text = "Pasta"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.pasta)
                btnSalud.setImageResource(R.drawable.neutral)
            }
            "Sandwich"->{
                binding.textoProducto.text = "Sándwich"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.sandwich)
                btnSalud.setImageResource(R.drawable.neutral)
            }
            "Pizza"->{
                binding.textoProducto.text = "Rebanada de pizza"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.pizza)
                btnSalud.setImageResource(R.drawable.sad)
            }
            "Pastel"->{
                binding.textoProducto.text = "Rebanada de pastel"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.pastel)
                btnSalud.setImageResource(R.drawable.sad)
            }
            "Galletas2"->{
                binding.textoProducto.text = "Galletas"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.galletas)
                btnSalud.setImageResource(R.drawable.sad)
            }
        }

    }
    private fun setButtons(){
        binding.btnComer.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetAlimentation_to_nav_alimentacionFragment)
        }
        binding.btnCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetAlimentation_to_nav_alimentacionFragment)
        }
    }
}