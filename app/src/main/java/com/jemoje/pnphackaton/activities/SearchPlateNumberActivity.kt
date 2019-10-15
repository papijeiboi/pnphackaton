package com.jemoje.pnphackaton.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.jemoje.pnphackaton.R
import kotlinx.android.synthetic.main.activity_search_plate_number.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jemoje.pnphackaton.adapters.PlateAdapter
import com.jemoje.pnphackaton.constant.Keys
import com.jemoje.pnphackaton.model.PlateData
import com.jemoje.pnphackaton.model.PlateResponse
import com.jemoje.pnphackaton.webervice.UserService
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_police_menu.*
import retrofit2.Call
import retrofit2.Response
import android.content.Intent




class SearchPlateNumberActivity : AppCompatActivity() {
    private var plateAdapter: PlateAdapter? = null
    var plateListData: MutableList<PlateResponse>? = ArrayList()
    private val TAG = "SearchPlateNumberActi"
    private var apitoken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_search_plate_number)
        apitoken = Prefs.getString(Keys.USER_TOKEN, "")
        val mLayoutManager = LinearLayoutManager(applicationContext)
        rv_list_plate_number.layoutManager = mLayoutManager

        callWebService()

        btn_search_plate_number.setOnClickListener {
            when{
                edt_plate_number.text.toString().trim().isEmpty() -> displayDialog("Plate number must not be empty.")
                else->{
                    val count = edt_plate_number.text.toString().length
                    if(count == 7){
                        //TODO: VALIDATION
                        searchPlateNumber(edt_plate_number.text.toString().trim())

                    }else{
                        displayDialog("Please enter valid plate number.")
                    }
                }
            }
        }


        btn_search_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.exitfrom, R.anim.exitto)
        }
    }

    private fun searchPlateNumber(plateNum: String) {
        val apiService = UserService.create(this.getString(R.string.base_url))

        val callService = apiService.getPlateNumber(plateNum, "application/json", "Bearer $apitoken")
        callService.enqueue(object : retrofit2.Callback<MutableList<PlateData>>{
            override fun onResponse(
                call: Call<MutableList<PlateData>>,
                response: Response<MutableList<PlateData>>
            ) {
                if (response.code()==200){
                    val plateData = response.body()

                   if(plateData!!.size == 0){
                       displayDialog("There's no suchthing like that plate number")
                   }else{
//                       displayDialog("${plateData!![0].user!!.firstName+" " + plateData!![0].user!!.lastName}")
                       val intent = Intent(baseContext, DisplayPlateActivity::class.java)
                       intent.putExtra("plate_number", plateData!![0].plateNumber)
                       intent.putExtra("name_owner", plateData!![0].user!!.firstName+" " + plateData!![0].user!!.lastName)
                       intent.putExtra("mobile_number", plateData!![0].user!!.mobileNumber)
                       intent.putExtra("car_model", plateData!![0].make)
                       intent.putExtra("car_color", plateData!![0].color)
                       startActivity(intent)
                       overridePendingTransition(
                           R.anim.enterfrom,
                           R.anim.exitfrom
                       )
                       finish()
                   }

                }
            }

            override fun onFailure(call: Call<MutableList<PlateData>>, t: Throwable) {
                Log.e(TAG, t.message)
            }


        })
    }

    private fun callWebService() {

        val apiService = UserService.create(this.getString(R.string.base_url))


        val callService = apiService.getPlateNumber(null, "application/json", "Bearer $apitoken")

        callService.enqueue(object : retrofit2.Callback<MutableList<PlateData>> {
            override fun onResponse(
                call: Call<MutableList<PlateData>>,
                response: Response<MutableList<PlateData>>
            ) {
                if (response.code() == 200) {

                    val plateData = response.body()
                    plateAdapter = PlateAdapter(plateData!!, this@SearchPlateNumberActivity)
                    rv_list_plate_number.adapter = plateAdapter


//                    val plateResponse = Gson().fromJson<PlateData>(response.body().toString(),PlateData::class.java)
//                    Log.d(TAG, "${plateResponse.status}")
                }
            }

            override fun onFailure(call: Call<MutableList<PlateData>>, t: Throwable) {
                Log.e(TAG, t.message)
            }


        }
        )

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
}
