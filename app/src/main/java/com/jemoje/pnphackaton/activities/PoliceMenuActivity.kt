package com.jemoje.pnphackaton.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.jemoje.pnphackaton.R
import com.jemoje.pnphackaton.constant.Keys
import com.jemoje.pnphackaton.model.UserData
import com.pixplicity.easyprefs.library.Prefs
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

        val fromApi = Prefs.getString(Keys.USER_FULL_DATA, "")
        val profileResponse = Gson().fromJson<UserData>(fromApi, UserData::class.java)

        tv_drawer_name.text = "${profileResponse.firstName}"

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

        log_out.setOnClickListener {
            val builder = AlertDialog.Builder(this)


            builder.setMessage("Are you sure you want to logout?")

            builder.setPositiveButton("YES") { dialog, which ->
                Prefs.clear()


                finish()
                val intent = Intent(this, LandingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(R.anim.enterfrom, R.anim.enterto)

                dialog.dismiss()


            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
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
