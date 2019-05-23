package com.example.plushcare.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.plushcare.helpers.MainAdapter
import com.example.plushcare.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_doc_profile.*
import kotlinx.android.synthetic.main.content_doc_profile.*

class DocProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_profile)


        val doctorName = intent.getStringExtra(MainAdapter.CustomViewHolder.DOCTOR_NAME_KEY)
        var button_background : Int = 1
        val fab1 = findViewById<FloatingActionButton>(R.id.fab)
        var button_fab : Int = 1

        fab1.setOnClickListener {
            if(button_fab == 2){
                fab1.setImageResource(R.drawable.ic_fav)
                button_fab = 1
            } else if (button_fab == 1) {
                fab1.setImageResource(R.drawable.ic_favo)
                Snackbar.make(toolbar, "$doctorName is added to your favorite list!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                button_fab = 2
            }
        }

        button_appointment.setOnClickListener {
            if(button_background == 2 ){
                button_appointment.setBackgroundResource(R.drawable.book_button);
                button_background = 1
            } else if(button_background == 1) {
                button_appointment.setBackgroundResource(R.drawable.btn_book);
                    Snackbar.make(toolbar, "Your appointment has been confirmed!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                button_background = 2
            }
        }

        setSupportActionBar(toolbar)
        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "$doctorName is added to your favorite list!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        } */

//        toolbar.title = intent.getStringExtra((CustomViewHolder.DOCTOR_NAME_KEY))

        toolbar.title =  intent.getStringExtra(MainAdapter.CustomViewHolder.DOCTOR_NAME_KEY)
        val doctorImg = intent.getStringExtra(MainAdapter.CustomViewHolder.DOCTOR_IMG_KEY)
        val doctorBio = intent.getStringExtra(MainAdapter.CustomViewHolder.DOCTOR_BIO_KEY)
        val doctorResidency = intent.getStringExtra(MainAdapter.CustomViewHolder.DOCTOR_RESIDENCY_KEY)
        val doctorExperience = intent.getStringExtra(MainAdapter.CustomViewHolder.DOCTOR_EXPERIENCE_KEY)
        val doctorRating = intent.getStringExtra(MainAdapter.CustomViewHolder.DOCTOR_RATING_KEY)


//        val doctorId = intent.getIntExtra(MainAdapter.CustomViewHolder.DOCTOR_ID_KEY, -1)
//        println("Doctor ID: $doctorId")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        textView_nname.text = doctorName
        textView_residency.text = doctorResidency
        textView_Rating.text = doctorRating
        textView_expe.text = "$doctorExperience Years"
        textView_Bio.text = doctorBio

        Picasso.get().load(doctorImg).into(doc_Img)

    }
}


