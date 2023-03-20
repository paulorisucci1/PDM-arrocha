package com.example.arrochaapp.arrocha

import android.util.Log

class Arrocha {

    var lowestNumber = 1
    var biggestNumber = 100
    var gameState = States.STARTED
    private val drawnNumber = (lowestNumber+1 until biggestNumber).random()

    init {
        Log.i("APP_ARROCHA", "Número sorteado: ${this.drawnNumber}")
    }

    fun processPlay(userGuess: Int): States {

        when {
            haveGuessLoseGame(userGuess) -> gameLose()
            isGuessLowerThanDrawn(userGuess) -> adjustLowestNumber(userGuess)
            isGuessBiggerThanDrawn(userGuess) -> adjustBiggestNumber(userGuess)
        }

        verifyIfGuessWon()

        return this.gameState
    }

    private fun isGameWon(): Boolean {
        return lowestNumber + 1 == biggestNumber - 1
    }

    private fun verifyIfGuessWon() {
        if(isGameWon()) {
            this.gameState = States.WON
        }
    }

    private fun gameLose() {
        this.gameState = States.LOSE
    }

    private fun haveGuessLoseGame(userGuess: Int): Boolean {
        return userGuess >= biggestNumber || userGuess <= lowestNumber || userGuess == drawnNumber
    }

    private fun adjustBiggestNumber(userGuess: Int) {
        this.biggestNumber = userGuess
        this.gameState = States.GUESS_BIGGER_THAN_CHOSEN_NUMBER
    }

    private fun isGuessBiggerThanDrawn(userGuess: Int): Boolean {
        return userGuess > drawnNumber
    }

    private fun isGuessLowerThanDrawn(userGuess: Int): Boolean {
        return userGuess < drawnNumber
    }

    private fun adjustLowestNumber(userGuess: Int) {
        lowestNumber = userGuess
        this.gameState = States.GUESS_LOWER_THAN_CHOSEN_NUMBER
    }

    fun gameOver(): Boolean {
        return this.gameState == States.WON || this.gameState == States.LOSE
    }

    enum class States (val message: String) {
        STARTED("The game have just started"),
        WON("You won!"),
        LOSE("You lose!"),
        GUESS_LOWER_THAN_CHOSEN_NUMBER("The number is bigger than this"),
        GUESS_BIGGER_THAN_CHOSEN_NUMBER("The number is lower than this")
    }
}