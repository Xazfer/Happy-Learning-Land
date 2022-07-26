package com.myapp.happylearningland

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.myapp.happylearningland.databinding.FragmentBottomSheetAlimentationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_bottom_sheet_alimentation.*

class BottomSheetAlimentation : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetAlimentationBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>
    private var uid = ""

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
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)
        arguments?.let {
            producto = it.getString("Producto")
        }



        when(producto){
            "Agua2"->{
                binding.textoProducto.text = "Agua simple"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.agua_simple)
                btnSalud.setImageResource(R.drawable.happy)
                setButtons("Muy saludable")
            }
            "AguaLimon"->{
                binding.textoProducto.text = "Agua de limón"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.agua_limon)
                btnSalud.setImageResource(R.drawable.neutral)
                setButtons("Saludable")
            }
            "AguaNaranja"->{
                binding.textoProducto.text = "Agua de naranja"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.agua_naranja)
                btnSalud.setImageResource(R.drawable.neutral)
                setButtons("Saludable")
            }
            "JugoNaranja"->{
                binding.textoProducto.text = "Jugo de naranja"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.jugo_naranja)
                btnSalud.setImageResource(R.drawable.happy)
                setButtons("Muy saludable")
            }
            "Leche2"->{
                binding.textoProducto.text = "Leche"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.leche)
                btnSalud.setImageResource(R.drawable.happy)
                setButtons("Muy saludable")
            }
            "LecheChocolate"->{
                binding.textoProducto.text = "Leche con chocolate"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.leche_cafe)
                btnSalud.setImageResource(R.drawable.sad)
                setButtons("No es recomendable")
            }
            "Refresco"->{
                binding.textoProducto.text = "Refresco"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.refresco)
                btnSalud.setImageResource(R.drawable.sad)
                setButtons("No es recomendable")
            }
            "RefrescoCola"->{
                binding.textoProducto.text = "Refresco de cola"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.refresco_cola)
                btnSalud.setImageResource(R.drawable.sad)
                setButtons("No es recomendable")
            }
            "Verdura2"->{
                binding.textoProducto.text = "Verduras"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.verduras)
                btnSalud.setImageResource(R.drawable.happy)
                setButtons("Muy saludable")
            }
            "Fruta2"->{
                binding.textoProducto.text = "Frutas"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.frutas)
                btnSalud.setImageResource(R.drawable.happy)
                setButtons("Muy saludable")
            }
            "Huevo2"->{
                binding.textoProducto.text = "Huevos"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.huevos)
                btnSalud.setImageResource(R.drawable.neutral)
                setButtons("Saludable")
            }
            "Pescado"->{
                binding.textoProducto.text = "Pescado"
                binding.textoInfo.text = "Muy saludable"
                imgAlimento.setImageResource(R.drawable.pescado)
                btnSalud.setImageResource(R.drawable.happy)
                setButtons("Muy saludable")
            }
            "Pasta"->{
                binding.textoProducto.text = "Pasta"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.pasta)
                btnSalud.setImageResource(R.drawable.neutral)
                setButtons("Saludable")
            }
            "Sandwich"->{
                binding.textoProducto.text = "Sándwich"
                binding.textoInfo.text = "Saludable"
                imgAlimento.setImageResource(R.drawable.sandwich)
                btnSalud.setImageResource(R.drawable.neutral)
                setButtons("Saludable")
            }
            "Pizza"->{
                binding.textoProducto.text = "Rebanada de pizza"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.pizza)
                btnSalud.setImageResource(R.drawable.sad)
                setButtons("No es recomendable")
            }
            "Pastel"->{
                binding.textoProducto.text = "Rebanada de pastel"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.pastel)
                btnSalud.setImageResource(R.drawable.sad)
                setButtons("No es recomendable")
            }
            "Galletas2"->{
                binding.textoProducto.text = "Galletas"
                binding.textoInfo.text = "No es recomendable"
                imgAlimento.setImageResource(R.drawable.galletas)
                btnSalud.setImageResource(R.drawable.sad)
                setButtons("No es recomendable")
            }
        }

    }

    private fun setButtons(n : String){
        binding.btnComer.setOnClickListener {
            if (coins < 1 && n.equals("No es recomendable")){
                Toast.makeText(context, "Cuidado con tus monedas", Toast.LENGTH_SHORT).show()
            }
            if (coins >= 1 && n.equals("No es recomendable")){
                val moneda = coins - 1
                updateCoins(moneda)
                Toast.makeText(context, "Cuidado que comes", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_bottomSheetAlimentation_to_nav_alimentacionFragment)
            }else{
                val moneda = coins + 1
                updateCoins(moneda)
                Toast.makeText(context, "Muy bien, sigue comiendo saludable", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_bottomSheetAlimentation_to_nav_alimentacionFragment)
            }
        }
        binding.btnCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetAlimentation_to_nav_alimentacionFragment)
        }
    }
    private fun getData(data : DatabaseReference) {
        val current = auth.currentUser
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val us = snapshot.child("player").child(current!!.uid).getValue<User>()
                imageCharacter = us!!.character
                coins = us.coins
                tasks = us.tasks
                Log.w("data", "datos recuperados $us")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("data", "datos no recuperados", error.toException())
            }

        }
        data.addValueEventListener(listener)
    }

    // Actualización de coins
    // Función de subir datos DB
    private fun updateCoins(coins : Int) {
        val id = uid
        db.child("player").child(id).get().addOnSuccessListener {
            if (it.value == null) {
                Log.w("texto inexistente", "datos no encontrados")
            } else {
                db.child("player").child(id).child("coins").setValue(coins)
                Log.w("texto existente", "datos encontrados ${it.value}")
            }
        }.addOnFailureListener {
            Log.w("texto inexistente", "datos no encontrados")
        }
    }
}