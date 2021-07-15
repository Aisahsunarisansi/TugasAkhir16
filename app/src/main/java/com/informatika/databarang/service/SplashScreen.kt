package com.informatika.databarang.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.informatika.databarang.R
        class SplashScreen : AppCompatActivity(){
            override fun onCreate(savedInstanceState: Bundle?){
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_splash_screen)
                handler().postDelayed({
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }, 3000)
            }
        }

