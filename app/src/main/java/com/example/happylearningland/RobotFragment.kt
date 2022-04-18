package com.example.happylearningland


import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.happylearningland.databinding.FragmentRobotBinding
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.view.DragEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_robot.*

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
        super.onCreate(savedInstanceState)

        drag1ImageView.setOnLongClickListener(longClickListener)
        drag2ImageView.setOnLongClickListener(longClickListener)
        drag3ImageView.setOnLongClickListener(longClickListener)
        drag4ImageView.setOnLongClickListener(longClickListener)
        drag5ImageView.setOnLongClickListener(longClickListener)
        drag6ImageView.setOnLongClickListener(longClickListener)

        target1ImageView.setOnDragListener(dragListener)
        target2ImageView.setOnDragListener(dragListener)
        target3ImageView.setOnDragListener(dragListener)
        target4ImageView.setOnDragListener(dragListener)
        target5ImageView.setOnDragListener(dragListener)
        target6ImageView.setOnDragListener(dragListener)
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(dragData, myShadow,null,0)
        } else {
            v.startDrag(dragData, myShadow,null,0)
        }

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
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.tag

                } else {
                    receiverView.setColorFilter(Color.RED)
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
}