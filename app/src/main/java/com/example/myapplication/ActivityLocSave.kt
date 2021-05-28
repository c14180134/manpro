package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.datalokasi.Location
import com.example.myapplication.datalokasi.LocationViewModel
import com.example.myapplication.ui.listsaveloc.ListlocAdapter

class ActivityLocSave : AppCompatActivity() {
        lateinit var mLocationViewModel: LocationViewModel
        var x = 0
        var getlong:Double??=0.0
        var getlat:Double??=0.0
        private var LocList = emptyList<Location>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loc_save)
        val button: Button = findViewById<View>(R.id.update_button) as Button
        val m: EditText = findViewById(R.id.noteUp)
        val adapter = ListlocAdapter()
        //memanggil database
        mLocationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        //memanggil fungsi insert to database yang dapat digunakan untuk menambahkan data ke table location
        button.setOnClickListener {
            insertDatatoDatabase()
        }
        getlong = getIntent().extras?.getDouble("long",0.0)
        getlat = getIntent().extras?.getDouble("lat",0.0)

        Log.d("longs",getlong.toString())
        Log.d("lat",getlat.toString())
    }

    private fun insertDatatoDatabase(){
        val LName= findViewById<EditText>(R.id.locUpname).text.toString()
        val comment = findViewById<EditText>(R.id.noteUp).text.toString()
        val Rnilai = findViewById<RatingBar>(R.id.ratingBar)
        val lock = Location(0,LName, this.getlong!!,this.getlat!!,comment,Rnilai.rating)
        if(LName != "" && Rnilai.rating>0){
            mLocationViewModel.addLocation(lock)
            Toast.makeText(this,"succesfull", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else{
            Toast.makeText(this,"Data Kurang Lengkap!", Toast.LENGTH_LONG).show()
        }


    }
}