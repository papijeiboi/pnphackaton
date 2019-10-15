package com.jemoje.pnphackaton.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jemoje.pnphackaton.R
import kotlinx.android.synthetic.main.activity_qr_staff_success.*

class QrStaffSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_staff_success)

        btn_qr_staff_success.setOnClickListener {
            runOnUiThread {
                finish()
                overridePendingTransition(R.anim.exitfrom, R.anim.exitto)
            }
        }
    }
}
