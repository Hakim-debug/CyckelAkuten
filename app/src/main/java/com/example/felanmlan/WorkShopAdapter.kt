package com.example.felanmlan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(private val exList: List<WorkShops>, private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.work_shop_list, parent, false)

        return ViewHolder(itemView)
    }

    //SingLine Code kotlin get the size of the list
    override fun getItemCount() = exList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val currentItem = exList[position]
        holder.imageView.setImageResource(currentItem.imageResources)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2




        holder.itemView.setOnClickListener {

            val intent = Intent(context, MainActivity::class.java)


            context.startActivity(intent)

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
        var personInfo: PersonInfo? = null


    }


}


