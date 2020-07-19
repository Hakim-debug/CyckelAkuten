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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_work_shop.*

class WorkShopActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "com.example.felanmlan"
    private val description = "My Notification"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_shop)
        supportActionBar?.title = "WorkShops"


        val exampleList = generateDummyList(20)

        recycler_view.adapter = Adapter(exampleList, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        val intent = Intent(applicationContext,WorkShopActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel= NotificationChannel(channelId,description, NotificationManager.IMPORTANCE_HIGH )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor= Color.RED
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(applicationContext,channelId)
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
        notificationManager.notify(5,builder.build())


    }

    private fun generateDummyList(size: Int): List<WorkShops> {

        val list = ArrayList<WorkShops>()

        for (i in 0 until size) {
            val drawable = when (i % 4) {
                0 -> R.drawable.ic_home
                1 -> R.drawable.ic_build
                2 -> R.drawable.ic_bike
                3 -> R.drawable.ic_add_a_photo

                else -> R.drawable.ic_add_a_photo
            }

            val item = WorkShops(drawable, "WorkShops $i", "Report Error ")
            list += item
        }

        return list
    }

}








