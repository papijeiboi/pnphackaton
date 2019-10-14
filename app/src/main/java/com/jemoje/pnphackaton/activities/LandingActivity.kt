package com.jemoje.pnphackaton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.flaviofaria.kenburnsview.KenBurnsView
import com.flaviofaria.kenburnsview.RandomTransitionGenerator
import com.flaviofaria.kenburnsview.Transition
import com.jemoje.pnphackaton.R
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity(), KenBurnsView.TransitionListener {
    private val TRANSITIONS_TO_SWITCH = 1
    private var mTransitionsCount = 0

    override fun onTransitionEnd(transition: Transition?) {
        mTransitionsCount++
        if (mTransitionsCount == TRANSITIONS_TO_SWITCH) {
            runOnUiThread {
                viewFlipper.setInAnimation(applicationContext, R.anim.fadein)
                viewFlipper.setOutAnimation(applicationContext, R.anim.fadeout)
                viewFlipper.showNext()

                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                mTransitionsCount = 0
            }
        }
    }

    override fun onTransitionStart(transition: Transition?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_landing)

        val generator = RandomTransitionGenerator(5000, LinearOutSlowInInterpolator())
        image1.setTransitionGenerator(generator)
        image2.setTransitionGenerator(generator)




        btn_login.setOnClickListener {
            runOnUiThread {

                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                applicationContext.startActivity(intent)
                overridePendingTransition(R.anim.enterfrom, R.anim.enterto)

            }

            image1.setTransitionListener(this)
            image2.setTransitionListener(this)


        }
    }






}
