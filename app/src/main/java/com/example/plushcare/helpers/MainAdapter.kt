package com.example.plushcare.helpers

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.plushcare.R
import com.example.plushcare.activities.DocProfileActivity
import com.example.plushcare.models.Appointment
import com.example.plushcare.models.AvailableDoctors
import com.example.plushcare.models.Doctor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_doc_profile.*
import kotlinx.android.synthetic.main.appointments_row.view.*

/**
*Created by Alkhurbush on 05/10/2019.
*/


//view holder class
class MainAdapter(val availableDoctors: AvailableDoctors): RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {



    //return number of items from the appointment list
    override fun getItemCount(): Int {

        return availableDoctors.appointments.count()
    }

    //create a view of available appointments then return the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.appointments_row, parent, false)

        return CustomViewHolder(
            cellForRow,
            appointments = availableDoctors.appointments,
            adapter = this
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

                val appointment = availableDoctors.appointments.get(position)
                val  doctorId = availableDoctors.appointments.get(position).doctor
                val status = appointment.appointment_status
                var appoTime = appointment.appointment_time
//                appoTime = appoTime.split(" ")[1]
                holder.view.textView_appoTime.text = appoTime

                val bookButton = holder.view.findViewById<Button>(R.id.button_book)

                if(!availableDoctors.appointments.get(position).confirmed){
                    bookButton?.setBackgroundResource(R.drawable.book_button);
                } else {
                    bookButton?.setBackgroundResource(R.drawable.btn_book);
                }


                for ( doctor in availableDoctors.doctors) {
                    if(doctorId == doctor.doctor_id) {

                        val doctorName = "Dr. ${doctor.first_name} ${doctor.last_name}"
                        holder.view.textView_drName.text = doctorName
                        holder.view.textView_status.text = status
                        val rating = doctor.average.rolling_average
                        holder.view.textView_Rating.text = rating
                        val doctorImg = holder.view.imageView_doctor
                        Picasso.get().load(doctor.image_url).into(doctorImg)

                        holder?.doctor = doctor
                    }
                }



    }

    class CustomViewHolder(val view: View, var doctor: Doctor? = null, var appointments: List<Appointment>, var adapter : MainAdapter): RecyclerView.ViewHolder(view) {
        companion object {
            val DOCTOR_NAME_KEY = "DOCTOR_NAME"
            val DOCTOR_ID_KEY = "DOCTOR_ID"
            val DOCTOR_IMG_KEY = "DOCTOR_IMG"
            val DOCTOR_BIO_KEY = "DOCTOR_BIO"
            val DOCTOR_RATING_KEY = "DOCTOR_RATING"
            val DOCTOR_RESIDENCY_KEY = "DOCTOR_RESIDENCY"
            val DOCTOR_EXPERIENCE_KEY = "DOCTOR_EXPERIENCE"
//            val DOCTOR_APP_TIME_KEY = "DOCTOR_APP_TIME"
        }

        init {
            view.setOnClickListener {
                val intent = Intent(view.context, DocProfileActivity::class.java)

                val doctorName = "Dr. ${doctor?.first_name} ${doctor?.last_name}"
                intent.putExtra(DOCTOR_NAME_KEY, doctorName)
                intent.putExtra(DOCTOR_ID_KEY, doctor?.doctor_id)
                intent.putExtra(DOCTOR_IMG_KEY, doctor?.image_url)
                intent.putExtra(DOCTOR_BIO_KEY, doctor?.bio)
                intent.putExtra(DOCTOR_RESIDENCY_KEY, doctor?.residency_program)
                intent.putExtra(DOCTOR_EXPERIENCE_KEY, doctor?.years_practiced)
                intent.putExtra(DOCTOR_RATING_KEY, doctor?.average?.rolling_average)
//            intent.putExtra(DOCTOR_APP_TIME_KEY, doctor.)



                view.context.startActivity(intent)
            }

            val bookButton = view.findViewById<Button>(R.id.button_book)
            bookButton.setOnClickListener {
                appointments[adapterPosition].confirmed = !appointments[adapterPosition].confirmed
                adapter.notifyItemChanged(adapterPosition)

            }
        }
    }
}


