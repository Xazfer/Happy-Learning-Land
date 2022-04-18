package com.example.happylearningland

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_settings_fragment.*

class BottomSheetSettings : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("REVISION", "CREACION")
        return inflater.inflate(R.layout.bottomsheet_settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVolume.setOnClickListener {
            Toast.makeText(context, "Sonido activado", Toast.LENGTH_SHORT).show()
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
        }

        btnMute.setOnClickListener {
            mediaPlayer?.release()
            mediaPlayer = null
            Toast.makeText(context, "Sonido desactivado", Toast.LENGTH_SHORT).show()
        }

    }
}