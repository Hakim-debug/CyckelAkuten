package com.example.felanmlan

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream


class PersonalDataActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    fun  test(){
        val persons = Person("Hakim")

        db.collection("users").add(persons)
            .addOnCompleteListener {
                println("set!!!")

            }.addOnCanceledListener {
                println("cancel!!!")

            }
            .addOnSuccessListener {
                println("write!!!")


            }
            .addOnFailureListener {
                println("did not write!!!") }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        println("Hakim")

        val imageUrl = intent.getStringExtra("imageUrl")

        db = FirebaseFirestore.getInstance()
        test()





     //Creating Send Button and go to Next activity (DataSaveActivity )
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{

            val intent = Intent(this, DataSaveActivity::class.java)
            startActivity(intent)

        }
        supportActionBar?.title = "Personal data"

       //Creating a forum and  to show in Next activity (DataSaveActivity )

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





        //Send button
        saveBtn.setOnClickListener{
            Log.i("Test", "Button Clicked")
            val name = nameEt.text.toString()
            val email = emailEt.text.toString()
            val phone = phoneEt.text.toString()

            val lastName = lastEt.text.toString()
            val rec = nrEt.text.toString()
            val typ = typEt.text.toString()
            val location = locEt.text.toString()

            val personInfo = PersonInfo(firstName = name, lastName = lastName,email = email,phone = phone,personnr = rec,typ = typ,
            location = location,selected = selected,image = imageUrl)
           // Log.i("Test", personInfo.firstName)







            var toast=Toast.makeText(applicationContext,"ErrorReport Sent",Toast.LENGTH_LONG )
            toast.show()





            //Add Data Firebase
            db.collection("person")


                .add(personInfo)


                .addOnCompleteListener {


                    println("complete")



                }.addOnCanceledListener {
                    println("cancel3")
                   // readData()



                }
                .addOnSuccessListener {

                    println("write")
                    


                }
                .addOnFailureListener {
                    println("did not write")

                }

            val intent = Intent(this, DataSaveActivity::class.java)
            intent.putExtra("person",personInfo)
            


           startActivity(intent)

       }

    }

    class Person(var user:String?="Kim2")




}






