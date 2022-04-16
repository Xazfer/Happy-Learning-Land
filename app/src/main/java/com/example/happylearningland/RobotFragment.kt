package com.example.happylearningland


import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentRobotBinding


class RobotFragment : Fragment(R.layout.fragment_robot) {

    private lateinit var binding: FragmentRobotBinding
    private var nombre:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentRobotBinding.bind(view)
        //obtencion de argumentos
        /*arguments?.let {
            nombre = it.getString("texto")
            binding.text.text = nombre
        }*/
       /* if(mediaPlayer?.isPlaying == true){
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
        binding.btnPause.setOnClickListener {
            mediaPlayer?.release()
            mediaPlayer = null
        }*/
    }
}