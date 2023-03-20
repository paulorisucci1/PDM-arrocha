package com.example.arrochaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.arrochaapp.arrocha.Arrocha

class MainActivity : AppCompatActivity() {

//    private lateinit var lowestNumberTextView: TextView
//    private lateinit var biggestNumberTextView: TextView
    private lateinit var playButton: Button
    private lateinit var drawnNumberTextEditor: EditText
    private lateinit var arrocha: Arrocha
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViewReferences()
        startGame()
    }

    private fun startGame() {
        arrocha = Arrocha()
        setOnClickEvents()
        showGameState(arrocha.gameState)
    }

    private fun restartGame() {
        Toast.makeText(this, "Restarting game...", Toast.LENGTH_SHORT).show()
        this.startGame()
    }

    private fun setOnClickEvents() {
        playButton.setOnClickListener(PlayButtonListener())
        resetButton.setOnClickListener(ResetButtonListener())
    }

    private fun initializeViewReferences() {
        playButton = findViewById(R.id.playButton)
        drawnNumberTextEditor = findViewById(R.id.drawnNumberTextEditor)
        resetButton = findViewById(R.id.resetButton)
    }

    private fun processPlay(gameState: Arrocha.States) {
        clearTextEditor()
        showGameState(gameState)
        if(this.arrocha.gameOver()) {
            this.restartGame()
        }
    }

    private fun clearTextEditor() {
        drawnNumberTextEditor.text.clear()
    }

    private fun showGameState(gameState: Arrocha.States) {
        Toast.makeText(this, gameState.message, Toast.LENGTH_SHORT).show()
    }

    inner class PlayButtonListener : OnClickListener  {
        override fun onClick(p0: View?) {
            val userGuess = this@MainActivity.drawnNumberTextEditor.text.toString().toInt()
            val gameState = this@MainActivity.arrocha.processPlay(userGuess)
            this@MainActivity.processPlay(gameState)
        }
    }

    inner class ResetButtonListener: OnClickListener {
        override fun onClick(p0: View?) {
            this@MainActivity.startGame()
        }
    }
}