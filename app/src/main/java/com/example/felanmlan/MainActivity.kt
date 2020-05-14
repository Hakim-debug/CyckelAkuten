package com.example.felanmlan

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener{


    var selected =""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)




        val photo = findViewById<ImageView>(R.id.iv_cam)


        btn_cam.setOnClickListener{
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,123)

        }


        check_chain.setOnClickListener(this)
        check_puncture.setOnClickListener(this)
        check_spokes.setOnClickListener(this)






        //Fortsätt knappen
        val button = findViewById<Button>(R.id.write_result)
        println("in")

        button.setOnClickListener{
            val intent = Intent(this, PersonalDataActivity::class.java)
            println("Hakim2")
           /* photo.buildDrawingCache()
            val bitmap: Bitmap = photo.getDrawingCache()
            println("Hakim4")*/

            intent.putExtra("selected", selected)
            intent.putExtra("image",bitmap)

            */
            startActivity(intent)
            println("Hakim5")




    }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123){
            var bmp=data?.extras?.get("data") as Bitmap
            iv_cam.setImageBitmap(bmp)
            println("Hakim6")

        }


    }

    override fun onClick(pO: View?) {
        pO as CheckBox
        var isChecked: Boolean = pO.isChecked
        when(pO.id){
            R.id.check_puncture ->
                if (isChecked){
                textView4.text = "You've chosen Puncture"
                    check_chain.isChecked = false
                    R.id.bottom
                    selected = check_puncture.text.toString()


            }else{
                    selected = ""
                }
            R.id.check_chain-> if (isChecked){
                textView4.text = " \n" + "You have chosen Chain"
                check_puncture.isChecked = false
                R.id.bottom

                selected = check_chain.text.toString()


            }else{
                selected = ""
            }

            R.id.check_spokes-> if (isChecked){
                textView4.text = "\n" + "You have chosen Spokes"
                check_chain.isChecked = false
                check_puncture.isChecked = false
                R.id.bottom
                selected = check_spokes.text.toString()


            }else{
                selected = ""
            }

    }
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bike-> {
                    // Respond to navigation item 1 click
                    true
                }

                R.id.build-> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }

    }

}




