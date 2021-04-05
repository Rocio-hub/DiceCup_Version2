package com.easv.rtl.assignment1_dicecup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easv.rtl.assignment1_dicecup.Model.BERoll
import kotlinx.android.synthetic.main.history_detail.view.*

class ListAdapter(private val rolls: List<BERoll>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.history_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initialize(rolls.get(position))
    }

    override fun getItemCount(): Int = rolls.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val diceId = intArrayOf(
            0,
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
        )

        private val currentTime = view.textView_currentTime
        private val dice1 = view.imageView_dice1
        private val dice2 = view.imageView_dice2
        private val dice3 = view.imageView_dice3
        private val dice4 = view.imageView_dice4
        private val dice5 = view.imageView_dice5
        private val dice6 = view.imageView_dice6

        fun initialize(roll: BERoll) {
            currentTime.text = roll.now
            dice1.setImageResource(diceId[roll.dice1])
            dice2.setImageResource(diceId[roll.dice2])
            dice3.setImageResource(diceId[roll.dice3])
            dice4.setImageResource(diceId[roll.dice4])
            dice5.setImageResource(diceId[roll.dice5])
            dice6.setImageResource(diceId[roll.dice6])
        }
    }
}