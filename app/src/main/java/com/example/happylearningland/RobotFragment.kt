package com.example.happylearningland

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentRobotBinding
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.media.MediaPlayer
import android.util.Log
import android.view.DragEvent
import android.widget.ImageView
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
import kotlinx.android.synthetic.main.fragment_robot.*

class RobotFragment : Fragment(R.layout.fragment_robot) {

    private lateinit var binding: FragmentRobotBinding
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var imageCharacter: String
    private var coins = 0
    private lateinit var tasks: List<String>

    var contador = 1
    var score = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentRobotBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        db = Firebase.database.reference

        getData(db)

        super.onCreate(savedInstanceState)

        drag1ImageView.setOnLongClickListener(longClickListener)
        drag2ImageView.setOnLongClickListener(longClickListener)
        drag3ImageView.setOnLongClickListener(longClickListener)
        drag4ImageView.setOnLongClickListener(longClickListener)
        drag5ImageView.setOnLongClickListener(longClickListener)
        drag6ImageView.setOnLongClickListener(longClickListener)

        target1ImageView.setOnDragListener(dragListener)
        target2ImageView.setOnDragListener(dragListener2)
        target3ImageView.setOnDragListener(dragListener3)
        target4ImageView.setOnDragListener(dragListener4)
        target5ImageView.setOnDragListener(dragListener5)
        target6ImageView.setOnDragListener(dragListener6)

