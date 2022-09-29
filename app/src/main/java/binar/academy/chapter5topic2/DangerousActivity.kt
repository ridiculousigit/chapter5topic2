package binar.academy.chapter5topic2

import android.Manifest
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

    val REQUEST_CODE = 445

    lateinit var tvStatusIzin : TextView
    lateinit var btnMain : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dangerous)

        tvStatusIzin = findViewById(R.id.main_tv_status)

        btnMain = findViewById(R.id.main_btn_call)
        btnMain.setOnClickListener {
            if (izinLokasi()){
                Toast.makeText(this, "Izin sudah diberikan", Toast.LENGTH_SHORT).show()
                tvStatusIzin.text =  "Izin sudah diberikan"
            }else{
                tvStatusIzin.text =  "Meminta izin akses lokasi"
            }
        }

        if (izinLokasi()){
            tvStatusIzin.text =  "Izin sudah diberikan"
            btnMain.visibility = View.GONE
        }
    }


    fun izinLokasi(): Boolean {

        val r_lokasi = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val r_lokasi_coarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)


        val listPermissionsNeed = ArrayList<String>()

        if (r_lokasi != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (r_lokasi_coarse != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeed.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (!listPermissionsNeed.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeed.toTypedArray(), REQUEST_CODE)
            return false
        }

        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE){
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Izin diberikan", Toast.LENGTH_SHORT).show()

                tvStatusIzin.text =  "Izin diberikan"
                btnMain.visibility = View.GONE
            }else{
                tvStatusIzin.text =  "Izin tidak diberikan"
            }
        }
    }
}