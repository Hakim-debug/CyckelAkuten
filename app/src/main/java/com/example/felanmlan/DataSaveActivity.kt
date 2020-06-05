package com.example.felanmlan

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DataSaveActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    //Show the formText in a TextView
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

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        nameEt = findViewById(R.id.textViewFirstName)
        lastEt = findViewById(R.id.textViewlastName)
        nrEt = findViewById(R.id.textViewRequest)
        emailEt =findViewById(R.id.textViewEmail)
        phoneEt =findViewById(R.id.textViewPhone)
        typEt = findViewById(R.id.textViewModel)
        locEt = findViewById(R.id.textViewLocation)
        selected = findViewById(R.id.textViewSelected)



        var intent = intent

        val personInfo = intent.getSerializableExtra("person") as PersonInfo
//Display the Persons info in a cardView
        nameEt.text = personInfo.firstName
        lastEt.text = personInfo.lastName
        nrEt.text = personInfo.personnr
        emailEt.text = personInfo.email
        phoneEt.text = personInfo.phone
        typEt.text = personInfo.typ
        locEt.text = personInfo.location
        selected.text = personInfo.selected


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

                    val intent = Intent(this, MainActivity::class.java)

                    startActivity(intent)

                    println("NextHome")

                    true
                }

                else -> false
            }
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)



    }

}