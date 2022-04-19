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
import android.os.Build
import android.view.DragEvent
import android.widget.ImageView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_letters.*


class LettersFragment : Fragment(R.layout.fragment_letters) {
    private lateinit var binding: FragmentLettersBinding
    private var nombre:String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindeo de elementos del fragment
        binding = FragmentLettersBinding.bind(view)
        //recuperacion de argumentos

        drag10ImageView.setOnLongClickListener(longClickListener)
        drag11ImageView.setOnLongClickListener(longClickListener)
        drag12ImageView.setOnLongClickListener(longClickListener)

        target10ImageView.setOnDragListener(dragListener)
        target11ImageView.setOnDragListener(dragListener2)
        target12ImageView.setOnDragListener(dragListener3)
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
                    target10ImageView.setImageResource(R.drawable.abecedariou)
                    drag10ImageView.isVisible = false
                } else {
                    receiverView.setColorFilter(Color.RED)
                }
                v.invalidate()
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
                    target11ImageView.setImageResource(R.drawable.abecedariov)
                    drag11ImageView.isVisible = false
                } else {
                    receiverView.setColorFilter(Color.RED)
                }
                v.invalidate()
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
                    target12ImageView.setImageResource(R.drawable.abecedarioa)
                    drag12ImageView.isVisible = false
                } else {
                    receiverView.setColorFilter(Color.RED)
                }
                v.invalidate()
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
}