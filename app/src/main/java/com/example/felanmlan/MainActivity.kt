package com.example.felanmlan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.CheckBox

class MainActivity : AppCompatActivity(),View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Fortsätt knappen
        val button = findViewById<Button>(R.id.write_result)
        button.setOnClickListener{
            val intent = Intent(this, PersonalDataActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onClick(pO: View?) {
        pO as CheckBox
        var isChecked: Boolean = pO.isChecked
        when(pO.id){
            R.id.check_puncture ->if (isChecked){
                R.id.bottom

            }

        }
    }

}
