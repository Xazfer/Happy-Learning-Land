package com.example.happylearningland

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentBottomSheetProductsBinding
import com.example.happylearningland.databinding.FragmentShopProductsFragmentBinding
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

class BottomSheetProducts : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetProductsBinding
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
        return inflater.inflate(R.layout.fragment_bottom_sheet_products, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetProductsBinding.bind(view)
        arguments?.let {
            producto = it.getString("Producto")
        }

        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)

        uid = auth.currentUser!!.uid
        when(producto){
            "Galletas" -> {
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_galleta)
                mediaPlayer= MediaPlayer.create(context, R.raw.galletas)
                mediaPlayer?.start()
                setButtons(5)
            }
            "Pan" -> {
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_pan)
                mediaPlayer= MediaPlayer.create(context, R.raw.pan)
                mediaPlayer?.start()
                setButtons(8)
            }
            "Papitas"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_sabritas)
                mediaPlayer= MediaPlayer.create(context, R.raw.papitas)
                mediaPlayer?.start()
                setButtons(6)
            }
            "Agua"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_agua)
                mediaPlayer= MediaPlayer.create(context, R.raw.agua)
                mediaPlayer?.start()
                setButtons(4)
            }
            "Leche"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_leche)
                mediaPlayer= MediaPlayer.create(context, R.raw.leche)
                mediaPlayer?.start()
                setButtons(6)
            }
            "Huevos"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_huevos)
                mediaPlayer= MediaPlayer.create(context, R.raw.huevos)
                mediaPlayer?.start()
                setButtons(4)
            }
            "Arroz"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_arroz)
                mediaPlayer= MediaPlayer.create(context, R.raw.arroz)
                mediaPlayer?.start()
                setButtons(7)
            }
            "Canasta de Frutas"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_fruta)
                mediaPlayer= MediaPlayer.create(context, R.raw.fruta)
                mediaPlayer?.start()
                setButtons(7)
            }
            "Canasta de Verduras"->{
                binding.textoProducto.text = producto
                imgAlimento.setImageResource(R.drawable.tienda_verdura)
                mediaPlayer= MediaPlayer.create(context, R.raw.verduras)
                mediaPlayer?.start()
                setButtons(6)
            }
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

    private fun setButtons(n: Int){
        binding.btnComer.setOnClickListener {
            if (coins < n){
                Toast.makeText(context, "No se puede comprar", Toast.LENGTH_SHORT).show()
            }else {
                val moneda = coins - n
                updateCoins(moneda)
                findNavController().navigate(R.id.action_bottomSheetProducts_to_shop_Products_fragment)
            }
        }
        binding.btnCancelar.setOnClickListener{
            findNavController().navigate(R.id.action_bottomSheetProducts_to_shop_Products_fragment)
        }
    }
}