package com.example.plushcare.models

/**
 *Created by Alkhurbush on 05/10/2019.
 */

class AvailableDoctors(val appointments: List<Appointment>, val doctors: List<Doctor>)


class Appointment(val id: Int, val doctor: Int,
                  val appointment_status: String, val appointment_time: String, var confirmed: Boolean = false)
class Doctor(val years_practiced: String, val first_name: String, val last_name: String,
             val image_url: String, val doctor_id: Int, val bio: String, val residency_program: String,
             val average: Average
)

class Average(val rolling_average: String)