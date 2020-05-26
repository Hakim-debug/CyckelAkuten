package com.example.felanmlan

import android.app.AlertDialog
import android.view.View
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_data_save.view.*
import kotlinx.android.synthetic.main.activity_personal_data.view.*
import kotlinx.android.synthetic.main.errors_item.view.*
import java.lang.Error


class UserRecycleAdapter(private val context: Context, private val errors: List<PersonInfo>) :
    RecyclerView.Adapter<UserRecycleAdapter.ViewHolder>() {
    //Is used to create a view out of XML layout
    private val layoutInflater = LayoutInflater.from(context)

    //Here view creates
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textName)
        val textViewAge = itemView.findViewById<TextView>(R.id.textAge)
        val textViewEmail = itemView.findViewById<TextView>(R.id.textEmail)
        var buttonDelete: ImageButton = itemView.findViewById(R.id.buttonDelete)




        var personInfo: PersonInfo? = null

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //is using inflator to creat a view
        val itemView = layoutInflater.inflate(R.layout.errors_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = errors.size

    //Here data binds with View
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val person = errors[position]
        holder.personInfo = person
        println("Pers: ${person.firstName}")



        holder.textViewName.text = person.firstName + " " + person.lastName + " " + person.email
        holder.textViewAge.text = person.phone
        holder.textViewEmail.text = person.email


        holder.itemView.setOnClickListener {

            println("Line 36")
            if (holder.personInfo != null) {

                val intent = Intent(context, ChosenActivity::class.java)

                intent.putExtra("personInfo", person)
                //intent.putExtra("imageUrl",personInfo)

                context.startActivity(intent)
            }


        }

        holder.buttonDelete.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setTitle("Remove Object?")
                .setMessage("Do you want to Remove this object?")

            val alert = dialogBuilder.create()

            alert.show()

            // Snackbar.make(view, text"Rmovd",)


            println("Delete")

        }
    }
}