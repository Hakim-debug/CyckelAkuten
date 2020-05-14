package com.example.felanmlan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore


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
                println("did not write!!!")
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        println("Hakim")

        db = FirebaseFirestore.getInstance()
        test()
       // displayFire()





      //  val name =  intent.getStringExtra("Name")

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{

            val intent = Intent(this, DataSaveActivity::class.java)
            startActivity(intent)

        }
        supportActionBar?.title = "Personal data"




       // var intent = intent

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
            location = location,selected = selected)
           // Log.i("Test", personInfo.firstName)





     var toast=Toast.makeText(applicationContext,"ErrorReport Sent",Toast.LENGTH_LONG )
            toast.show()
            db.collection("person")
                .add(personInfo)
                .addOnCompleteListener {
                    println("complete")
                    displayFire()

                }.addOnCanceledListener {
                    println("cancel3")


                }
                .addOnSuccessListener {
                    println("write")

                }
                .addOnFailureListener {
                    println("did not write")

                }

            /*db.collection("users")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d(
                                FragmentActivity.TAG,
                                document.id + " => " + document.data
                            )
                        }
                    } else {
                        Log.w(
                            FragmentActivity.TAG,
                            "Error getting documents.",
                            task.exception
                        )
                    }
                }
*/

            val intent = Intent(this, DataSaveActivity::class.java)
            intent.putExtra("person",personInfo)


           startActivity(intent)



       }

    }
    class Person(var user:String?="Kim2")

private fun displayFire(){
    val shoppingItems = mutableListOf<PersonInfo>()

    val itemsRef = db.collection("person")

    itemsRef.addSnapshotListener { snapshot, e ->
        if( snapshot != null ) {
            shoppingItems.clear()
            for(document in snapshot.documents) {
                val newItem = document.toObject(PersonInfo::class.java)
                if (newItem != null)
                    shoppingItems.add(newItem!!)
                if (newItem != null) {
                    println("Works!!!!: ${newItem.firstName!!}")
                }
            }
        }
    }
}

}






