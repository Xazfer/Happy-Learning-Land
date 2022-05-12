package com.example.happylearningland

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentAlimentacionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class AlimentacionFragment : Fragment(R.layout.fragment_alimentacion) {

    private lateinit var binding: FragmentAlimentacionBinding
    private lateinit var db: DatabaseReference
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentAlimentacionBinding.bind(view)

        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        // Recuperación de imagen del personaje que fue seleccionado
        getData(db)

        binding.btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_nav_mainScreenFragment)
        }
        binding.instrucciones.setOnClickListener {
            mediaPlayer= MediaPlayer.create(context, R.raw.instruccionesalimentacion)
            mediaPlayer?.start()
        }
        binding.btnAgua.setOnClickListener {
            val bundle = bundleOf("Producto" to "Agua2")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnAguaLimon.setOnClickListener {
            val bundle = bundleOf("Producto" to "AguaLimon")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnAguaNaranja.setOnClickListener {
            val bundle = bundleOf("Producto" to "AguaNaranja")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnJugoNaranja.setOnClickListener {
            val bundle = bundleOf("Producto" to "JugoNaranja")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnLeche.setOnClickListener {
            val bundle = bundleOf("Producto" to "Leche2")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnLecheChocolate.setOnClickListener {
            val bundle = bundleOf("Producto" to "LecheChocolate")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnRefresco.setOnClickListener {
            val bundle = bundleOf("Producto" to "Refresco")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnRefrescoCola.setOnClickListener {
            val bundle = bundleOf("Producto" to "RefrescoCola")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnVerdura.setOnClickListener {
            val bundle = bundleOf("Producto" to "Verdura2")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnFruta.setOnClickListener {
            val bundle = bundleOf("Producto" to "Fruta2")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnHuevo.setOnClickListener {
            val bundle = bundleOf("Producto" to "Huevo2")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnPescado.setOnClickListener {
            val bundle = bundleOf("Producto" to "Pescado")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnPasta.setOnClickListener {
            val bundle = bundleOf("Producto" to "Pasta")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnSandwich.setOnClickListener {
            val bundle = bundleOf("Producto" to "Sandwich")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnPizza.setOnClickListener {
            val bundle = bundleOf("Producto" to "Pizza")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnPastel.setOnClickListener {
            val bundle = bundleOf("Producto" to "Pastel")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
        }
        binding.btnGalletas.setOnClickListener {
            val bundle = bundleOf("Producto" to "Galletas2")
            findNavController().navigate(R.id.action_nav_alimentacionFragment_to_bottomSheetAlimentation, bundle)
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
                getImage(imageCharacter)
                Log.w("data", "datos recuperados $us")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("data", "datos no recuperados", error.toException())
            }

        }
        data.addValueEventListener(listener)
    }

    // Función de obtención de personaje para colocarlo en su contenedor
    private fun getImage(path : String) {
        var pathImage = storageReference.child(path)
        var localFileImage = File.createTempFile("tempImage", "png")

        pathImage.getFile(localFileImage).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFileImage.absolutePath)
            binding.containerCharacter.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Log.w("image", "imagen no recuperados")
        }
    }

}