package com.jemoje.pnphackaton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.google.gson.GsonBuilder
import com.jemoje.pnphackaton.R
import com.jemoje.pnphackaton.constant.Keys
import com.jemoje.pnphackaton.model.UserResponse
import com.jemoje.pnphackaton.webervice.UserService
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        back_exit.setOnClickListener {
            runOnUiThread {
                finish()
                overridePendingTransition(R.anim.exitfrom, R.anim.exitto)
            }
        }

        btn_login.setOnClickListener {
            when {
                edt_login_email.text.toString().isEmpty() -> displayDialog("Email must not be empty.")
                edt_login_password.text.toString().isEmpty() -> displayDialog("Password must not be empty.")
                else -> {
                    if (isEmailValid(edt_login_email.text.toString().trim())) {
                        callWebService()
//                        val intent = Intent(applicationContext, PoliceMenuActivity::class.java)
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        applicationContext.startActivity(intent)
//                        overridePendingTransition(R.anim.enterfrom, R.anim.enterto)
                    } else {
                        displayDialog("Enter valid email.")
                    }
                }
            }

        }
    }

    private fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun displayDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun callWebService() {

        progress_layout_login.visibility = View.VISIBLE

        //TODO: CHECK IF USER TYPE THEN THROW IN StaffMenuActivity or PoliceMenuActivity
        val apiService = UserService.create(this.getString(R.string.base_url))

        val email = edt_login_email.text.toString()
        val password = edt_login_password.text.toString()

        val callService = apiService.login(email.trim(), password.trim())
        callService.enqueue(object : retrofit2.Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {


                if (response.code() == 200) {
                    val userResponse: UserResponse? = response.body()

                    if (userResponse?.user?.userType == "staff1") {
                        //StaffMenuActivity


                        Prefs.putString(Keys.USER_TOKEN, userResponse.token?.accessToken)
                        val mGson = GsonBuilder()
                            .setLenient()
                            .create()
                        Prefs.putString(Keys.USER_FULL_DATA, mGson.toJson(userResponse.user))

                        val intent = Intent(applicationContext, StaffMenuActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        applicationContext.startActivity(intent)
                        progress_layout_login.visibility = View.GONE
                        overridePendingTransition(R.anim.enterfrom, R.anim.enterto)
                        finish()

                    } else if (userResponse?.user?.userType == "police") {
                        //PoliceMenuActivity


                        Prefs.putString(Keys.USER_TOKEN, userResponse.token?.accessToken)
                        val mGson = GsonBuilder()
                            .setLenient()
                            .create()
                        Prefs.putString(Keys.USER_FULL_DATA, mGson.toJson(userResponse.user))

                        val intent = Intent(applicationContext, PoliceMenuActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        applicationContext.startActivity(intent)
                        progress_layout_login.visibility = View.GONE
                        overridePendingTransition(R.anim.enterfrom, R.anim.enterto)
                        finish()
                    }


                } else {

                    progress_layout_login.visibility = View.GONE
                    displayDialog("Failed to login")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                progress_layout_login.visibility = View.GONE
                displayDialog("Something went wrong!!")
            }

        })

    }
}
