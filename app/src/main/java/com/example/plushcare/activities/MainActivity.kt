package com.example.plushcare.activities

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.example.plushcare.models.AvailableDoctors
import com.example.plushcare.helpers.MainAdapter
import com.example.plushcare.R
import com.example.plushcare.models.Appointment
import com.example.plushcare.services.AppointmentService
import com.example.plushcare.services.ServiceBuilder
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import retrofit2.Call
import java.io.IOException
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var textViewMessage : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewMessage = findViewById(R.id.textView_noAppointment)

        recyclerView_main.layoutManager = LinearLayoutManager(this) //as RecyclerView.LayoutManager?

//        fetchJson()
        loadAppointments()

    }

     fun loadAppointments() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.show()

        val appointmentService = ServiceBuilder.buildService(AppointmentService::class.java)

        val requestCall = appointmentService.getAppointmentList()

        requestCall.enqueue(object: Callback<AvailableDoctors> {

            override fun onResponse(call: Call<AvailableDoctors>, response: Response<AvailableDoctors>) {


                if (response.isSuccessful) {
                    val availableDoctors = response.body()

                    if(availableDoctors!=null && availableDoctors.appointments.isEmpty()) {
                      textViewMessage?.text = "No available appointments today!"
                   }
                   if(availableDoctors!=null) {
                        recyclerView_main.adapter = MainAdapter(availableDoctors)
                    }
                } else {
                    textViewMessage?.text = "An error has occurred"
                }

                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<AvailableDoctors>, t: Throwable) {
                textViewMessage?.text = "An error has occurred"
                progressDialog.dismiss()
            }

        })

    }


    //Fetching Json using OkHttp
//     fun fetchJson() {
//
//        println("fetching JSON...")
//
//        val sdf = SimpleDateFormat("MMddyyyy")
//        val date = sdf.format(Date())
//
//
//        val url = "https://www.plushcare.com/appointments/internal/next/$date/CA/"
//
//        val request = Request.Builder().url(url).build()
//        val client = OkHttpClient()
//
//        client.newCall(request).enqueue(object: Callback {
//            override fun onResponse(call: Call, response: Response) {
//                val body = response.body()?.string()
//                println(body)
//
//                val gson = GsonBuilder().create()
//                val availableDoctors : AvailableDoctors? = gson.fromJson(body, AvailableDoctors::class.java)
//
//
//                runOnUiThread {
//                    if(availableDoctors!=null && availableDoctors.appointments?.isEmpty()) {
//                        textViewMessage?.text = "No available appointments today!"
//                    }
//                    if(availableDoctors!=null) {
//                        recyclerView_main.adapter = MainAdapter(availableDoctors)
//                    }
//
//
//                }
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                println("Failed to execute the request")
//                runOnUiThread {
//                    textViewMessage?.text = "An error has occurred"
//                }
//
//            }
//        })
//    }
}
