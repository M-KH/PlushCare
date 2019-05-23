package com.example.plushcare.services

import com.example.plushcare.models.Appointment
import com.example.plushcare.models.AvailableDoctors
import retrofit2.Call
import retrofit2.http.GET

interface AppointmentService {

    @GET("CA/") //state
    fun getAppointmentList() : Call<AvailableDoctors>
}