package com.example.felanmlan

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class UserRecycleAdapter(private val context: Context, private val errors: List<PersonInfo>) :
    RecyclerView.Adapter<UserRecycleAdapter.ViewHolder>() {
    //Is used to create a view out of XML layout
    private val layoutInflater = LayoutInflater.from(context)

    //Here view creates
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.txtName)
        val textViewAge = itemView.findViewById<TextView>(R.id.textAge)
        val textViewEmail = itemView.findViewById<TextView>(R.id.textEmail)
        var buttonDelete: ImageButton = itemView.findViewById(R.id.buttonDelete)
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkboxDone)
        val db = FirebaseFirestore.getInstance()


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



        holder.checkBox.setOnClickListener{

            //Update database
            val data = hashMapOf("serviceDone" to holder.checkBox.isChecked)



            errors[position].id?.let { it1 ->
                holder.db.collection("person").document(it1)
                    .set(data, SetOptions.merge())
                    .addOnSuccessListener {
                        Log.d(
                            " ",
                            "DocumentSnapshot successfully deleted!"
                        )
                    }
                    .addOnFailureListener { e -> Log.w(" ", "Error deleting document", e) }


            }

        }

        holder.buttonDelete.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setTitle("Remove Object?")
                .setMessage("Do you want to Remove this object?")
                .setPositiveButton("Remove", DialogInterface.OnClickListener {

                        dialog, id ->
                    println("id" + errors[position].id)


                    errors[position].id?.let { it1 ->
                        holder.db.collection("person").document(it1)
                            .delete()
                            .addOnSuccessListener {
                                Log.d(
                                    " ",
                                    "DocumentSnapshot successfully deleted!"
                                )
                            }
                            .addOnFailureListener { e -> Log.w(" ", "Error deleting document", e) }


                    }


                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()


                })
            val alert = dialogBuilder.create()

            alert.show()




            println("Delete")

        }
    }
}