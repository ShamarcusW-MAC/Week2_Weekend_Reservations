package com.example.week2_weekend_reservations

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    lateinit var editor: SharedPreferences.Editor

//    companion object{
    val EXTRA_MESSAGE = "com.example.week2_weekend_reservations.extra.MESSAGE"
//    }

    //private var name = R.id.name_edittext
    //private var price = R.id.price_edittext
    private var guest_key: String? = "guests_in"
    private var VALUE_KEY = "Guest_"
    companion object {
        var numberOfGuests = 0
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
        sharedPreferences.getInt(guest_key, 0)

        submit_button.setOnClickListener {_->

            intent = Intent(this, DisplayActivity::class.java)

            intent.putExtra("Name", name_edittext.text.toString())
            Log.d("Check", name_edittext.text.toString())
            intent.putExtra("Price", price_edittext.text.toString())
            Log.d("Money", price_edittext.text.toString())
            startActivity(intent)
            var guestspot = name.text.toString() + "\t$" + price.text.toString()
            Log.d("Full", guestspot)
            editor.putString(VALUE_KEY + (numberOfGuests), (guestspot))

//            intent.putExtra(guest_key, name.text.toString())
//            intent.putExtra(VALUE_KEY, price.text.toString())
//            intent.putExtra(guest_key, users_textView.text.toString())

            if(name_edittext.text.toString() == ""  ||
                number_edittext.text.toString() == ""   ||
                date_edittext.text.toString() == "" ||
                price_edittext.text.toString() == "")
            {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_LONG).show()
            }
            else
            {
                editor.putString(guest_key + (numberOfGuests), ("" + name_textview.text + " " + name.text +
                        " " + price_textview.text + " " + price.text))
                editor.putInt((guest_key), (numberOfGuests + 1)).commit()
                numberOfGuests++

                name_edittext.setText("")
                number_edittext.setText("")
                date_edittext.setText("")
                price_edittext.setText("")

                intent.putExtra(EXTRA_MESSAGE, guestspot)
                Toast.makeText(this, "Done!", Toast.LENGTH_LONG).show()
            }
//            startActivity(intent)
        }
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
//        if(numberOfGuests > 0)
//        {
//
//            var myUsers = StringBuilder()
//            for(i in 0..numberOfGuests)
//            {
//                var guest: String? = sharedPreferences.getString(VALUE_KEY + i, "")
//                myUsers.append(guest + "\n")
//            }
//            users_textView.setText(myUsers)
//        }
//        else {
//            users_textView.setText("No guests residing!")
//        }
    }


    override fun onStop() {
        super.onStop()
        name_edittext.setText("")
        number_edittext.setText("")
        date_edittext.setText("")
        price_edittext.setText("")
    }
}