        btnDerecha.setOnClickListener {

            if (contador == 5){
                contador = 1
            }else{
                contador += 1
            }
            Log.d("REVISION", contador.toString())
            images()
        }
        btnIzquierda.setOnClickListener {
            contador -= 1
            if (contador == 0){
                contador = 5
            }
            Log.d("REVISION", contador.toString())
            images()
        }
        btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_nav_robotFragment_to_nav_mainScreenFragment)
        }
        instrucciones.setOnClickListener {
            mediaPlayer= MediaPlayer.create(context, R.raw.instruccionesrobot)
            mediaPlayer?.start()
        }
        btnPlay.setOnClickListener {
            if (score == 6){
                score = 0
                Log.e("CUMPLIMIENTO","SE COMPLETARON 5 PTS")
                mediaPlayer= MediaPlayer.create(context, R.raw.robotarmado)
                mediaPlayer?.start()
            }else{
                mediaPlayer= MediaPlayer.create(context, R.raw.errorrobot)
                mediaPlayer?.start()
                Log.e("INCUMPLIMIENTO","NO SE COMPLETARON LOS 5 PTS")
            }
        }
        btnReset.setOnClickListener {
            target1ImageView.setImageResource(R.drawable.baseuno)
            target2ImageView.setImageResource(R.drawable.basedos)
            target3ImageView.setImageResource(R.drawable.basetres)
            target4ImageView.setImageResource(R.drawable.basecuatro)
            target5ImageView.setImageResource(R.drawable.basecinco)
            target6ImageView.setImageResource(R.drawable.baseseis)

            drag1ImageView.isVisible = true
            drag2ImageView.isVisible = true
            drag3ImageView.isVisible = true
            drag4ImageView.isVisible = true
            drag5ImageView.isVisible = true
            drag6ImageView.isVisible = true
        }
    }
    fun score(){
        score += 1
    }
    fun images(){
        when(contador){
            1->{
                Log.d("REVISION", "IMAGENES 1")
                drag6ImageView.setImageResource(R.drawable.robotgrisc)
                drag1ImageView.setImageResource(R.drawable.robotgris)
                drag4ImageView.setImageResource(R.drawable.robotgrist)
                drag2ImageView.setImageResource(R.drawable.robotgrisu)
                drag5ImageView.setImageResource(R.drawable.robotgriss)
                drag3ImageView.setImageResource(R.drawable.robotgrisd)
            }
            2->{
                Log.d("REVISION", "IMAGENES 2")
                drag6ImageView.setImageResource(R.drawable.robotamarillo6)
                drag1ImageView.setImageResource(R.drawable.robotamarillo1)
                drag4ImageView.setImageResource(R.drawable.robotamarillo4)
                drag2ImageView.setImageResource(R.drawable.robotamarillo3)
                drag5ImageView.setImageResource(R.drawable.robotamarillo5)
                drag3ImageView.setImageResource(R.drawable.robotamarillo2)
            }
            3->{
                drag6ImageView.setImageResource(R.drawable.robotoriginal6)
                drag1ImageView.setImageResource(R.drawable.robotoriginal1)
                drag4ImageView.setImageResource(R.drawable.robotoriginal4)
                drag2ImageView.setImageResource(R.drawable.robotoriginal3)
                drag5ImageView.setImageResource(R.drawable.robotoriginal5)
                drag3ImageView.setImageResource(R.drawable.robotoriginal2)
            }
            4->{
                drag6ImageView.setImageResource(R.drawable.robotverde66)
                drag1ImageView.setImageResource(R.drawable.robotverde11)
                drag4ImageView.setImageResource(R.drawable.robotverde44)
                drag2ImageView.setImageResource(R.drawable.robotverde33)
                drag5ImageView.setImageResource(R.drawable.robotverde55)
                drag3ImageView.setImageResource(R.drawable.robotverde22)
            }
            5->{
                drag6ImageView.setImageResource(R.drawable.robotnegro66)
                drag1ImageView.setImageResource(R.drawable.robotnegro11)
                drag4ImageView.setImageResource(R.drawable.robotnegro44)
                drag2ImageView.setImageResource(R.drawable.robotnegro33)
                drag5ImageView.setImageResource(R.drawable.robotnegro55)
                drag3ImageView.setImageResource(R.drawable.robotnegro22)
            }
        }
    }
    fun setHead(){
        when(contador){
            1->target1ImageView.setImageResource(R.drawable.robotgris)
            2->target1ImageView.setImageResource(R.drawable.robotamarillo1)
            3->target1ImageView.setImageResource(R.drawable.robotoriginal1)
            4->target1ImageView.setImageResource(R.drawable.robotverde11)
            5-> target1ImageView.setImageResource(R.drawable.robotnegro11)
        }
    }
    fun setTorso(){
        when(contador){
            1->target2ImageView.setImageResource(R.drawable.robotgrisu)
            2->target2ImageView.setImageResource(R.drawable.robotamarillo3)
            3->target2ImageView.setImageResource(R.drawable.robotoriginal3)
            4->target2ImageView.setImageResource(R.drawable.robotverde33)
            5->target2ImageView.setImageResource(R.drawable.robotnegro33)
        }
    }
    fun setRigthLeg(){
        when(contador){
            1->target5ImageView.setImageResource(R.drawable.robotgrisc)
            2->target5ImageView.setImageResource(R.drawable.robotamarillo5)
            3->target5ImageView.setImageResource(R.drawable.robotoriginal5)
            4->target5ImageView.setImageResource(R.drawable.robotverde55)
            5->target5ImageView.setImageResource(R.drawable.robotnegro55)
        }
    }
    fun setLeftLeg(){
        when(contador){
            1->target6ImageView.setImageResource(R.drawable.robotgriss)
            2->target6ImageView.setImageResource(R.drawable.robotamarillo6)
            3->target6ImageView.setImageResource(R.drawable.robotoriginal6)
            4->target6ImageView.setImageResource(R.drawable.robotverde66)
            5->target6ImageView.setImageResource(R.drawable.robotnegro66)
        }
    }
    fun setRigthHand(){
        when(contador){
            1->target3ImageView.setImageResource(R.drawable.robotgrisd)
            2->target3ImageView.setImageResource(R.drawable.robotamarillo2)
            3->target3ImageView.setImageResource(R.drawable.robotoriginal2)
            4->target3ImageView.setImageResource(R.drawable.robotverde22)
            5->target3ImageView.setImageResource(R.drawable.robotnegro22)
        }
    }
    fun setLeftHand(){
        when(contador){
            1->target4ImageView.setImageResource(R.drawable.robotgrist)
            2->target4ImageView.setImageResource(R.drawable.robotamarillo4)
            3->target4ImageView.setImageResource(R.drawable.robotoriginal4)
            4->target4ImageView.setImageResource(R.drawable.robotverde44)
            5->target4ImageView.setImageResource(R.drawable.robotnegro44)
        }
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
        val receiverView:ImageView = v as ImageView

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
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }
            DragEvent.ACTION_DROP -> {
                receiverView.setColorFilter(Color.TRANSPARENT)
                if(event.clipDescription.label == receiverView.tag as String) {
                    /**CABEZA*/
                    setHead()
                    score()
                    drag1ImageView.isVisible = false
                    mediaPlayer= MediaPlayer.create(context, R.raw.cabeza)
                    mediaPlayer?.start()
                } else {
                    receiverView.setColorFilter(Color.RED)
                    mediaPlayer= MediaPlayer.create(context, R.raw.piezaerronea)
                    mediaPlayer?.start()
                    if(event.clipDescription.label == receiverView.tag as String) {
                        /**CABEZA*/
                        setHead()
                        score()
                        drag1ImageView.isVisible = false

                        mediaPlayer= MediaPlayer.create(context, R.raw.cabeza)
                        mediaPlayer?.start()
                    }
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.invalidate()
                true
            }
            else -> {
                false
            }
        }
    }

    private val dragListener3 = View.OnDragListener { v, event ->
        val receiverView:ImageView = v as ImageView

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
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }
            DragEvent.ACTION_DROP -> {
                receiverView.setColorFilter(Color.TRANSPARENT)
                if(event.clipDescription.label == receiverView.tag as String) {
                    /**MANO DERECHA*/
                    setRigthHand()
                    score()
                    drag3ImageView.isVisible = false
                    mediaPlayer= MediaPlayer.create(context, R.raw.brazoderecho)
                    mediaPlayer?.start()
                } else {
                    mediaPlayer= MediaPlayer.create(context, R.raw.piezaerronea)
                    mediaPlayer?.start()
                    receiverView.setColorFilter(Color.RED)
                    if(event.clipDescription.label == receiverView.tag as String) {
                        /**MANO DERECHA*/
                        setRigthHand()
                        score()
                        drag3ImageView.isVisible = false
                        mediaPlayer= MediaPlayer.create(context, R.raw.brazoderecho)
                        mediaPlayer?.start()
                    }
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.invalidate()
                true
            }
            else -> {
                false
            }
        }
    }

    private val dragListener2 = View.OnDragListener { v, event ->
        val receiverView:ImageView = v as ImageView

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
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }
            DragEvent.ACTION_DROP -> {
                receiverView.setColorFilter(Color.TRANSPARENT)
                if(event.clipDescription.label == receiverView.tag as String) {
                    /**TORSO*/
                    setTorso()
                    score()
                    drag2ImageView.isVisible = false
                    mediaPlayer= MediaPlayer.create(context, R.raw.torso)
                    mediaPlayer?.start()
                } else {
                    mediaPlayer= MediaPlayer.create(context, R.raw.piezaerronea)
                    mediaPlayer?.start()
                    receiverView.setColorFilter(Color.RED)
                    if(event.clipDescription.label == receiverView.tag as String) {
                        /**TORSO*/
                        setTorso()
                        score()
                        drag2ImageView.isVisible = false
                        mediaPlayer= MediaPlayer.create(context, R.raw.torso)
                        mediaPlayer?.start()
                    }
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.invalidate()
                true
            }
            else -> {
                false
            }
        }
    }

    private val dragListener4 = View.OnDragListener { v, event ->
        val receiverView:ImageView = v as ImageView

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
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }
            DragEvent.ACTION_DROP -> {
                receiverView.setColorFilter(Color.TRANSPARENT)
                if(event.clipDescription.label == receiverView.tag as String) {
                    /**MANO IZQUIERDA*/
                    setLeftHand()
                    score()
                    drag4ImageView.isVisible = false
                    mediaPlayer= MediaPlayer.create(context, R.raw.brazoizquierdo)
                    mediaPlayer?.start()
                } else {
                    mediaPlayer= MediaPlayer.create(context, R.raw.piezaerronea)
                    mediaPlayer?.start()
                    receiverView.setColorFilter(Color.RED)
                    if(event.clipDescription.label == receiverView.tag as String) {
                        /**MANO IZQUIERDA*/
                        setLeftHand()
                        score()
                        drag4ImageView.isVisible = false
                        mediaPlayer= MediaPlayer.create(context, R.raw.brazoizquierdo)
                        mediaPlayer?.start()
                    }
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.invalidate()
                true
            }
            else -> {
                false
            }
        }
    }

    private val dragListener5 = View.OnDragListener { v, event ->
        val receiverView:ImageView = v as ImageView

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
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }
            DragEvent.ACTION_DROP -> {
                receiverView.setColorFilter(Color.TRANSPARENT)
                if(event.clipDescription.label == receiverView.tag as String) {
                    /**PIERNA DERECHA*/
                    setRigthLeg()
                    score()
                    drag5ImageView.isVisible = false
                    mediaPlayer= MediaPlayer.create(context, R.raw.piernaderecha)
                    mediaPlayer?.start()
                } else {
                    mediaPlayer= MediaPlayer.create(context, R.raw.piezaerronea)
                    mediaPlayer?.start()
                    receiverView.setColorFilter(Color.RED)
                    if(event.clipDescription.label == receiverView.tag as String) {
                        /**PIERNA DERECHA*/
                        setRigthLeg()
                        score()
                        drag5ImageView.isVisible = false
                        mediaPlayer= MediaPlayer.create(context, R.raw.piernaderecha)
                        mediaPlayer?.start()
                    }
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.invalidate()
                true
            }
            else -> {
                false
            }
        }
    }

    private val dragListener6 = View.OnDragListener { v, event ->
        val receiverView:ImageView = v as ImageView

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
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    v.invalidate()
                }
                true
            }
            DragEvent.ACTION_DROP -> {
                receiverView.setColorFilter(Color.TRANSPARENT)
                if(event.clipDescription.label == receiverView.tag as String) {
                    /**PIERNA IZQUIERDA*/
                    setLeftLeg()
                    score()
                    drag6ImageView.isVisible = false
                    mediaPlayer= MediaPlayer.create(context, R.raw.piernaizquierda)
                    mediaPlayer?.start()
                } else {
                    mediaPlayer= MediaPlayer.create(context, R.raw.piezaerronea)
                    mediaPlayer?.start()
                    receiverView.setColorFilter(Color.RED)
                    if(event.clipDescription.label == receiverView.tag as String) {
                        /**PIERNA IZQUIERDA*/
                        setLeftLeg()
                        score()
                        drag6ImageView.isVisible = false
                        mediaPlayer= MediaPlayer.create(context, R.raw.piernaizquierda)
                        mediaPlayer?.start()
                    }
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.invalidate()
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