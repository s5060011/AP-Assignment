package uk.ac.bournemouth.ap.dotsandboxes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import org.example.student.dotsboxgame.StudentDotsBoxGame

class GameView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
                                                                                   )

    private val colCount = 8
    private val rowCount = 10

    private var selectedRowLine = 0
    private var selectedColLine = 1

    private val playerScore1 = 0
    private val playerScore2 = 0

    private var background: Paint
    private var border: Paint
    private var dotAndText: Paint
    private var mNoPlayerPaint: Paint
    private var player1Paint: Paint
    private var player2Paint: Paint
    private var boxText: Paint

    init {
        background = Paint().apply {
            style = Paint.Style.FILL_AND_STROKE
            color = Color.WHITE
        }
        border = Paint().apply {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth = 6F
        }
        dotAndText = Paint().apply {
            style = Paint.Style.FILL_AND_STROKE
            color = Color.BLACK
            strokeWidth = 15F
            textSize = 150F
            textAlign = Paint.Align.CENTER
        }
        mNoPlayerPaint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 6F
            color = Color.GRAY
        }
        player1Paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 8F
            color = Color.RED
        }
        player2Paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 8F
            color = Color.BLUE
        }
        boxText = Paint().apply {
            style = Paint.Style.FILL_AND_STROKE
            color = Color.BLACK
            textSize = 10F
            textAlign = Paint.Align.CENTER
        }
    }

    /**
    override fun onDraw(canvas: Canvas) {
    val chosenDiameter: Float

    val viewWidth: Float = width.toFloat()
    val viewHeight: Float = height.toFloat()

    val diameterX: Float = viewWidth / colCount.toFloat()
    val diameterY: Float = viewHeight / rowCount.toFloat()

    // Choose the smallest of the two
    if (diameterX < diameterY)
    chosenDiameter = diameterX
    else
    chosenDiameter = diameterY

    canvas.drawRect(0.toFloat(), 0.toFloat(), viewWidth, viewHeight, background)

    for (x in 0 until colCount + 1) {
    for (y in 0 until rowCount + 1) {
    canvas.drawLine(
    0F,
    x * diameterX,
    height.toFloat(),
    x * diameterX,
    mNoPlayerPaint
    )
    canvas.drawLine(
    y * diameterY,
    0F,
    y * diameterY,
    width.toFloat(),
    mNoPlayerPaint
    )
    }
    }
    }*/


    override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas)

        val chosenDiameter: Float
        var tokenAtPos: Int
        // var paint1: Paint
        // var paint2: Paint

        val viewWidth: Float = width.toFloat()
        val viewHeight: Float = height.toFloat()

        val diameterX: Float = viewWidth / colCount.toFloat()
        val diameterY: Float = viewHeight / rowCount.toFloat()

        // Choose the smallest of the two
        if (diameterX < diameterY)
            chosenDiameter = diameterX
        else
            chosenDiameter = diameterY
        /**
        // Draw the game board
        canvas.drawRect(0.toFloat(), 0.toFloat(), viewWidth, viewHeight, background)

        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), border)
        canvas.drawText("Player1: "+playerScore1, (width / 2).toFloat(), 250.toFloat(), dotAndText)
        canvas.drawText("Player2: "+playerScore2, (width / 2).toFloat(), 400.toFloat(), dotAndText)*/

        val radius = chosenDiameter / 2
        // Draw the circles on the game board

        for (col in 0 until colCount - 1) {
            for (row in 0 until rowCount) {

                val cx = chosenDiameter * col + radius
                val cy = chosenDiameter * row + radius
                val nx = chosenDiameter * (col + 1) + radius
                //val ny = chosenDiameter * (row + 1) + radius
                canvas.drawLine(cx, cy, nx, cy, mNoPlayerPaint)
                // canvas.drawLine(cx, cy, cx, ny,mNoPlayerPaint)

            }
        }

        for (col in 0 until colCount) {
            for (row in 0 until rowCount - 1) {

                val cx = chosenDiameter * col + radius
                val cy = chosenDiameter * row + radius
                // val nx = chosenDiameter * (col + 1) + radius
                val ny = chosenDiameter * (row + 1) + radius
                // canvas.drawLine(cx, cy, nx, cy,mNoPlayerPaint)
                canvas.drawLine(cx, cy, cx, ny, mNoPlayerPaint)

            }
        }

        for (col in 0 until colCount) {
            for (row in 0 until rowCount) {
                // Calculate the co-ordinates of the circle
                val cx = chosenDiameter * col + radius
                val cy = chosenDiameter * row + radius
                canvas.drawCircle(cx, cy, 15.toFloat(), dotAndText)
            }
        }

        if (selectedRowLine == -1 || selectedColLine == -1) return
        val cx = chosenDiameter * selectedColLine + radius
        val cy = chosenDiameter * selectedRowLine + radius
        val nx = chosenDiameter * (selectedColLine + 1) + radius
        val ny = chosenDiameter * (selectedRowLine + 1) + radius
        //if ()
