package com.example.edp

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.edp.databinding.ActivityUserPageBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class UserPage : AppCompatActivity() , OnMapReadyCallback {

    //    private var mGoogleMap:GoogleMap?=null
    private var binding:ActivityUserPageBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.ivFriends?.setOnClickListener {    startActivity(Intent(this,InfoActivity::class.java))
             }



//        setContentView(com.example.edp.R.layout.activity_user_page)

        val mapFragment = supportFragmentManager
            .findFragmentById(com.example.edp.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }










    override fun onMapReady(googleMap: GoogleMap) {
        googleMap?.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }
}