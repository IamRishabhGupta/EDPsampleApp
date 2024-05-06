package com.example.edp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
//            Log.e("aa gaya", "1 one")
//            var currentUserId= FirestoreClass().getCurrentUserId()
//            Log.e("aa gaya", "2 two")
//            if(currentUserId.isNotEmpty()){
//                Log.e("aa gaya", "3 Three ${currentUserId}")
//                startActivity(Intent(this, MainActivity::class.java))
//            }else{
                Log.e("aa gaya", "2 Four")
                startActivity(Intent(this, datav::class.java))
//            }
            finish()
        },3000)
    }
}