/**
            for (col in 0 until colCount - 1) {
                for (row in 0 until rowCount - 1) {
                  //  val cx = chosenDiameter * col + radius
                 //   val cy = chosenDiameter * row + radius
                    if (row == selectedRowLine && col != selectedColLine) {
                       // val nx = chosenDiameter * (col + 1) + radius
                      //  val ny = chosenDiameter * (row + 1) + radius
                        canvas.drawLine(cx, cy, nx, cy, player1Paint)
                    } else if (row != selectedRowLine && col == selectedColLine) {
                       // val ny = chosenDiameter * (row + 1) + radius
                        canvas.drawLine(cx, cy, cx, ny, player1Paint)
                    }
                }
            }*/
    }

    private val myGestureDetector = GestureDetector(context, myGestureListener())

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return myGestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    inner  class myGestureListener: GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val colWidth = width / colCount
            val rowHeight = height / rowCount
            val colTouch = e.x.toInt() / colWidth
            val rowTouch = e.y.toInt() / rowHeight

           // StudentDotsBoxGame.playToken(colTouch, 1)
           // StudentDotsBoxGame.playToken(rowTouch, 1)

            invalidate()
            return true
        }
    }

        // fillLines(canvas)

        /**
        for (col in 0 until colCount + 1) {
        for (row in 0 until rowCount + 1) {
        paint2 = mNoPlayerPaint
        // Calculate the co-ordinates of the circle
        val cx = viewWidth / colCount * col
        val cy = viewHeight / rowCount * row
        val nx = viewWidth / colCount * col + 1
        val ny = viewHeight / rowCount * row
        canvas.drawLine(cx, cy, nx, ny, paint2)
        }
        }
        for (col in 0 until colCount + 1) {
        for (row in 0 until rowCount + 1) {
        paint2 = mNoPlayerPaint
        // Calculate the co-ordinates of the circle
        val cx = viewWidth / colCount * col
        val cy = viewHeight / rowCount * row
        val nx = viewWidth / colCount * col
        val ny = viewHeight / rowCount * row + 1
        canvas.drawLine(cx, cy, nx, ny, paint2)
        }
        }

        for (col in 0 until colCount) {
        for (row in 0 until rowCount) {
        paint1 = dot
        paint2 = mNoPlayerPaint
        // Calculate the co-ordinates of the circle
        val cx = chosenDiameter * col + radius
        val cy = chosenDiameter * row + radius
        val nx = chosenDiameter * (col + 1) + radius
        val ny = chosenDiameter * (row + 1) + radius
        val mx = viewWidth / colCount * col + 1
        val my = viewHeight / rowCount * row + 1
        canvas.drawCircle(cx, cy, 15.toFloat(), paint1)
        canvas.drawLine(cx, cy, nx, cy, paint2)
        canvas.drawLine(cx, cy, cx, ny, paint2)
        }
        }*/


    // private fun fillLines(canvas: Canvas, row: Int, col: Int, paint: Paint) {

    // }

    // private fun fillLineH(canvas: Canvas, row: Int, col: Int, paint: Paint) {
    //    canvas.drawLine(col * )
    //}

    // private fun fillLineV(canvas: Canvas, row: Int, col: Int, paint: Paint) {
    //    canvas.drawLine()
    //}
    /**
    override fun onTouchEvent(event: MotionEvent): Boolean {
    return when (event.action) {
    MotionEvent.ACTION_DOWN -> {
    handleTouchEvent(event.x, event.y)
    true
    }
    else -> false
    }
    }

    private fun handleTouchEvent(x: Float, y: Float) {
    val possibleSelectedRow = (y / chosenDiameter).toInt()
    val possibleSelectedCol = (x / chosenDiameter).toInt()
    listener?.onCellTouched(possibleSelectedRow, possibleSelectedCol)
    }*/

}