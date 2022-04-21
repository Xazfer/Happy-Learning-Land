package com.example.happylearningland

import android.os.Binder
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.happylearningland.databinding.FragmentReciclerEasyBinding


class ReciclerEasyFragment : Fragment(R.layout.fragment_recicler_easy) {

    private lateinit var binding: FragmentReciclerEasyBinding
    private var txtTiempo: EditText? = null
    private var tvCuentaAtras: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReciclerEasyBinding.bind(view)
        txtTiempo = binding.txtTiempo.findViewById(R.id.txtTiempo)
        tvCuentaAtras = binding.tvCuentaAtras.findViewById(R.id.tvCuentaAtras)

    }
        fun play(view: View) {
            var tiempoSegundos = txtTiempo?.text.toString().toLong()
            var tiempoMilisegundos = tiempoSegundos * 1000
            object : CountDownTimer(tiempoMilisegundos, 1000) {
                override fun onFinish() {
                }
                override fun onTick(millisUntilFinished: Long) {
                    val tiempoSegundos = (millisUntilFinished / 1000).toInt() + 1
                    tvCuentaAtras?.text = tiempoSegundos.toString().padStart(2, '0')
                }
            }.start()
        }
}


