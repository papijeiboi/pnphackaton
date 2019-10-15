package com.jemoje.pnphackaton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.jemoje.pnphackaton.R
import com.jemoje.pnphackaton.adapters.PlateAdapter
import com.jemoje.pnphackaton.constant.Keys
import com.jemoje.pnphackaton.model.PlateData
import com.jemoje.pnphackaton.model.ScanStaffResponse
import com.jemoje.pnphackaton.webervice.UserService
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_scan_qr_staff.*
import kotlinx.android.synthetic.main.activity_search_plate_number.*
import retrofit2.Call
import retrofit2.Response

class ScanQrStaffActivity : AppCompatActivity() {
    private val TAG = "ScanQrStaffActivity"
    private lateinit var codeScanner: CodeScanner
    private var apitoken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr_staff)
        apitoken = Prefs.getString(Keys.USER_TOKEN, "")
        btn_scan_staff_back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.exitfrom, R.anim.exitto)
        }

        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view_staff)
        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
                scanWebService(it.text)
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        codeScanner.startPreview()
    }

    private fun scanWebService(code: String) {
        val apiService = UserService.create(this.getString(R.string.base_url))
        val callService = apiService.scanStaff(code,  "Bearer $apitoken")

        callService.enqueue(object : retrofit2.Callback<ScanStaffResponse> {
            override fun onResponse(
                call: Call<ScanStaffResponse>,
                response: Response<ScanStaffResponse>
            ) {
                if (response.isSuccessful()) {

                    runOnUiThread {

                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        applicationContext.startActivity(intent)
                        overridePendingTransition(R.anim.enterfrom, R.anim.enterto)

                    }
                    finish()

                }
            }

            override fun onFailure(call: Call<ScanStaffResponse>, t: Throwable) {
                Log.e(TAG, t.message)
            }


        }
        )
    }
}
