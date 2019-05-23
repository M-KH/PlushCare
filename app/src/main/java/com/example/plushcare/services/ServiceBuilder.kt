package com.example.plushcare.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date

object ServiceBuilder {

    val sdf = SimpleDateFormat("MMddyyyy")
    val date = sdf.format(Date())
    private var url =  "https://www.plushcare.com/appointments/internal/next/$date/"

    //OkHttp Client
    private val okHttp = OkHttpClient.Builder()

    //Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(
        okHttp.build())

    // Retrofit Instance
    private val retrofit = builder.build()


    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}