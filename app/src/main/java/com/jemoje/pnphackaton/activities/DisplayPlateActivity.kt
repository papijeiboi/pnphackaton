package com.jemoje.pnphackaton.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jemoje.pnphackaton.R
import kotlinx.android.synthetic.main.activity_display_plate_actvity.*

class DisplayPlateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_plate_actvity)

        val plateNumber = intent.getStringExtra("plate_number")
        val nameOwner = intent.getStringExtra("name_owner")
        val mobileNumber = intent.getStringExtra("mobile_number")
        val carModel = intent.getStringExtra("car_model")
        val carColor = intent.getStringExtra("car_color")

        tv_owner_name.text = nameOwner
        tv_plate_cp.text = mobileNumber
        tv_display_model.text = carModel
        tv_display_color.text = carColor
        tv_plate_no.text = plateNumber

        btn_display_plate_back.setOnClickListener {
            runOnUiThread {
                finish()
                overridePendingTransition(R.anim.exitfrom, R.anim.exitto)
            }


        }

    }
}
