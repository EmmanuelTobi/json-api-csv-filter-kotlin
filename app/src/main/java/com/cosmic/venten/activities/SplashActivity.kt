package com.cosmic.venten.activities

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.cosmic.venten.R

class SplashActivity : AppCompatActivity() {

    internal var handler = Handler()
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        this.context = this@SplashActivity

        if(isNetworkAvailable()) {

            handler.postDelayed(postTask,
                delayTime
            )

        } else {

            Toast.makeText(context, "Sorry, no internet connection", Toast.LENGTH_LONG).show()
        }
    }

    protected fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onDestroy() {
        handler.removeCallbacks(postTask)
        super.onDestroy()
    }

    internal var postTask: Runnable = Runnable {

        startActivity(Intent(context, MainActivity::class.java))
        overridePendingTransition(0, 0)
        finish()
    }

    companion object {

        private val delayTime: Long = 1000
    }
}