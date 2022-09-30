package binar.academy.chapter5topic2

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class DangerousActivity : AppCompatActivity() {

    private val requestCode = 445

    private lateinit var currentStatus : TextView
    private lateinit var btnAccess : Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dangerous)

        currentStatus = findViewById(R.id.tvStatusLocation)

        btnAccess = findViewById(R.id.btnRequest)
        btnAccess.setOnClickListener {
            if (!izinLokasi()) {
                currentStatus.text =  "Mencoba mengakses lokasi"
            } else {
                Toast.makeText(this, "Telah diberikan izin", Toast.LENGTH_SHORT).show()
                currentStatus.text =  "Telah diberikan izin"
            }
        }

        when {
            !izinLokasi() -> return
            else -> {
                currentStatus.text = "Telah diberikan izin"
                btnAccess.visibility = View.GONE
            }
        }
    }


    private fun izinLokasi(): Boolean {

        val locationFine = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val locationCoarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)


        val listPermissionsNeed = ArrayList<String>()

        if (locationFine != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (locationCoarse != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (listPermissionsNeed.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeed.toTypedArray(), requestCode)
            return false
        }

        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != this.requestCode) return
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Tracking Location Success !", Toast.LENGTH_SHORT).show()

            currentStatus.text =  "Diizinkan untuk akses GPS"
            btnAccess.visibility = View.GONE
        } else currentStatus.text =  "Tak ada izin untuk akses GPS"
    }
}