package com.example.happylearningland

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentShopProductsFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Shop_Products_fragment : Fragment(R.layout.fragment_shop_products_fragment) {
    private lateinit var binding: FragmentShopProductsFragmentBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentShopProductsFragmentBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)
        binding.instrucciones.setOnClickListener {
            mediaPlayer= MediaPlayer.create(context, R.raw.instruccionestienda)
            mediaPlayer?.start()
        }
        binding.btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_shop_Products_fragment_to_nav_shopFragment)
        }
        binding.btnVuelta.setOnClickListener {
            findNavController().navigate(R.id.action_shop_Products_fragment_to_nav_mainScreenFragment)
        }
        binding.productPan.setOnClickListener {
            val producto:String = "pam"
            val bundle = bundleOf("Producto" to "Pan")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.productGalleta.setOnClickListener {
                    val bundle = bundleOf("Producto" to "Galleta")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.productPapitas.setOnClickListener {
            val bundle = bundleOf("Producto" to "Papitas")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.productLeche.setOnClickListener {
            val bundle = bundleOf("Producto" to "Leche")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.productHuevo.setOnClickListener {
            val bundle = bundleOf("Producto" to "Huevo")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.profuctArroz.setOnClickListener {
            val bundle = bundleOf("Producto" to "Arroz")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.profuctAgua.setOnClickListener {
            val bundle = bundleOf("Producto" to "Agua")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.productFruta.setOnClickListener {
            val bundle = bundleOf("Producto" to "Fruta")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }
        binding.productVerdura.setOnClickListener {
            val bundle = bundleOf("Producto" to "Verdura")
            findNavController().navigate(R.id.action_shop_Products_fragment_to_bottomSheetAlimentation, bundle)
        }

    }

    // Función de obtener datos
    private fun getData(data : DatabaseReference) {
        val current = auth.currentUser
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val us = snapshot.child("player").child(current!!.uid).getValue<User>()
                imageCharacter = us!!.character
                coins = us.coins
                tasks = us.tasks
                binding.coins.text = us.coins.toString()
                Log.w("data", "datos recuperados $us")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("data", "datos no recuperados", error.toException())
            }

        }
        data.addValueEventListener(listener)
    }

    /*
    // Actualización de coins
    // Función de subir datos DB
    private fun updateCoins(coins : Int, email: String) {
        val id = email
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

     */

}