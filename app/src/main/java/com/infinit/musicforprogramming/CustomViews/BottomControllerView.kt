package com.infinit.musicforprogramming.CustomViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class BottomControllerView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var isPlaying = false

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create("", Typeface.BOLD)
        setBackgroundColor(Color.BLUE)
    }

    private val trPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        drawState(canvas)
    }

    fun drawState(canvas: Canvas?){
        if (isPlaying)
            drawStop(canvas)
        else drawPlay(canvas)
    }
    fun drawPlay(canvas: Canvas?){
        drawTriangle(canvas,trPaint,100,50,200)
    }
    fun drawStop(canvas: Canvas?){
        canvas?.drawRect(100f,50f,300f,250f,paint)
    }

    override fun performClick(): Boolean {
        isPlaying = !isPlaying
        invalidate()
        return super.performClick()
    }

    fun drawTriangle(canvas: Canvas?, paint: Paint, x: Int, y: Int, width: Int) {
        val halfWidth = width / 2
        val path = Path()
        path.moveTo(x.toFloat(), y.toFloat()) // Top
        path.lineTo((x + width).toFloat(), (y + halfWidth).toFloat()) // Bottom left
        path.lineTo(x.toFloat(), (y + width).toFloat()) // Bottom right
        path.lineTo(x.toFloat(), y.toFloat()) // Back to Top
        path.close()
        canvas?.drawPath(path, paint)
    }

}