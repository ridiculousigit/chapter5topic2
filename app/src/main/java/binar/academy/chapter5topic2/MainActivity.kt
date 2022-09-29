@file:Suppress("DEPRECATION")

package binar.academy.chapter5topic2

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkConnectionType()
    }

    private fun checkConnectionType()
    {
        val connectionManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiConnection = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileConnection = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifiConnection?.isConnectedOrConnecting == true)
        {
            toast("WIFI Connection is on")
        }
        else
        {
            if (mobileConnection?.isConnectedOrConnecting == true)
            {
                toast("Mobile Data Connection is on")
            }
            else
            {
                toast("No Network Connection")
            }
        }
    }

    fun toast(msg: String)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}