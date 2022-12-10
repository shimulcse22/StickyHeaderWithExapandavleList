package com.example.expandablerecyclerview

import android.graphics.Canvas
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.MeasureSpec
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.State
import com.example.expandablerecyclerview.databinding.ParentRowBinding
import com.example.expandablerecyclerview.databinding.ViewStickyHeaderBinding

class StickyHeaderDecoration(private val adapter: ExapndleAdapter, root: View) :
    ItemDecoration() {

    private val headerBinding by lazy { ParentRowBinding.inflate(LayoutInflater.from(root.context)) }

    private val headerView: View
        get() = headerBinding.root

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: State) {
        super.onDrawOver(canvas, parent, state)

        val topChild = parent.getChildAt(0)
        val secondChild = parent.getChildAt(1)

        parent.getChildAdapterPosition(topChild)
            .let { topChildPosition ->
                val header = adapter.getHeaderForCurrentPosition(topChildPosition)
                Log.d("SHIMUL2222222222", header.toString())
                headerBinding.parentTitle.text = header.toString()

                layoutHeaderView(topChild)

                canvas.drawHeaderView(topChild, secondChild)
            }
    }

    private fun layoutHeaderView(topView: View?) {
        topView?.let {
            headerView.measure(
                MeasureSpec.makeMeasureSpec(topView.width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
            )
            headerView.layout(topView.left, 0, topView.right, headerView.measuredHeight)
        }
    }

    private fun Canvas.drawHeaderView(topView: View?, secondChild: View?) {
        save()
        translate(0f, calculateHeaderTop(topView, secondChild))
        headerView.draw(this)
        restore()
    }

    private fun calculateHeaderTop(topView: View?, secondChild: View?): Float =
        secondChild?.let { secondView ->
            val threshold = getPixels(8, headerBinding.root.context) + headerView.bottom
            if (secondView.findViewById<View>(headerView.id)?.visibility != View.GONE && secondView.top <= threshold) {
                (secondView.top - threshold).toFloat()
            } else {
                maxOf(topView?.top ?: 0, 0).toFloat()
            }
        } ?: maxOf(topView?.top ?: 0, 0).toFloat()
}
