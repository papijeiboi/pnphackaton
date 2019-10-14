package com.jemoje.pnphackaton.activities

import android.content.ContextWrapper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.google.gson.Gson
import com.jemoje.pnphackaton.R
import com.jemoje.pnphackaton.constant.Keys
import com.jemoje.pnphackaton.model.UserData
import com.jemoje.pnphackaton.model.UserResponse
import com.pixplicity.easyprefs.library.Prefs
import java.util.*
import kotlin.concurrent.timerTask

class SplashScreenActivity : AppCompatActivity() {
    private val TAG = "SplashScreenActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)

        initSplash()

    }

    private fun initSplash() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()


        runOnUiThread {
            val timer = Timer()
            timer.schedule(timerTask {

                val apiToken = Prefs.getString(Keys.USER_TOKEN, "")



                if (apiToken.equals("")) {
                    runOnUiThread {
                        val intent = Intent(applicationContext, LandingActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        applicationContext.startActivity(intent)
                        overridePendingTransition(
                            R.anim.fadein,
                            R.anim.fadeout
                        )
                        finish()
                    }

                } else {
                    runOnUiThread {

                        Log.d(TAG, "app has a token")

                        val fromApiInfo = Prefs.getString(Keys.USER_FULL_DATA, "")
                        val profileResponse =
                            Gson().fromJson<UserData>(fromApiInfo, UserData::class.java)

                        Log.d(TAG, "${profileResponse.userType}")


                        if (profileResponse.userType == "staff1") {
                            val intent = Intent(applicationContext, StaffMenuActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            applicationContext.startActivity(intent)
                            overridePendingTransition(
                                R.anim.fadein,
                                R.anim.fadeout
                            )
                            finish()
                        } else if (profileResponse.userType == "police") {
                            val intent = Intent(applicationContext, PoliceMenuActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            applicationContext.startActivity(intent)
                            overridePendingTransition(
                                R.anim.fadein,
                                R.anim.fadeout
                            )
                            finish()
                        }

                    }
                }
            }, 2000)
        }
    }
}
