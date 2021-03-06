package com.codingblocks.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var board :Array<Array<Button>>
    private var player :Boolean= true
    var turncount:Int = 0
    var player1name:String = ""

    var player2name:String = ""


    var boardStatus = Array(3){IntArray(3)}
     
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     
        player1name = intent.getStringExtra(player1).toString()
        player2name = intent.getStringExtra(player2).toString()
//        Log.d("tag",player1name)
//        Log.d("tag",player2name)
        updatedisplay(player1name)
        board = arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9))

        for(i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()
        resetbtn.setOnClickListener {

            player = true
            turncount = 0
            initializeBoardStatus()
            updatedisplay(player1name)
        }
    }

    private fun initializeBoardStatus() {
        for( i :Int in 0..2)
        {
            for(j in 0..2)
            {
                boardStatus[i][j] = -1
                board[i][j].isEnabled=true
                board[i][j].text = ""
            }
        }
    }


    override fun onClick(view: View) {

        when(view.id)
        {
            R.id.btn1->
            {
                updateValue(row=0,col=0,player=player)
            }
            R.id.btn2->
            {
                updateValue(row=0,col=1,player=player)
            }
            R.id.btn3->
            {
                updateValue(row=0,col=2,player=player)
            }
            R.id.btn4->
            {
                updateValue(row=1,col=0,player=player)
            }
            R.id.btn5->
            {
                updateValue(row=1,col=1,player=player)
            }
            R.id.btn6->
            {
                updateValue(row=1,col=2,player=player)
            }
            R.id.btn7->
            {
                updateValue(row=2,col=0,player=player)
            }
            R.id.btn8->
            {
                updateValue(row=2,col=1,player=player)
            }
            R.id.btn9->
            {
                updateValue(row=2,col=2,player=player)
            }

        }
        turncount++
        player = !player
        if(player)
        {
            updatedisplay("$player1name's turn")
        }
        else
        {
            updatedisplay("$player2name's turn")
        }
        if(turncount==9)
        {
            updatedisplay("Draw")
        }
        checkwinner()
    }

    private fun checkwinner() {
        for(i in 0..2)
        {
            //For horizontal rows
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][2]==boardStatus[i][0]&& boardStatus[i][0]!=-1)
            {
                if(boardStatus[i][0]==1)
                {
                    updatedisplay("$player1name is the Winner")
                    return ;
                }
                else if(boardStatus[i][0]==0)
                {
                    updatedisplay("$player2name is the Winner")
                    return ;
                }
            }
        }
        //For vertical columns
        for(i in 0..2)
        {

            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[2][i]==boardStatus[0][i]&& boardStatus[0][i]!=-1)
            {
                if(boardStatus[0][i]==1)
                {
                    updatedisplay("$player1name is the Winner")
                    return;
                }
                else if(boardStatus[0][i]==0) {
                    updatedisplay("${player2name}is the Winner")
                    return ;

                }
            }
        }
        //For diagonals
        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][2])
        {
            if(boardStatus[0][0]==1)
            {
                updatedisplay("${player1name}is the Winner")
                return;
            }
            else if(boardStatus[0][0]==0) {
                updatedisplay("${player2name}is the Winner")
                return ;

            }
        }
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][0])
        {
            if(boardStatus[1][1]==1)
            {
                updatedisplay("${player1name}is the Winner")
                return;
            }
            else if(boardStatus[1][1]==0) {
                updatedisplay("${player2name}is the Winner")
                return ;

            }
        }
    }


    private fun updatedisplay(s: String) {
        displaytextview.text = s
        if(s.contains("Winner"))
        {
            disablebutton()
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val textvalue = if(player) "X" else "O"
        val value = if(player) 1 else 0

        board[row][col].apply {

                isEnabled = false
                text = textvalue
            }
        boardStatus[row][col] = value

    }
    private fun disablebutton()
    {
        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled = false
            }
        }

    }

}