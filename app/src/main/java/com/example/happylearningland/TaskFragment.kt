package com.example.happylearningland

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.happylearningland.databinding.FragmentTaskBinding

class TaskFragment : Fragment(R.layout.fragment_task) {
    private lateinit var binding: FragmentTaskBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentTaskBinding.bind(view)

        binding.btnInicio.setOnClickListener {
            val bundle = bundleOf("texto" to "Pagina letras")
            findNavController().navigate(R.id.action_nav_taskFragment_to_nav_mainScreenFragment, bundle)
        }
        //reproduccion de audio
        binding.ayuda.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.ayudatareas)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.ayudatareas)
                mediaPlayer?.start()
            }
        }

        binding.tarea.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.realizartarea)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.realizartarea)
                mediaPlayer?.start()
            }
        }

        binding.dientes.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.lavardientes)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.lavardientes)
                mediaPlayer?.start()
            }
        }

        binding.cuarto.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.limpiarcuarto)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.limpiarcuarto)
                mediaPlayer?.start()
            }
        }

        binding.limpiar.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.limpiarcasa)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.limpiarcasa)
                mediaPlayer?.start()
            }
        }

        binding.basura.setOnClickListener {
            if(mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
                mediaPlayer= MediaPlayer.create(context, R.raw.tirarbasura)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.tirarbasura)
                mediaPlayer?.start()
            }
        }

    }

}