package com.jemoje.pnphackaton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.jemoje.pnphackaton.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_police_menu.*
import kotlinx.android.synthetic.main.activity_staff_menu.*
import kotlinx.android.synthetic.main.activity_staff_menu.menu_bar
import kotlinx.android.synthetic.main.layout_drawer.*

class StaffMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_staff_menu)

        menu_bar.setOnClickListener {
            toggle()
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

        btn_staff_scan.setOnClickListener {
            val intent = Intent(applicationContext, ScanQrStaffActivity::class.java)
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
