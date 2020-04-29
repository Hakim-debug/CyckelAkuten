package com.example.felanmlan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_personal_data.*

class MainActivity : AppCompatActivity(),View.OnClickListener{
    var selected =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        check_chain.setOnClickListener(this)
        check_puncture.setOnClickListener(this)
        check_spokes.setOnClickListener(this)






        //Fortsätt knappen

        val button = findViewById<Button>(R.id.write_result)

       // val checkBox = findViewById<CheckBox>(R.id.check_puncture)
        //val p = textView3.text.toString()

       // val intent = Intent(this, PersonalDataActivity::class.java)
       /// intent.putExtra( "P", p)


        //val name = check_puncture.text.toString()

        button.setOnClickListener{
            val intent = Intent(this, PersonalDataActivity::class.java)
           // val name = check_puncture.text.toString()
           // intent.putExtra( "P", name)
            //textView4.check_puncture.id

            intent.putExtra("selected", selected)
            startActivity(intent)




       // val P = findViewById<CheckBox>(R.id.check_puncture)

    }
    }
    override fun onClick(pO: View?) {
        pO as CheckBox
        var isChecked: Boolean = pO.isChecked
        when(pO.id){
            R.id.check_puncture ->
                if (isChecked){
                textView4.text = "Du har valt Pungtering"
                    check_chain.isChecked = false
                    R.id.bottom
                    selected = check_puncture.text.toString()


            }else{
                    selected = ""
                }
            R.id.check_chain-> if (isChecked){
                textView4.text = "Du har valt Kedja"
                check_puncture.isChecked = false
                R.id.bottom

                selected = check_chain.text.toString()


            }else{
                selected = ""
            }

            R.id.check_spokes-> if (isChecked){
                textView4.text = "Du har valt Ekrrar"
                check_chain.isChecked = false
                check_puncture.isChecked = false
                R.id.bottom
                selected = check_spokes.text.toString()


            }else{
                selected = ""
            }


    }



    }
}



