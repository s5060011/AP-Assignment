package uk.ac.bournemouth.ap.dotsandboxes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ContentView

class MainActivity : AppCompatActivity() {

    /**
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
     */

    private var mScoreView: ScoreView? = null
    private var mGameView: GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mScoreView = ScoreView(this)
        setContentView(mScoreView)

        mGameView = GameView(this)
        setContentView(mGameView)
    }
}
