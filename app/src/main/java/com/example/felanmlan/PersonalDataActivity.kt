package com.example.felanmlan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_personal_data.*

class PersonalDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{
            val intent = Intent(this, DataSaveActivity::class.java)
            startActivity(intent)

        }
        supportActionBar?.title = "Personuppgifter"
        // Nästa steg spara felanmällna till nästa aktivty i recyckelview

        //Sudda här i från

        val nameEt =findViewById<EditText>(R.id.nameEt)

        val lastEt = findViewById<EditText>(R.id.lastEt)

       // val nrEt = findViewById<EditText>(R.id.nrEt)


        //val emailEt =findViewById<EditText>(R.id.emailEt)

        //val phoneEt =findViewById<EditText>(R.id.phoneEt)

        //val saveBtn =findViewById<EditText>(R.id.saveBtn)

        saveBtn.setOnClickListener{
           val name = nameEt.text.toString()
           //val email = emailEt.text.toString()
          // val phone = phoneEt.text.toString()

            val intent = Intent(this, DataSaveActivity::class.java)
            intent.putExtra( "Name", name)
            intent.putExtra( "Name", name)

           startActivity(intent)



       }
    }



    //val saveBtn =findViewById<EditText>(R.id.saveBtn)

}






