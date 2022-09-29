package com.games.barley_break.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.games.barley_break.ui.utils.GameStatus

class BarelyBreakViewModel: ViewModel() {

    val field = mutableStateListOf(
        "1","2","3","4",
        "5","6","7","8",
        "9","10","11","12",
        "13","14","15"," "
    )
    private val correctField = field.toList()

    var gameStatus by mutableStateOf(GameStatus.Game)

    private var emptyCellIndex = field.indexOf(
        field.find {
            it == " "
        }
    )

    fun initGame() {
        shuffleField()
    }

    private var possibleSwapIndexes = getPossibleSwapIndexes()

    private fun winCheck() = field.toList() == correctField

    private fun shuffleField() {
        var prevIndex = emptyCellIndex
        repeat(20) {
            possibleSwapIndexes.remove(prevIndex)
            val index = possibleSwapIndexes.random()
            val newCell = field[index]
            field[emptyCellIndex] = newCell
            field[index] = " "
            prevIndex = emptyCellIndex
            emptyCellIndex = index
            possibleSwapIndexes = getPossibleSwapIndexes()
        }
    }

    fun onCellClick(index: Int) {
        if(possibleSwapIndexes.contains(index)) {
            val newCell = field[index]
            field[emptyCellIndex] = newCell
            field[index] = " "
            emptyCellIndex = index
            possibleSwapIndexes = getPossibleSwapIndexes()
            if(winCheck()) {
                win()
            }
        }

    }

    fun restartGame() {
        shuffleField()
        emptyCellIndex = field.indexOf(
            field.find {
                it == " "
            }
        )
        possibleSwapIndexes = getPossibleSwapIndexes()
        gameStatus = GameStatus.Game
    }

    private fun win() {
        gameStatus = GameStatus.GameOver
    }

    private fun getPossibleSwapIndexes(): MutableList<Int> {
        val topIndex = emptyCellIndex - 4
        val leftIndex = emptyCellIndex - 1
        val rightIndex = emptyCellIndex + 1
        val bottomIndex = emptyCellIndex + 4
        return mutableListOf<Int>().apply {
            if(topIndex >= 0)
                add(topIndex)
            if(leftIndex >= 0 && emptyCellIndex % 4 != 0)
                add(leftIndex)
            if(rightIndex < 16 && rightIndex % 4 != 0)
                add(rightIndex)
            if(bottomIndex < 16)
                add(bottomIndex)
        }
    }
}