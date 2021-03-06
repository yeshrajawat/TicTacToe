package com.codingblocks.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main2.*

const val player1 = "name1"
const val player2 = "name2"
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

            playbtn.setOnClickListener {
                val i = Intent(this,MainActivity::class.java)
                val player1name: String= editplayer1.text.toString()
                val player2name:String= editplayer2.text.toString()
                i.putExtra(player1,player1name)
                i.putExtra(player2,player2name)
                startActivity(i)
            }
    }
}