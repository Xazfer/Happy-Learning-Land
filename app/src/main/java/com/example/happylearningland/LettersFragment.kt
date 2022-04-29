package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentLettersBinding
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.media.MediaPlayer
import android.util.Log
import android.view.DragEvent
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_letters.*


class LettersFragment : Fragment(R.layout.fragment_letters) {
    private lateinit var binding: FragmentLettersBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>
    private var uid = ""

    var num = 1
    var score = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentLettersBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)
        uid = auth.currentUser!!.uid

        //recuperacion de argumentos
        Log.e("Revision", num.toString())

        drag10ImageView.setOnLongClickListener(longClickListener)
        drag11ImageView.setOnLongClickListener(longClickListener)
        drag12ImageView.setOnLongClickListener(longClickListener)
        drag13ImageView.setOnLongClickListener(longClickListener)

        drag14ImageView.setOnLongClickListener(longClickListener)
        drag15ImageView.setOnLongClickListener(longClickListener)
        drag16ImageView.setOnLongClickListener(longClickListener)
        drag17ImageView.setOnLongClickListener(longClickListener)
        drag18ImageView.setOnLongClickListener(longClickListener)
        drag19ImageView.setOnLongClickListener(longClickListener)
        drag20ImageView.setOnLongClickListener(longClickListener)

        target10ImageView.setOnDragListener(dragListener)
        target11ImageView.setOnDragListener(dragListener2)
        target12ImageView.setOnDragListener(dragListener3)

        setLetters()

        binding.btnInicio.setOnClickListener {
            back()
        }
        binding.instrucciones.setOnClickListener {
            mediaPlayer= MediaPlayer.create(context, R.raw.instruccionesletras)
            mediaPlayer?.start()
        }

    }

    fun setLetters(){
        when(num){
            1->{
                imageView.setImageResource(R.drawable.ola)
                mediaPlayer= MediaPlayer.create(context, R.raw.ola)
                mediaPlayer?.start()
                drag10ImageView.setImageResource(R.drawable.abecedarioo)
                drag11ImageView.setImageResource(R.drawable.abecedariol)
                drag12ImageView.setImageResource(R.drawable.abecedarioa)
                drag13ImageView.setImageResource(R.drawable.abecedariop)
                drag14ImageView.setImageResource(R.drawable.abecedarios)
                drag15ImageView.setImageResource(R.drawable.abecedariob)
                drag16ImageView.setImageResource(R.drawable.abecedariou)
                drag17ImageView.setImageResource(R.drawable.abecedariov)
                drag18ImageView.setImageResource(R.drawable.abecedarioy)
                drag19ImageView.setImageResource(R.drawable.abecedariov)
                drag20ImageView.setImageResource(R.drawable.abecedariop)
            }
            2->{
                imageView.setImageResource(R.drawable.oso)
                mediaPlayer= MediaPlayer.create(context, R.raw.oso)
                mediaPlayer?.start()
                drag10ImageView.setImageResource(R.drawable.abecedarioo)
                drag11ImageView.setImageResource(R.drawable.abecedarios)
                drag12ImageView.setImageResource(R.drawable.abecedarioo)
                drag13ImageView.setImageResource(R.drawable.abecedariop)
                drag14ImageView.setImageResource(R.drawable.abecedarioz)
                drag15ImageView.setImageResource(R.drawable.abecedarior)
                drag16ImageView.setImageResource(R.drawable.abecedarioe)
                drag17ImageView.setImageResource(R.drawable.abecedariou)
                drag18ImageView.setImageResource(R.drawable.abecedarioy)
                drag19ImageView.setImageResource(R.drawable.abecedariov)
                drag20ImageView.setImageResource(R.drawable.abecedariop)
            }
            3->{
                imageView.setImageResource(R.drawable.rey)
                mediaPlayer= MediaPlayer.create(context, R.raw.rey)
                mediaPlayer?.start()
                drag10ImageView.setImageResource(R.drawable.abecedarior)
                drag11ImageView.setImageResource(R.drawable.abecedarioe)
                drag12ImageView.setImageResource(R.drawable.abecedarioy)
                drag13ImageView.setImageResource(R.drawable.abecedarioa)
                drag14ImageView.setImageResource(R.drawable.abecedarioz)
                drag15ImageView.setImageResource(R.drawable.abecedariob)
                drag16ImageView.setImageResource(R.drawable.abecedariou)
                drag17ImageView.setImageResource(R.drawable.abecedariol)
                drag18ImageView.setImageResource(R.drawable.abecedarioy)
                drag19ImageView.setImageResource(R.drawable.abecedariov)
                drag20ImageView.setImageResource(R.drawable.abecedariop)
            }
            4->{
                imageView.setImageResource(R.drawable.pez)
                mediaPlayer= MediaPlayer.create(context, R.raw.pez)
                mediaPlayer?.start()
                drag10ImageView.setImageResource(R.drawable.abecedariop)
                drag11ImageView.setImageResource(R.drawable.abecedarioe)
                drag12ImageView.setImageResource(R.drawable.abecedarioz)
                drag13ImageView.setImageResource(R.drawable.abecedarioa)
                drag14ImageView.setImageResource(R.drawable.abecedarios)
                drag15ImageView.setImageResource(R.drawable.abecedarior)
                drag16ImageView.setImageResource(R.drawable.abecedarioi)
                drag17ImageView.setImageResource(R.drawable.abecedariou)
                drag18ImageView.setImageResource(R.drawable.abecedarioy)
                drag19ImageView.setImageResource(R.drawable.abecedarios)
                drag20ImageView.setImageResource(R.drawable.abecedarioa)
            }
            5->{
                imageView.setImageResource(R.drawable.uva)
                mediaPlayer= MediaPlayer.create(context, R.raw.uva)
                mediaPlayer?.start()
                drag10ImageView.setImageResource(R.drawable.abecedariou)
                drag11ImageView.setImageResource(R.drawable.abecedariov)
                drag12ImageView.setImageResource(R.drawable.abecedarioa)
                drag13ImageView.setImageResource(R.drawable.abecedarior)
                drag14ImageView.setImageResource(R.drawable.abecedarioi)
                drag15ImageView.setImageResource(R.drawable.abecedariob)
                drag16ImageView.setImageResource(R.drawable.abecedariol)
                drag17ImageView.setImageResource(R.drawable.abecedariol)
                drag18ImageView.setImageResource(R.drawable.abecedarioy)
                drag19ImageView.setImageResource(R.drawable.abecedariob)
                drag20ImageView.setImageResource(R.drawable.abecedariop)
            }
            6->{
                imageView.setImageResource(R.drawable.rio)
                mediaPlayer= MediaPlayer.create(context, R.raw.rio)
                mediaPlayer?.start()
                drag10ImageView.setImageResource(R.drawable.abecedarior)
                drag11ImageView.setImageResource(R.drawable.abecedarioi)
                drag12ImageView.setImageResource(R.drawable.abecedarioo)
                drag13ImageView.setImageResource(R.drawable.abecedarioa)
                drag14ImageView.setImageResource(R.drawable.abecedarioz)
                drag15ImageView.setImageResource(R.drawable.abecedariob)
                drag16ImageView.setImageResource(R.drawable.abecedariou)
                drag17ImageView.setImageResource(R.drawable.abecedariol)
                drag18ImageView.setImageResource(R.drawable.abecedarioy)
                drag19ImageView.setImageResource(R.drawable.abecedariov)
                drag20ImageView.setImageResource(R.drawable.abecedariop)
            }
            7->{
                imageView.setImageResource(R.drawable.ave)
                mediaPlayer= MediaPlayer.create(context, R.raw.ave)
                mediaPlayer?.start()
                drag10ImageView.setImageResource(R.drawable.abecedarioa)
                drag11ImageView.setImageResource(R.drawable.abecedariov)
                drag12ImageView.setImageResource(R.drawable.abecedarioe)
                drag13ImageView.setImageResource(R.drawable.abecedarioo)
                drag14ImageView.setImageResource(R.drawable.abecedarioz)
                drag15ImageView.setImageResource(R.drawable.abecedariop)
                drag16ImageView.setImageResource(R.drawable.abecedariob)
                drag17ImageView.setImageResource(R.drawable.abecedariou)
                drag18ImageView.setImageResource(R.drawable.abecedarioy)
                drag19ImageView.setImageResource(R.drawable.abecedariov)
                drag20ImageView.setImageResource(R.drawable.abecedariop)
            }
            8->{
                imageView.setImageResource(R.drawable.sol)
                mediaPlayer= MediaPlayer.create(context, R.raw.sol)
                mediaPlayer?.start()
                drag10ImageView.setImageResource(R.drawable.abecedarios)
                drag11ImageView.setImageResource(R.drawable.abecedarioo)
                drag12ImageView.setImageResource(R.drawable.abecedariol)
                drag13ImageView.setImageResource(R.drawable.abecedarior)
                drag14ImageView.setImageResource(R.drawable.abecedariou)
                drag15ImageView.setImageResource(R.drawable.abecedariob)
                drag16ImageView.setImageResource(R.drawable.abecedariop)
                drag17ImageView.setImageResource(R.drawable.abecedarioi)
                drag18ImageView.setImageResource(R.drawable.abecedarioy)
                drag19ImageView.setImageResource(R.drawable.abecedariov)
                drag20ImageView.setImageResource(R.drawable.abecedariop)
            }
        }
    }

    fun wordCaseOne(){
        when(num){
            1->{
                target10ImageView.setImageResource(R.drawable.abecedarioo)
                drag10ImageView.isVisible = false
            }
            2->{
                target10ImageView.setImageResource(R.drawable.abecedarioo)
                drag10ImageView.isVisible = false
            }
            3->{
                target10ImageView.setImageResource(R.drawable.abecedarior)
                drag10ImageView.isVisible = false
            }
            4->{
                target10ImageView.setImageResource(R.drawable.abecedariop)
                drag10ImageView.isVisible = false
            }
            5->{
                target10ImageView.setImageResource(R.drawable.abecedariou)
                drag10ImageView.isVisible = false
            }
            6->{
                target10ImageView.setImageResource(R.drawable.abecedarior)
                drag10ImageView.isVisible = false
            }
            7->{
                target10ImageView.setImageResource(R.drawable.abecedarioa)
                drag10ImageView.isVisible = false
            }
            8->{
                target10ImageView.setImageResource(R.drawable.abecedarios)
                drag10ImageView.isVisible = false
            }
        }
    }

    fun wordCaseTwo(){
        when(num){
            1->{
                target11ImageView.setImageResource(R.drawable.abecedariol)
                drag11ImageView.isVisible = false
            }
            2->{
                target11ImageView.setImageResource(R.drawable.abecedarios)
                drag11ImageView.isVisible = false
            }
            3->{
                target11ImageView.setImageResource(R.drawable.abecedarioe)
                drag11ImageView.isVisible = false
            }
            4->{
                target11ImageView.setImageResource(R.drawable.abecedarioe)
                drag11ImageView.isVisible = false
            }
            5->{
                target11ImageView.setImageResource(R.drawable.abecedariov)
                drag11ImageView.isVisible = false
            }
            6->{
                target11ImageView.setImageResource(R.drawable.abecedarioi)
                drag11ImageView.isVisible = false
            }
            7->{
                target11ImageView.setImageResource(R.drawable.abecedariov)
                drag11ImageView.isVisible = false
            }
            8->{
                target11ImageView.setImageResource(R.drawable.abecedarioo)
                drag11ImageView.isVisible = false
            }
        }
    }

    fun wordCaseTrhee(){
        when(num){
            1->{
                target12ImageView.setImageResource(R.drawable.abecedarioa)
                drag12ImageView.isVisible = false
            }
            2->{
                target12ImageView.setImageResource(R.drawable.abecedarioo)
                drag12ImageView.isVisible = false
            }
            3->{
                target12ImageView.setImageResource(R.drawable.abecedarioy)
                drag12ImageView.isVisible = false
            }
            4->{
                target12ImageView.setImageResource(R.drawable.abecedarioz)
                drag12ImageView.isVisible = false
            }
            5->{
                target12ImageView.setImageResource(R.drawable.abecedarioa)
                drag12ImageView.isVisible = false
            }
            6->{
                target12ImageView.setImageResource(R.drawable.abecedarioo)
                drag12ImageView.isVisible = false
            }
            7->{
                target12ImageView.setImageResource(R.drawable.abecedarioe)
                drag12ImageView.isVisible = false
            }
            8->{
                target12ImageView.setImageResource(R.drawable.abecedariol)
                drag12ImageView.isVisible = false
            }
        }
    }

    fun revelateLetter(){
        drag10ImageView.isVisible = true
        drag11ImageView.isVisible = true
        drag12ImageView.isVisible = true
    }

    fun cleanTarget(){
        target10ImageView.setImageResource(R.drawable.btn_letters)
        target11ImageView.setImageResource(R.drawable.btn_letters)
        target12ImageView.setImageResource(R.drawable.btn_letters)
    }

    fun updateScore(){
        score += 1
        when(score){
            3->{
                num += 1
                Log.e("Revision", num.toString())
                revelateLetter()
                setLetters()
                cleanTarget()
            }
            6->{
                num += 1
                Log.e("Revision", num.toString())
                revelateLetter()
                setLetters()
                cleanTarget()
            }
            9->{
                num += 1
                Log.e("Revision", num.toString())
                revelateLetter()
                setLetters()
                cleanTarget()
            }
            12->{
                num += 1
                Log.e("Revision", num.toString())
                revelateLetter()
                setLetters()
                cleanTarget()
            }
            15->{
                num += 1
                Log.e("Revision", num.toString())
                revelateLetter()
                setLetters()
                cleanTarget()
            }
            18->{
                num += 1
                Log.e("Revision", num.toString())
                revelateLetter()
                setLetters()
                cleanTarget()
            }
            21->{
                num += 1
                Log.e("Revision", num.toString())
                revelateLetter()
                setLetters()
                cleanTarget()
            }
            24->{
                num += 1
                Log.e("Revision", num.toString())
                revelateLetter()
                setLetters()
                cleanTarget()
                if(score == 24){
                    val monedas = coins +8
                    updateCoins(monedas)
                    back()
                }
            }
        }
    }
    fun back(){
        findNavController().navigate(R.id.action_nav_lettersFragment_to_nav_mainScreenFragment)

    }

    private class MyDragShadowBuilder(val v: View) : View.DragShadowBuilder(v) {

        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            size.set(view.width, view.height)
            touch.set(view.width / 2, view.height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            v.draw(canvas)
        }
    }

    private val longClickListener = View.OnLongClickListener { v ->
        val item = ClipData.Item(v.tag as? CharSequence)

        val dragData = ClipData(
            v.tag as CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            item
        )
        val myShadow = MyDragShadowBuilder(v)
        v.startDragAndDrop(dragData, myShadow,null,0)
        true
    }

    private val dragListener = View.OnDragListener { v, event ->
        val receiverView: ImageView = v as ImageView

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                v.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                true

            DragEvent.ACTION_DRAG_EXITED -> {
                if (event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }

            DragEvent.ACTION_DROP -> {
                if (event.clipDescription.label == receiverView.tag as String) {
                    wordCaseOne()
                    updateScore()
                    Log.e("SCORE", score.toString())
                } else {
                    if (event.clipDescription.label == receiverView.tag as String) {
                        wordCaseOne()
                        updateScore()
                        Log.e("SCORE", score.toString())
                    }
                }
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                true
            }

            else -> {
                false
            }
        }
    }

    private val dragListener2 = View.OnDragListener { v, event ->
        val receiverView: ImageView = v as ImageView

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                v.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                true

            DragEvent.ACTION_DRAG_EXITED -> {
                if (event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }

            DragEvent.ACTION_DROP -> {
                if (event.clipDescription.label == receiverView.tag as String) {
                    wordCaseTwo()
                    updateScore()
                    Log.e("SCORE", score.toString())
                } else {
                    if (event.clipDescription.label == receiverView.tag as String) {
                        wordCaseTwo()
                        updateScore()
                        Log.e("SCORE", score.toString())
                    }
                }
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                true
            }

            else -> {
                false
            }
        }
    }

    private val dragListener3 = View.OnDragListener { v, event ->
        val receiverView: ImageView = v as ImageView

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                v.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                true

            DragEvent.ACTION_DRAG_EXITED -> {
                if (event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }

            DragEvent.ACTION_DROP -> {
                if (event.clipDescription.label == receiverView.tag as String) {
                    wordCaseTrhee()
                    updateScore()
                    Log.e("SCORE", score.toString())
                } else {
                    if (event.clipDescription.label == receiverView.tag as String) {
                    wordCaseTrhee()
                    updateScore()
                    Log.e("SCORE", score.toString())
                    }
                }
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                true
            }

            else -> {
                false
            }
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