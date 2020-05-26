package com.example.felanmlan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class UserRecyclerview : AppCompatActivity() {

    private val errorReports = mutableListOf<PersonInfo>()
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_recyclerview)
        supportActionBar?.title = "Job objects"

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
                                errorReports.add(newItem)

                                //println("Works!!!!K: $newItem")
                            }
                        } catch (e: Exception) {
                            println("Error on object: ${e.localizedMessage}")
                        }

                        db.collection("person").document("documentId")
                            .delete()
                            .addOnSuccessListener { Log.d(" ", "DocumentSnapshot successfully deleted!") }
                            .addOnFailureListener { e -> Log.w(" ", "Error deleting document", e) }


                    }
                    val recyclerView = findViewById<RecyclerView>(R.id.recycleViewErrors)

                    recyclerView.layoutManager = LinearLayoutManager(baseContext)

                    val adapter = UserRecycleAdapter(context = this, errors = errorReports)

                    recyclerView.adapter = adapter
                }
            }
        } catch (e: Exception) {
            println("Error on reading FB: ${e.localizedMessage}")
        }
        /*db.collection("person").document("documentId")
            .delete()
            .addOnSuccessListener { Log.d(" ", "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(" ", "Error deleting document", e) }
*/
    }

    //Read data in Firebase
    private fun readData() {

    }
}
