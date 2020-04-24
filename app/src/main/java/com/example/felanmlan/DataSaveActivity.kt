package com.example.felanmlan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DataSaveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_save)
        supportActionBar?.title = "Översikt"

      val name =  intent.getStringExtra("Name")
        println(name)

    }

}
