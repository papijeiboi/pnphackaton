package com.jemoje.pnphackaton.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.jemoje.pnphackaton.R
import kotlinx.android.synthetic.main.activity_police_menu.*
import kotlinx.android.synthetic.main.layout_drawer.*

class PoliceMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_police_menu)

        menu_bar.setOnClickListener {
            toggle()
        }

        Glide.with(this)
            .load(R.drawable.police_profile)
            .circleCrop()
            .into(iv_drawer_profile)

        btn_scan.setOnClickListener {
            val intent = Intent(applicationContext, ScanQrActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(intent)
            overridePendingTransition(R.anim.enterfrom, R.anim.enterto)
        }

        btn_search.setOnClickListener {
            val intent = Intent(applicationContext, SearchPlateNumberActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(intent)
            overridePendingTransition(R.anim.enterfrom, R.anim.enterto)
        }
    }



    private fun toggle() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}
