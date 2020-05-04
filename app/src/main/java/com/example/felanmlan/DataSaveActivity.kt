package com.example.felanmlan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_data_save.*
import kotlinx.android.synthetic.main.activity_personal_data.*

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
        supportActionBar?.title = "Översikt"
        nameEt = findViewById(R.id.textViewFirstName)
        lastEt = findViewById(R.id.textViewlastName)
        nrEt = findViewById(R.id.textViewPersonNr)
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



       /* val name =  intent.getStringExtra("Name")
        println(name)

        val email =  intent.getStringExtra("Email")
        println(email)

         val phone =  intent.getStringExtra("Mobil")
        println(phone)

         val lastName = intent.getStringExtra("Efternamn")

         val nr =  intent.getStringExtra("Person nr")

         val typ =  intent.getStringExtra("Typ Av Cyckel?")

         val location =  intent.getStringExtra("Vart Står Cyckel Parkerad?")
        val selected =  intent.getStringExtra("selected")

*/

//textView
       /* val resultTv = findViewById<TextView>(R.id.resultTv)

        resultTv.text = "Name:"+name+"\nEfternamn:"+lastName+"\nPerson nr: "+nr+"\nEmail:"+email+
                "\nMobil:"+phone+"\nTyp Av Cyckel?"+typ+"\nVart Står Cyckel Parkerad?"+location+"\nselected:"+selected*/



    }

}