@file:Suppress("DEPRECATION")

package binar.academy.chapter5topic2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectionType()

        btnMove.setOnClickListener {
            val move = Intent(this, DangerousActivity :: class.java)
            startActivity(move)
        }
    }

    private fun connectionType()
    {
        val connectionManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val koneksiWiFi = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val koneksidataSeluler = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (koneksiWiFi?.isConnectedOrConnecting != true) {
            if (koneksidataSeluler?.isConnectedOrConnecting == true) {
                toast("Koneksi Data Seluler Menyala !")
            } else {
                toast("Tak Ada Koneksi Internet !")
            }
        } else {
            toast("Koneksi WiFi Menyala !")
        }
    }

    fun toast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}