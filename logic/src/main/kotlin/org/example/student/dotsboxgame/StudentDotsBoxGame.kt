package org.example.student.dotsboxgame

import uk.ac.bournemouth.ap.dotsandboxeslib.*
import uk.ac.bournemouth.ap.dotsandboxeslib.matrix.Matrix
import uk.ac.bournemouth.ap.dotsandboxeslib.matrix.MutableMatrix
import uk.ac.bournemouth.ap.dotsandboxeslib.matrix.MutableSparseMatrix
import uk.ac.bournemouth.ap.dotsandboxeslib.matrix.SparseMatrix
import kotlin.random.Random

class StudentDotsBoxGame(columns: Int, rows: Int, players: List<Player>) : AbstractDotsAndBoxesGame() {
    override val players: List<Player> = players //TODO("You will need to get players from your constructor")

    override val currentPlayer: Player get()= TODO("Determine the current player, like keeping" +
                                                           "the index into the players list")

    // NOTE: you may want to me more specific in the box type if you use that type in your class
    override val boxes: Matrix<StudentBox> = MutableMatrix(columns, rows, ::StudentBox) //TODO("Create a matrix initialized with your own box type")

    //override val lines: SparseMatrix<DotsAndBoxesGame.Line> = MutableSparseMatrix(columns, rows, ::StudentLine) //TODO("Create a matrix initialized with your own line type")
    override val lines: SparseMatrix<StudentLine> = MutableSparseMatrix(columns + 1, rows * 2 + 1, ::StudentLine) {
        x, y -> columns % 2 == 1 || x < columns
    }

    override val isFinished: Boolean
        get() = TODO("Provide this getter. Note you can make it a var to do so")

    override fun playComputerTurns() {
        var current = currentPlayer
        while (current is ComputerPlayer && ! isFinished) {
            current.makeMove(this)
            current = currentPlayer
        }
    }

    /**
     * This is an inner class as it needs to refer to the game to be able to look up the correct
     * lines and boxes. Alternatively you can have a game property that does the same thing without
     * it being an inner class.
     */
    inner class StudentLine(lineX: Int, lineY: Int) : AbstractLine(lineX, lineY) {
        override val isDrawn: Boolean
            get() = TODO("Provide this getter. Note you can make it a var to do so")


        override val adjacentBoxes: Pair<StudentBox?, StudentBox?>
            get() {
                //return Pair(boxes[leftBoxX, boxY], boxes[rightBoxX, boxY])
                TODO("You need to look up the correct boxes for this to work")
            }

        override fun drawLine() {
            /**
             *
            If the line is already drawn, throw an exception (eg. IllegalStateException)
            Update the isDrawn property
            For each of the adjacent boxes (hint hint):
                Determine if all its lines have been drawn (hint: box.boundingLines.all { line -> line.isDrawn }
                If so, update the owning player
                If so, record that a box was completed (player gets another turn)
            If any box was completed, check that the game was won: update the state and fire the event
            If no box was completed:
                update the current player (currentPlayerIdx = (currentPlayerIdx+1) % players.size)
                call playComputerTurns()
            fire the game change event (fireGameChange)
             */
            TODO("Implement the logic for a player drawing a line. Don't forget to inform the listeners (fireGameChange, fireGameOver)")
            // NOTE read the documentation in the interface, you must also update the current player.
        }
    }

    inner class StudentBox(boxX: Int, boxY: Int) : AbstractBox(boxX, boxY) {
// 8x8 boxes = grid of 17x17 (dot-line-dot(outer)/line-box-line(inner), double +1 of #boxes)
        override val owningPlayer: Player?
            get() = TODO("Provide this getter. Note you can make it a var to do so")

        /**
         * This must be lazy or a getter, otherwise there is a chicken/egg problem with the boxes
         */
        override val boundingLines: Iterable<DotsAndBoxesGame.Line>
            // Border lines of box
            get() = TODO("Look up the correct lines from the game outer class")

    }
}