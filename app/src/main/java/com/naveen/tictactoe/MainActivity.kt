package com.naveen.tictactoe

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.start_game_layout.*

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    var Player1Name = ""
    var Player2Name = ""
    var PlayerButton = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_game_layout)
        Player1Name = player1.text.toString()
        Player2Name = player2.text.toString()


        gametable.visibility = View.INVISIBLE

        btngamestar.setOnClickListener {

            if(PlayerButton == false) {
                Player1Name = player1.text.toString()
                Player2Name = player2.text.toString()
                gametable.visibility = View.VISIBLE
                btngamestar.setBackgroundColor(ContextCompat.getColor(this, R.color.stopbutton))
                btngamestar.setTextColor(ContextCompat.getColor(this, R.color.stopbuttoncolor))
                PlayerButton = true
                btngamestar.text = "Stop"
                hideSoftKeyboard()
            }
            else {
                gametable.visibility = View.INVISIBLE
                btngamestar.setBackgroundColor(ContextCompat.getColor(this, R.color.playbutton))
                btngamestar.setTextColor(ContextCompat.getColor(this, R.color.white))
                PlayerButton = false
                btngamestar.text = "Play"
            }

        }

    }

    val positiveButtonClick = {
        dialog: DialogInterface, which: Int ->
        resetGame()
    }

    private fun resetGame() {

        button1.isClickable = true
        button1.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))
        button2.isClickable = true
        button2.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))
        button3.isClickable = true
        button3.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))
        button4.isClickable = true
        button4.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))
        button5.isClickable = true
        button5.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))
        button6.isClickable = true
        button6.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))
        button7.isClickable = true
        button7.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))
        button8.isClickable = true
        button8.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))
        button9.isClickable = true
        button9.setBackgroundColor(ContextCompat.getColor(this, R.color.button_def))

        winner = 0
        activePlayer = PLAYER_1
        PlayerArr1.clear()
        PlayerArr2.clear()
        count = 0
    }

    override fun onClick(view: View?) {

        var clickPositiion = 0
        count++
        var button = view as Button
        when(view?.id) {
            R.id.button1 ->  clickPositiion =1
            R.id.button2 ->  clickPositiion =2
            R.id.button3 ->  clickPositiion =3
            R.id.button4 ->  clickPositiion =4
            R.id.button5 ->  clickPositiion =5
            R.id.button6 ->  clickPositiion =6
            R.id.button7 ->  clickPositiion =7
            R.id.button8 ->  clickPositiion =8
            R.id.button9 ->  clickPositiion =9
        }

        applyGameRulles(clickPositiion, button)
    }

    private fun validateWinner() {
        if(PlayerArr1.contains(1) && PlayerArr1.contains(2) && PlayerArr1.contains(3))
            winner = PLAYER_1
        if(PlayerArr2.contains(1) && PlayerArr2.contains(2) && PlayerArr2.contains(3))
            winner = PLAYER_2
        if(PlayerArr1.contains(4) && PlayerArr1.contains(5) && PlayerArr1.contains(6))
            winner = PLAYER_1
        if(PlayerArr2.contains(4) && PlayerArr2.contains(5) && PlayerArr2.contains(6))
            winner = PLAYER_2
        if(PlayerArr1.contains(7) && PlayerArr1.contains(8) && PlayerArr1.contains(9))
            winner = PLAYER_1
        if(PlayerArr2.contains(7) && PlayerArr2.contains(8) && PlayerArr2.contains(9))
            winner = PLAYER_2

        if(PlayerArr1.contains(1) && PlayerArr1.contains(4) && PlayerArr1.contains(7))
            winner = PLAYER_1
        if(PlayerArr2.contains(1) && PlayerArr2.contains(4) && PlayerArr2.contains(7))
            winner = PLAYER_2

        if(PlayerArr1.contains(2) && PlayerArr1.contains(5) && PlayerArr1.contains(8))
            winner = PLAYER_1
        if(PlayerArr2.contains(2) && PlayerArr2.contains(5) && PlayerArr2.contains(8))
            winner = PLAYER_2

        if(PlayerArr1.contains(3) && PlayerArr1.contains(6) && PlayerArr1.contains(9))
            winner = PLAYER_1
        if(PlayerArr2.contains(3) && PlayerArr2.contains(6) && PlayerArr2.contains(9))
            winner = PLAYER_2

        if(PlayerArr1.contains(1) && PlayerArr1.contains(5) && PlayerArr1.contains(9))
            winner = PLAYER_1
        if(PlayerArr2.contains(1) && PlayerArr2.contains(5) && PlayerArr2.contains(9))
            winner = PLAYER_2

        if(PlayerArr1.contains(3) && PlayerArr1.contains(5) && PlayerArr1.contains(7))
            winner = PLAYER_1
        if(PlayerArr2.contains(3) && PlayerArr2.contains(5) && PlayerArr2.contains(7))
            winner = PLAYER_2
        announesWinner()
    }

    var count = 0
    var winner = 0
    val PLAYER_1 = 1
    val PLAYER_2 = 2
    var activePlayer = PLAYER_1
    var PlayerArr1 = arrayListOf<Int>()
    var PlayerArr2 = arrayListOf<Int>()


    private fun applyGameRulles(clickPositiion: Int, button: Button) {

        if(activePlayer == PLAYER_1) {
            PlayerArr1.add(clickPositiion)
            activePlayer = PLAYER_2
            button.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.player1))


        }
        else if (activePlayer == PLAYER_2) {
            PlayerArr2.add(clickPositiion)
            activePlayer = PLAYER_1
            button.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.player2))
        }
        button.isClickable = false
        validateWinner()
    }

    private fun announesWinner() {
        if (winner == 0 && count < 9)
            return

        var namePayer = if (winner == PLAYER_1)  Player1Name else Player2Name

        winner_textview.text = namePayer

        var msg = "Congratulation.. Player $namePayer is the Winner"
        if(winner == 0 && count == 9) {
            msg = "Well played!! Game is Tie"
            winner_textview.text = "Tie"
        }

       var dialog = AlertDialog.Builder(this)
        with(dialog) {
            dialog.setTitle("Winner")
            dialog.setMessage(msg)
            dialog.setPositiveButton(android.R.string.ok, positiveButtonClick)
            dialog.show()
        }

    }

    private fun hideSoftKeyboard() {
        var imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if(imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}