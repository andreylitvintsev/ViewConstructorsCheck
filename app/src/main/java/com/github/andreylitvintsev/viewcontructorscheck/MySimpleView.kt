package com.github.andreylitvintsev.viewcontructorscheck

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class MySimpleView : View {

    private var linesNumber: Int = 0
    private var step: Float = 0f

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        init(context, attributeSet, R.attr.mySimpleViewStyle)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init(context, attributeSet, defStyleAttr)
    }

//    constructor(context: Context, attributeSet: AttributeSet?,  defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes)

    private fun init(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int = 0) {
        context.theme.obtainStyledAttributes(attributeSet, R.styleable.MySimpleView, defStyleAttr, 0).apply {
            try {
                linesNumber = this.getInt(R.styleable.MySimpleView_stubAttribute, 0)
            } finally {
                recycle()
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        step = width / (linesNumber + 1f)
    }

    override fun onDraw(canvas: Canvas?) = drawLines(canvas)

    private fun drawLines(canvas: Canvas?) {
        for (i in 1..linesNumber) {
            canvas?.drawLine(
                i * step,
                0f,
                i * step,
                height.toFloat(),
                paint
            )
        }
    }

}
