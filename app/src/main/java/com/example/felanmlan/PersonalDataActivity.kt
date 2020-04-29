package com.example.felanmlan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_personal_data.*

class PersonalDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)



        val name =  intent.getStringExtra("Name")

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{

            val intent = Intent(this, DataSaveActivity::class.java)
            startActivity(intent)

        }
        supportActionBar?.title = "Personuppgifter"
        // Nästa steg spara felanmällna till nästa aktivty i recyckelview

        //Sudda här i från

        //val binding = MyBinding.inflate(...)
       /// binding.greeting.text = "Hakim"

        var intent = intent

        val selected =  intent.getStringExtra("selected")

        val textSelected = findViewById<TextView>(R.id.selected)

        textSelected.text=selected

        val nameEt =findViewById<EditText>(R.id.nameEt)

        val lastEt = findViewById<EditText>(R.id.lastEt)

        val nrEt = findViewById<EditText>(R.id.nrEt)

        val emailEt =findViewById<EditText>(R.id.emailEt)

        val phoneEt =findViewById<EditText>(R.id.phoneEt)

        val typEt  = findViewById<EditText>(R.id.typEt)

        val locEt  = findViewById<EditText>(R.id.locEt)

       // val checkBox = findViewById<CheckBox>(R.id.check_puncture)








        saveBtn.setOnClickListener{
            val name = nameEt.text.toString()
            val email = emailEt.text.toString()
            val phone = phoneEt.text.toString()
            val lastName = lastEt.text.toString()
            val nr = nrEt.text.toString()
            val typ = typEt.text.toString()
            val location = locEt.text.toString()
           // val checkBox = checkBox.text.toString()
            val personInfo = PersonInfo(firstName = name, lastName = lastName,email = email,phone = phone,personnr = nr,typ = typ,
            location = location,selected = selected)



            val intent = Intent(this, DataSaveActivity::class.java)
            intent.putExtra("person",personInfo)
           /* intent.putExtra( "Name", name)
            intent.putExtra( "Email", email)
            intent.putExtra( "Mobil", phone)
            intent.putExtra( "Efternamn",lastName )
            intent.putExtra( "Person nr",nr )
            intent.putExtra( "Typ Av Cyckel?",typ )
            intent.putExtra( "Vart Står Cyckel Parkerad?",location )
            intent.putExtra("selected", selected)*/
           // intent.putExtra( name = "Fel",checkBox)


           startActivity(intent)



       }
    }



}






