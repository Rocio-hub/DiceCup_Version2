package com.easv.rtl.assignment1_dicecup.GUI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easv.rtl.assignment1_dicecup.ListAdapter
import com.easv.rtl.assignment1_dicecup.Model.BERoll
import com.easv.rtl.assignment1_dicecup.R
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    var arrayListHistory = arrayListOf<BERoll>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        arrayListHistory  = getIntent().getSerializableExtra("MYHISTORY") as ArrayList<BERoll>
        myRecyclerView.adapter = ListAdapter(arrayListHistory)

        btn_back.setOnClickListener { v -> onClickBack() }
        btn_clear.setOnClickListener { v -> onClickClear() }
    }

    private fun onClickClear() {
        Toast.makeText(this, "History deleted successfully", Toast.LENGTH_SHORT).show()
        setResult(RESULT_CANCELED)
        finish()
    }

    private fun onClickBack() {
        val intent = Intent()
        intent.putExtra("MYHISTORY", arrayListHistory)
        setResult(RESULT_OK, intent)
        finish()
    }
}