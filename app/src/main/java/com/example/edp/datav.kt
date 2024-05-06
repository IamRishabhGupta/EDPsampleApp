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
import kotlinx.android.synthetic.main.activity_datav.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//kotlinx.android.synthetic.main.activity_datav.*

class datav : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datav)

        // Make network request to fetch data
        val call = RetrofitInstance.apiService.getData()

        call.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                if (response.isSuccessful) {
                    val myData: MyData? = response.body()
                    // Update UI with fetched data
                    updateUI(myData)
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                // Handle network errors
            }
        })
    }

    private fun updateUI(myData: MyData?) {
        // Update UI components with fetched data
        latitudeTextView.text = "Latitude: ${myData?.latitude}"
        longitudeTextView.text = "Longitude: ${myData?.longitude}"
        timeTextView.text = "Time: ${myData?.time}"
        weatherConditionTextView.text = "Weather Condition: ${myData?.weatherCondition}"
        nearbyPlacesTextView.text = "Nearby Places: ${myData?.nearbyPlaces}"
        visibilityTextView.text = "Visibility: ${myData?.visibility}"
    }
}

