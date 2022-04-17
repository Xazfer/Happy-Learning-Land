package com.example.happylearningland

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomshet_settings_fragment.*

class BottomSheetSettings : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVolume.setOnClickListener {
            Toast.makeText(context, "Configuración de sonido", Toast.LENGTH_SHORT).show()
        }

        btnMute.setOnClickListener {
            Toast.makeText(context, "Configuración de sonido", Toast.LENGTH_SHORT).show()
        }

    }

}