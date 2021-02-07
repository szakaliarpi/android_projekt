package com.example.android_projekt
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val myThread: Thread = object : Thread() {
            override fun run() {
                try { sleep(1500)
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish() }
                catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        myThread.start()
    }
}