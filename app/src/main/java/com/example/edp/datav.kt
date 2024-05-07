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
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_datav.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.edp.databinding.ActivityDatavBinding
//kotlinx.android.synthetic.main.activity_datav.*

class datav : AppCompatActivity() {

    private var binding: ActivityDatavBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatavBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val call = RetrofitInstance.apiService.getData()
        call.enqueue(object : Callback<List<MyData>> {
            override fun onResponse(call: Call<List<MyData>>, response: Response<List<MyData>>) {
                if (response.isSuccessful) {
                    val dataList: List<MyData>? = response.body()
                    // Check if dataList is not null and contains at least two elements
                    if (dataList != null && dataList.size >= 2) {
                        val secondBlock = dataList[1] // Access the second element (index 1)
                        // Now you can use the secondBlock object as needed
                        updateUI(secondBlock)
                    } else {
                        // Handle case where dataList is null or does not contain enough elements
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<List<MyData>>, t: Throwable) {
                // Handle network errors
            }
        })
    }

    private fun updateUI(myData: MyData) {
        // Update UI components with the second block of data
        binding?.latitudeTextView?.text = "Latitude: ${myData.latitude}"
        binding?.longitudeTextView?.text = "Longitude: ${myData.longitude}"
        binding?.timeTextView?.text = "Time: ${myData.time}"
        binding?.weatherConditionTextView?.text = "Weather Condition: ${myData.weatherCondition}"
        binding?.nearbyPlacesTextView?.text = "Nearby Places: ${myData.nearbyPlaces}"
        binding?.visibilityTextView?.text = "Visibility: ${myData.visibility}"
        binding?.speedTextView?.text = "Speed: ${myData.speed}" // Add this line to update speed
        binding?.pwmTextView?.text = "PWM: ${myData.pwm}" // Add this line to update PWM
        binding?.vTextView?.text = "__v: ${myData.v}" // Add this line to update __v
    }
}