package com.example.edp

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
////import android.os.Bundle
////import androidx.appcompat.app.AppCompatActivity
////import kotlinx.android.synthetic.main.activity_datav.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_datav.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.edp.databinding.ActivityDatavBinding
//kotlinx.android.synthetic.main.activity_datav.*

class datav : AppCompatActivity() {

    private var binding: ActivityDatavBinding? = null
    private val updateInterval = 65 * 1000L // 65 seconds in milliseconds
    private val handler = Handler(Looper.getMainLooper())
    private val toastInterval = 62 * 1000 // 30 seconds in milliseconds

    private val toastRunnable = object : Runnable {
        override fun run() {
            showToast("Hello, this is your toast message!")
            handler.postDelayed(this, toastInterval.toLong())
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        // Stop the handler when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(toastRunnable)
    }

    private fun showToast(message: String) {
        fetchDataFromApi()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatavBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        fetchDataFromApi()
        handler.postDelayed(toastRunnable, toastInterval.toLong())
    }

    private fun fetchDataFromApi() {
        val call = RetrofitInstance.apiService.getData()
        call.enqueue(object : Callback<List<MyData>> {
            override fun onResponse(call: Call<List<MyData>>, response: Response<List<MyData>>) {
                if (response.isSuccessful) {
                    val dataList: List<MyData>? = response.body()
                    // Check if dataList is not null and contains at least two elements
                    if (dataList != null && dataList.size >= 2) {
                        val secondBlock = dataList.last() // Access the second element (index 1)
                        // Now you can use the secondBlock object as needed
                        updateUI(secondBlock)
                    } else {
                        // Handle case where dataList is null or does not contain enough elements
                    }
                } else {
                    // Handle unsuccessful response
                }
                scheduleNextUpdate()
            }

            override fun onFailure(call: Call<List<MyData>>, t: Throwable) {
                // Handle network errors
            }
        })
    }

    private fun updateUI(myData: MyData) {
        val formattedLatitude = String.format("%.8f", myData.latitude.toDouble())
        val formattedLongitude = String.format("%.8f", myData.longitude.toDouble())
        val truncatedSpeed = String.format("%.2f", myData.speed.toDouble())
        val truncatedPWM = String.format("%.2f", myData.pwm.toDouble())

        binding?.latitudeTextView?.text = "Latitude: $formattedLatitude"
        binding?.longitudeTextView?.text = "Longitude: $formattedLongitude"
        binding?.timeTextView?.text = "Time: ${myData.time}"
        binding?.weatherConditionTextView?.text = "Weather Condition: ${myData.weatherCondition}"
//        binding?.nearbyPlacesTextView?.text = "Nearby Places: ${myData.nearbyPlaces}"
        binding?.visibilityTextView?.text = "Visibility: ${myData.visibility}"
        binding?.speedTextView?.text = "Speed: $truncatedSpeed km/h"
//        binding?.pwmTextView?.text = "PWM: $truncatedPWM"
        binding?.locationTextView?.text = "Location: ${myData.location}"
    }

    private fun scheduleNextUpdate() {
        // Schedule the next update after the specified interval
        handler.postDelayed({ fetchDataFromApi() }, updateInterval)
    }

}