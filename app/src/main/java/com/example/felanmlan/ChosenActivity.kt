package com.example.felanmlan

import android.R.attr.bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_user_recyclerview.*
import java.lang.Exception


class ChosenActivity : AppCompatActivity() {

    private var personInfo: PersonInfo? = null
    private lateinit var textViewFirstName: TextView
    private lateinit var textViewlastName: TextView
    private lateinit var textViewRequest: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var textViewModel: TextView
    private lateinit var textViewLocation: TextView
    private lateinit var textViewSelected: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chosen)
        supportActionBar?.title = "Case Report"

        textViewFirstName = findViewById(R.id.textViewFirstName)
        textViewlastName = findViewById(R.id.textViewlastName)
        textViewRequest = findViewById(R.id.textViewRequest)
        textViewEmail = findViewById(R.id.textViewEmail)
        textViewPhone = findViewById(R.id.textViewPhone)
        textViewModel = findViewById(R.id.textViewModel)
        textViewLocation = findViewById(R.id.textViewLocation)
        textViewSelected = findViewById(R.id.textViewSelected)

        try {
            personInfo = intent.getSerializableExtra("personInfo") as PersonInfo
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
        println("PersonInfo: $personInfo")
        val imageError = findViewById<ImageView>(R.id.imageViewError)

        personInfo?.let { personInfo ->
            textViewFirstName.text = personInfo.firstName
            textViewlastName.text = personInfo.lastName
            textViewRequest.text = personInfo.personnr
            textViewEmail.text = personInfo.email
            textViewPhone.text = personInfo.phone
            textViewModel.text = personInfo.typ
            textViewLocation.text = personInfo.location
            textViewSelected.text = personInfo.selected


        }

        personInfo?.image?.let { imageUrl ->
            println("ImageUrl: ${imageUrl}")
            val storage = FirebaseStorage.getInstance()

            // val storageReference = Firebase.storage.reference

            // Create a storage reference from our app
            val storageRef = storage.reference

            val ONE_MEGABYTE: Long = 1024 * 1024
            // Create a reference with an initial file path and name
            try {
                val pathReference = storageRef.child("images/${imageUrl}.jpg")
                try {
                    pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                        println("Succcesssssss")
                        // Data for "images/island.jpg" is returned, use this as needed
                        val options = BitmapFactory.Options()
                        //Convert bytearray to bitmap
                        var bitmap = BitmapFactory.decodeByteArray(
                            it, 0, it.size, options
                        )

                        imageError.setImageBitmap(bitmap)

                    }.addOnFailureListener { error ->
                        taskId
                        // Handle any errors
                        println("errorrrr ${error.localizedMessage}")
                    }
                } catch (e: Exception) {
                    println(e.localizedMessage)
                }
            } catch (e: Exception) {
                println("Get image error : ${e.localizedMessage}")
            }

        }

    }


}
