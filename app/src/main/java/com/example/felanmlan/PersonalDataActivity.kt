package com.example.felanmlan

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class PersonalDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        println("Hakim")

        val bitmap = intent.getParcelableExtra<Parcelable>("image") as Bitmap

        val image = findViewById<ImageView>(R.id.imageView)

        image.setImageBitmap(bitmap)






        val name =  intent.getStringExtra("Name")

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{

            val intent = Intent(this, DataSaveActivity::class.java)
            startActivity(intent)

        }
        supportActionBar?.title = "Personuppgifter"




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










        saveBtn.setOnClickListener{
            Log.i("Test", "Button Clicked")
            val name = nameEt.text.toString()
            val email = emailEt.text.toString()
            val phone = phoneEt.text.toString()
            val lastName = lastEt.text.toString()
            val nr = nrEt.text.toString()
            val typ = typEt.text.toString()
            val location = locEt.text.toString()

            val personInfo = PersonInfo(firstName = name, lastName = lastName,email = email,phone = phone,personnr = nr,typ = typ,
            location = location,selected = selected)



            //Här skapar du din databas
            val db = FirebaseFirestore.getInstance()

            val PersonInfo: MutableMap<String, Any> = HashMap()
            personInfo["phone"] = "phone"
            user["last"] = "Lovelace"
            user["born"] = 1815

// Add a new document with a generated ID

// Add a new document with a generated ID
            db.collection("person")
                .add(PersonInfo)
                .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                    Log.d("!!!",
                        "DocumentSnapshot added with ID: " + documentReference.id)
                })
                .addOnFailureListener(OnFailureListener { e ->
                    Log.w("!!!",
                        "Error adding document",
                        e
                    )
                })






            val intent = Intent(this, DataSaveActivity::class.java)
            intent.putExtra("person",personInfo)


           startActivity(intent)



       }
    }



}






