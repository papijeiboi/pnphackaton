package com.jemoje.pnphackaton.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.jemoje.pnphackaton.R
import kotlinx.android.synthetic.main.activity_search_plate_number.*
import android.widget.ArrayAdapter



class SearchPlateNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_search_plate_number)

        val fruits = arrayListOf<String>("Apple", "Appy", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear")

        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, fruits)
        autoTextView.threshold = 1 //will start working from first character
        autoTextView.setAdapter(adapter)

        btn_search_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.exitfrom, R.anim.exitto)
        }
    }
}
