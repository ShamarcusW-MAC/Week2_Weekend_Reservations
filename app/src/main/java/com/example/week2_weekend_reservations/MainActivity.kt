package com.example.week2_weekend_reservations

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var nData: Int = 0

   companion object{
       lateinit var sharedPreferences: SharedPreferences
       lateinit var editor: SharedPreferences.Editor
       var numberOfGuests = 0
       var guestspot: String? = null
         var guest_key: String? = "guests_in"
        var VALUE_KEY = "Guest_"
   }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent: Intent
        var name: EditText
        var price: EditText
        sharedPreferences = this.getSharedPreferences("com.example.week2_weekend_reservations", Context.MODE_PRIVATE)
        name = name_edittext
        price = price_edittext

        editor = sharedPreferences.edit()


        submit_button.setOnClickListener {_->

            intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("Name", name_edittext.text.toString())
            intent.putExtra("Price", price_edittext.text.toString())

            guestspot = name.text.toString() + "  -  $" + price.text.toString()


            if(name_edittext.text.toString() == ""  ||
                number_edittext.text.toString() == ""   ||
                date_edittext.text.toString() == "" ||
                price_edittext.text.toString() == "")
            {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_LONG).show()
            }
            else
            {
                editor.putString(VALUE_KEY + numberOfGuests, (guestspot))
                editor.putInt(guest_key, numberOfGuests).commit()
                numberOfGuests++

                name_edittext.setText("")
                number_edittext.setText("")
                date_edittext.setText("")
                price_edittext.setText("")

                Toast.makeText(this, "Done!", Toast.LENGTH_LONG).show()
            }
        }
        nData = sharedPreferences.getInt(guest_key, numberOfGuests)
        Log.d("create", "" + numberOfGuests)
        sharedPreferences.getString(VALUE_KEY + (numberOfGuests), guestspot)
        display_button.setOnClickListener {

            val intent2 = Intent(this, DisplayActivity::class.java)
            startActivityForResult(intent2, 1)

        }
    }

    override fun onResume() {
        super.onResume()

        name_edittext.setText("")
        number_edittext.setText("")
        date_edittext.setText("")
        price_edittext.setText("")
        Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show()

    }

    override fun onPause() {
        super.onPause()
        name_edittext.setText("")
        number_edittext.setText("")
        date_edittext.setText("")
        price_edittext.setText("")
        Log.d("stay", "" + numberOfGuests)

        var ed: SharedPreferences.Editor = sharedPreferences.edit()
        ed.putInt(guest_key, nData).commit()
        ed.putString(VALUE_KEY + (numberOfGuests), guestspot)

    }
}
