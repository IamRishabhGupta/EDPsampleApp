package com.example.edp

import retrofit2.Call
import retrofit2.http.GET


//import retrofit2.Call
//import retrofit2.http.GET

interface ApiService {
    @GET("details")
    fun getData(): Call<List<MyData>>
}
