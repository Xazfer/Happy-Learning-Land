package com.example.happylearningland


import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentMainScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_main_screen.*


//variable para sonido de fondo
var mediaPlayer: MediaPlayer? = null

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private lateinit var binding: FragmentMainScreenBinding
    //variable para recuperacion de datos

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bindeo de elementos del fragment
        binding = FragmentMainScreenBinding.bind(view)

        //obtencion de argumentos
        /*arguments?.let {
            nombre = it.getString("texto")
            binding.texto.text = nombre
        }*/

        // Bóton de configuración de audio
        /*binding.btnSettings.setOnClickListener {
            val view:View = layoutInflater.inflate(R.layout.bottomsheet_settings_fragment, null)
            val dialog = BottomSheetDialog(Contex?)
            dialog.setContentView(view)
            dialog.show()
        }*/
        /*val bottomSheetSettings = BottomSheetSettings()

        btnSettings.setOnClickListener {
            bottomSheetSettings.show(supportFragmentManager, "BottomSheetDialog")
        }*/

        //Reproducion de sonido en loop
        if(mediaPlayer?.isPlaying == true){
            mediaPlayer?.release()
            mediaPlayer = null
            mediaPlayer= MediaPlayer.create(context, R.raw.gamesample)
            mediaPlayer?.start()
            mediaPlayer?.isLooping = true
        }else{
            mediaPlayer= MediaPlayer.create(context, R.raw.gamesample)
            mediaPlayer?.start()
            mediaPlayer?.isLooping = true
        }
        binding.btnApagarMusic.setOnClickListener {
            mediaPlayer?.release()
            mediaPlayer = null
        }

        //TODO: momentaneo para acceder a tareas
        binding.btnEncenderMusic.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina letras")
            findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_taskFragment, bundle)

        }
        //Navegacion a los fragments
        binding.bottomNavigation.setOnItemSelectedListener {
                item ->
            when(item.itemId) {
                R.id.nav_lettersFragment  -> {
                    // Respond to navigation item 1 click
                    val bundle = bundleOf("texto" to "Pagina letras")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_lettersFragment, bundle)
                    true
                }
                R.id.nav_robotFragment -> {
                    // Respond to navigation item 2 click
                    val bundle = bundleOf("texto" to "Pagina robot")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_robotFragment, bundle)
                    true
                }
                R.id.nav_shopFragment -> {
                    // Respond to navigation item 2 click
                    val bundle = bundleOf("texto" to "Pagina tienda")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_shopFragment, bundle)
                    true
                }
                R.id.nav_alimentacionFragment -> {
                    // Respond to navigation item 2 click
                    val bundle = bundleOf("texto" to "Pagina alimentación")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_alimentacionFragment, bundle)
                    true
                }
                R.id.nav_reciclerFragment -> {
                    // Respond to navigation item 2 click
                    val bundle = bundleOf("texto" to "Pagina reciclaje")
                    findNavController().navigate(R.id.action_nav_mainScreenFragment_to_nav_reciclerFragment, bundle)
                    true
                }
                else -> false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.release()
        mediaPlayer = null
    }

}