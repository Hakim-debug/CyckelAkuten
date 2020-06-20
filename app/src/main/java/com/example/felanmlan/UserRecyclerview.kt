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
import java.lang.Exception

class UserRecyclerview : AppCompatActivity() {

    private val errorReports = mutableListOf<PersonInfo>()
    private lateinit var db: FirebaseFirestore
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "com.example.felanmlan"
    private val description = "My Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_recyclerview)
        supportActionBar?.title = "Job objects"
        val checkBox = findViewById<CheckBox>(R.id.checkboxDone)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        checkBox.setOnClickListener{
            val intent = Intent(this,DataSaveActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel=NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH )
                notificationChannel.enableLights(true)
                notificationChannel.lightColor=Color.RED
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)
                builder = Notification.Builder(this,channelId)
                    .setContentTitle("Bicycle Assistants")
                    .setContentText("Bicycle ready for pickup")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)


            }
            else{
                builder = Notification.Builder(this)
                    .setContentTitle("Bicycle Assistants")
                    .setContentText("Bicycle ready for pickup")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)


            }
            notificationManager.notify(0,builder.build())

        }


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
