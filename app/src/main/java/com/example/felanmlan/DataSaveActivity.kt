package com.example.felanmlan

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DataSaveActivity : AppCompatActivity() {
     lateinit var selected : TextView


    lateinit var  nameEt: TextView

    lateinit var  lastEt:TextView

    lateinit var  nrEt: TextView

    lateinit var  emailEt:TextView

    lateinit var  phoneEt: TextView

    lateinit var  typEt: TextView

    lateinit var  locEt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_save)
        supportActionBar?.title = "Overview"

        nameEt = findViewById(R.id.textViewFirstName)
        lastEt = findViewById(R.id.textViewlastName)
        this.nrEt = findViewById(R.id.textViewPersonNr)
        emailEt =findViewById(R.id.textViewEmail)
        phoneEt =findViewById(R.id.textViewPhone)
        typEt = findViewById(R.id.textViewModel)
        locEt = findViewById(R.id.textViewLocation)
        selected = findViewById(R.id.textViewSelected)



        var intent = intent

        val personInfo = intent.getSerializableExtra("person") as PersonInfo

        nameEt.text = personInfo.firstName
        lastEt.text = personInfo.lastName
        nrEt.text= personInfo.personnr
        emailEt.text = personInfo.email
        phoneEt.text = personInfo.phone
        typEt.text = personInfo.typ
        locEt.text = personInfo.location
        selected.text = personInfo.selected



    }

}