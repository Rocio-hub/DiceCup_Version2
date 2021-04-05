package com.easv.rtl.assignment1_dicecup.GUI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.easv.rtl.assignment1_dicecup.Model.BERoll
import com.easv.rtl.assignment1_dicecup.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 1
    private val numbers = arrayOf(1, 2, 3, 4, 5, 6)
    private var numOfDices = 1
    private lateinit var listRandomNumbers: List<Int>
    private val randomGenerator = Random()
    private var arrayListHistory = arrayListOf<BERoll>()
    private val diceId = intArrayOf(
        0,
        R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_roll.setOnClickListener { v -> onClickRoll() }
        btn_history.setOnClickListener { v -> onClickHistory() }

        //set images for dices for the start of the app
        setDefaulImages()

        //spinner
        setSpinner()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        //val extras: Bundle = intent.extras!!
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK) arrayListHistory =  data?.extras?.getSerializable("MYHISTORY") as ArrayList<BERoll>
            else arrayListHistory.clear()
        }
    }

    private fun setSpinner() {
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numbers)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    numOfDices = numbers[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun setDefaulImages() {
        dice1.setImageResource(diceId[1])
        dice2.setImageResource(diceId[2])
        dice3.setImageResource(diceId[3])
        dice4.setImageResource(diceId[4])
        dice5.setImageResource(diceId[5])
        dice6.setImageResource(diceId[6])
    }

    private fun onClickHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
        intent.putExtra("MYHISTORY", arrayListHistory)
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun onClickRoll() {
        //create random numbers for all dices even if they are after set as GONE, will be solved if there is enough time
        createRandomNumbers()
        //set images for dices
        updateDices(listRandomNumbers)
    }

    private fun updateDices(s: List<Int>) {
        dice1.setImageResource(diceId[s[0]])
        dice2.setImageResource(diceId[s[1]])
        dice3.setImageResource(diceId[s[2]])
        dice4.setImageResource(diceId[s[3]])
        dice5.setImageResource(diceId[s[4]])
        dice6.setImageResource(diceId[s[5]])

        hideDices()
        when {
            (numOfDices > 1) -> dice2.visibility = View.VISIBLE
        }
        when {
            (numOfDices > 2) -> dice3.visibility = View.VISIBLE
        }
        when {
            (numOfDices > 3) -> dice4.visibility = View.VISIBLE
        }
        when {
            (numOfDices > 4) -> dice5.visibility = View.VISIBLE
        }
        when {
            (numOfDices > 5) -> dice6.visibility = View.VISIBLE
        }
    }

    private fun hideDices() {
        dice2.visibility = View.GONE
        dice3.visibility = View.GONE
        dice4.visibility = View.GONE
        dice5.visibility = View.GONE
        dice6.visibility = View.GONE
    }

    private fun createRandomNumbers() {
        var num1 = randomGenerator.nextInt(6) + 1
        var num2: Int;
        var num3: Int;
        var num4: Int;
        var num5: Int;
        var num6: Int

        if (numOfDices > 1) num2 = randomGenerator.nextInt(6) + 1
        else num2 = 0

        if (numOfDices > 2) num3 = randomGenerator.nextInt(6) + 1
        else num3 = 0

        if (numOfDices > 3) num4 = randomGenerator.nextInt(6) + 1
        else num4 = 0

        if (numOfDices > 4) num5 = randomGenerator.nextInt(6) + 1
        else num5 = 0

        if (numOfDices > 5) num6 = randomGenerator.nextInt(6) + 1
        else num6 = 0

        listRandomNumbers = listOf(num1, num2, num3, num4, num5, num6)
        arrayListHistory.add(BERoll(getCurrentTime(), num1, num2, num3, num4, num5, num6))
    }

    private fun getCurrentTime(): String {
        var hourString: String
        var minuteString: String
        var secondString: String

        val c = Calendar.getInstance()
        var hour = (c.get(Calendar.HOUR_OF_DAY) + 2)
        var min = c.get(Calendar.MINUTE)
        var sec = c.get(Calendar.SECOND)

        if (min < 10) minuteString = "0${min}"
        else minuteString = min.toString()
        if (hour < 10) hourString = "0${hour}"
        else hourString = hour.toString()
        if (sec < 10) secondString = "0${sec}"
        else secondString = sec.toString()

        return "${hourString}:${minuteString}:${secondString}"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("dice1", listRandomNumbers[0])
        outState.putInt("dice2", listRandomNumbers[1])
        outState.putInt("dice3", listRandomNumbers[2])
        outState.putInt("dice4", listRandomNumbers[3])
        outState.putInt("dice5", listRandomNumbers[4])
        outState.putInt("dice6", listRandomNumbers[5])
        outState.putInt("diceNum", numOfDices)
        outState.putSerializable("history", arrayListHistory)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var dice1 = savedInstanceState.getInt("dice1")
        var dice2 = savedInstanceState.getInt("dice2")
        var dice3 = savedInstanceState.getInt("dice3")
        var dice4 = savedInstanceState.getInt("dice4")
        var dice5 = savedInstanceState.getInt("dice5")
        var dice6 = savedInstanceState.getInt("dice6")
        numOfDices = savedInstanceState.getInt("diceNum")
        arrayListHistory = savedInstanceState.getSerializable("history") as ArrayList<BERoll>
        listRandomNumbers = listOf(dice1, dice2, dice3, dice4, dice5, dice6)
        updateDices(listRandomNumbers)
    }
}