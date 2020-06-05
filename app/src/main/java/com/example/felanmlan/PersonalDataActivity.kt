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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream


class PersonalDataActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        supportActionBar?.title = "Personal Data"


        bottomNavigationView = findViewById(R.id.bottom_navigation)

        val imageUrl = intent.getStringExtra("imageUrl")

        db = FirebaseFirestore.getInstance()
        //test()


        //Creating Send Button and go to Next activity (DataSaveActivity )
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener {

            val intent = Intent(this, DataSaveActivity::class.java)
            startActivity(intent)

        }
        supportActionBar?.title = "Personal data"

       //Creating a forum and  to show in Next activity (DataSaveActivity )

        val selected = intent.getStringExtra("selected")

        val textSelected = findViewById<TextView>(R.id.selected)

        textSelected.text = selected

        val nameEt = findViewById<EditText>(R.id.nameEt)

        val lastEt = findViewById<EditText>(R.id.lastEt)

        val nrEt = findViewById<EditText>(R.id.nrEt)

        val emailEt = findViewById<EditText>(R.id.emailEt)

        val phoneEt = findViewById<EditText>(R.id.phoneEt)

        val typEt = findViewById<EditText>(R.id.typEt)

        val locEt = findViewById<EditText>(R.id.locEt)


        //Send button
        saveBtn.setOnClickListener {
            Log.i("Test", "Button Clicked")
            val name = nameEt.text.toString()
            val email = emailEt.text.toString()
            val phone = phoneEt.text.toString()

            val lastName = lastEt.text.toString()
            val rec = nrEt.text.toString()
            val typ = typEt.text.toString()
            val location = locEt.text.toString()

            val personInfo = PersonInfo(
                firstName = name,
                lastName = lastName,
                email = email,
                phone = phone,
                personnr = rec,
                typ = typ,
                location = location,
                selected = selected,
                image = imageUrl
            )
            // Log.i("Test", personInfo.firstName)


            var toast = Toast.makeText(applicationContext, "ErrorReport Sent", Toast.LENGTH_LONG)
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
            intent.putExtra("person", personInfo)



            startActivity(intent)

        }

        //BottomNavigationView Action

        val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            println("itemId!!: ${item.itemId}")
            println("bike!!: ${R.id.bike}")
            when (item.itemId) {
                R.id.page_1-> {


                    val intent = Intent(this, PersonalDataActivity::class.java)

                    startActivity(intent)
                    println("Next")

                    true
                }

                R.id.page_2  -> {

                    val intent = Intent(this, WorkShopActivity::class.java)

                    startActivity(intent)

                    println("Next 2")


                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_3-> {

                    val intent = Intent(this, MapsActivity::class.java)

                    startActivity(intent)

                    println("NextHome")

                    true
                }

                R.id.page_4-> {

                    val intent = Intent(this, UserLoginActivity::class.java)

                    startActivity(intent)

                    println("NextHome")

                    true
                }

                else -> false
            }
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)


    }

    class Person(var user: String? = "Kim2")


}






