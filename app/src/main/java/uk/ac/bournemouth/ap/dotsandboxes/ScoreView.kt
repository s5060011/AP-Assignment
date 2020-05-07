package uk.ac.bournemouth.ap.dotsandboxes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ScoreView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr)

    private val playerScore1 = 0
    private val playerScore2 = 0

    private val border = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 6F
    }
    private val text = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
        textSize = 150F
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), border)
        canvas.drawText("Player1: "+playerScore1, (width / 2).toFloat(), 250.toFloat(), text)
        canvas.drawText("Player2: "+playerScore2, (width / 2).toFloat(), 400.toFloat(), text)
    }
}