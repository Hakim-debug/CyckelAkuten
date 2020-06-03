package com.example.felanmlan

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_work_shop.*

class WorkShopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_shop)
        supportActionBar?.title = "WorkShops"





        val exampleList = generateDummyList(20)

        recycler_view.adapter = Adapter(exampleList,this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
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

            val item =WorkShops(drawable, "WorkShops $i", "Report Error ")
            list += item
        }

        return list
    }

}
       /*//WorkShopAdapter(applicationContext, arrayListOf(WorkShops) )



        var recyclerview = findViewById<RecyclerView>(R.id.recycleView)
        var arrShops:ArrayList<WorkShops> = ArrayList()
       var workShopAdapter = recyclerview.adapter
        arrShops.add(WorkShops("Bike",R.drawable.ic_bike))
        arrShops.add(WorkShops("Shops",R.drawable.ic_home))


        recyclerview.adapter = workShopAdapter(applicationContext,arrShops)
        //WorkShopAdapter(workshop = this)*/







