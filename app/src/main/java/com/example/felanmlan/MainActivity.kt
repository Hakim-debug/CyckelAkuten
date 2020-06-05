package com.example.felanmlan

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.felanmlan.R.id.build
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var selected = ""
    lateinit var imageUrl: String
    lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        supportActionBar?.title = "Report Error"


        bottomNavigationView = findViewById(R.id.bottom_navigation)




        btn_cam.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 123)

        }


        check_chain.setOnClickListener(this)
        check_puncture.setOnClickListener(this)
        check_spokes.setOnClickListener(this)


        //Contineu Buttom
        val button = findViewById<Button>(R.id.write_result)
        println("in")

        button.setOnClickListener {
            val intent = Intent(this, PersonalDataActivity::class.java)
            println("Hakim2")


            intent.putExtra("selected", selected)
            intent.putExtra("image",bitmap)

            */
            startActivity(intent)
            println("Hakim5")


        }

        //BottomNavigationView Action

        val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            println("itemId!!: ${item.itemId}")
            println("bike!!: ${R.id.bike}")
            when (item.itemId) {
                R.id.page_1 -> {


                    val intent = Intent(this, PersonalDataActivity::class.java)

                    startActivity(intent)
                    println("Next")

                    true
                }

                R.id.page_2 -> {

                    val intent = Intent(this, WorkShopActivity::class.java)

                    startActivity(intent)

                    println("Next 2")


                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_3 -> {

                    val intent = Intent(this, MapsActivity::class.java)

                    startActivity(intent)

                    println("NextHome")

                    true
                }

                R.id.page_4 -> {

                    val intent = Intent(this, MainActivity::class.java)

                    startActivity(intent)

                    println("NextHome")

                    true
                }

                else -> false
            }
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

    }


    //Displays the image in Firebase storage
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            imageUrl = UUID.randomUUID().toString()

            var bmp = data?.extras?.get("data") as Bitmap
            iv_cam.setImageBitmap(bmp)
            println("Hakim6")
            iv_cam.isDrawingCacheEnabled = true
            iv_cam.buildDrawingCache()
            val bitmap = (iv_cam.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            //Storage
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("images/$imageUrl.jpg")

            var uploadTask = imageRef.putBytes(data)
            uploadTask.addOnFailureListener {
                println("!!! fail" + it.localizedMessage)
            }.addOnSuccessListener {
                println("!!! success")
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
            }

        }

    }


    //Check Box
    override fun onClick(pO: View?) {
        pO as CheckBox
        var isChecked: Boolean = pO.isChecked
        when (pO.id) {
            R.id.check_puncture ->
                if (isChecked) {
                    textView4.text = "You've chosen Puncture"
                    check_chain.isChecked = false
                    R.id.bottom
                    selected = check_puncture.text.toString()


                } else {
                    selected = ""
                }
            R.id.check_chain -> if (isChecked) {
                textView4.text = " \n" + "You have chosen Chain"
                check_puncture.isChecked = false
                R.id.bottom

                selected = check_chain.text.toString()


            } else {
                selected = ""
            }

            R.id.check_spokes -> if (isChecked) {
                textView4.text = "\n" + "You have chosen Spokes"
                check_chain.isChecked = false
                check_puncture.isChecked = false
                R.id.bottom
                selected = check_spokes.text.toString()


            } else {
                selected = ""
            }

        }

    }

}




