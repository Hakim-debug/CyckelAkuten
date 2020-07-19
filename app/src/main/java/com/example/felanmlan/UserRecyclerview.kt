package com.example.felanmlan

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.lang.Exception

class UserRecyclerview : AppCompatActivity() {

    private val errorReports = mutableListOf<PersonInfo>()
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_recyclerview)
        supportActionBar?.title = "Job objects"




        val recyclerView = findViewById<RecyclerView>(R.id.recycleViewErrors)

        recyclerView.layoutManager = LinearLayoutManager(baseContext)

        val adapter = UserRecycleAdapter(context = this, errors = errorReports)

        recyclerView.adapter = adapter

        db = FirebaseFirestore.getInstance()
        try {
            val itemsRef = db.collection("person")

            itemsRef.addSnapshotListener { snapshot, e ->
                if (snapshot != null) {
                    errorReports.clear()
                    for (document in snapshot.documents) {
                        try {
                            val newItem = document.toObject(PersonInfo::class.java)
                            if (newItem != null) {
                                newItem.id = document.id
                                errorReports.add(newItem)

                                //println("Works!!!!K: $newItem")
                            }
                        } catch (e: Exception) {
                            println("Error on object: ${e.localizedMessage}")
                        }


                    }
                    adapter.notifyDataSetChanged()
                }
            }
        } catch (e: Exception) {
            println("Error on reading FB: ${e.localizedMessage}")
        }


    }

    //Read data in Firebase
    private fun readData() {

    }
}
