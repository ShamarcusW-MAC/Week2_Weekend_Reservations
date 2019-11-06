package com.example.week2_weekend_reservations

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.week2_weekend_reservations.MainActivity.Companion.numberOfGuests
import kotlinx.android.synthetic.main.activity_display.*
import kotlin.text.StringBuilder

class DisplayActivity : AppCompatActivity() {


    lateinit var sharedPreferences: SharedPreferences

    lateinit var editor: SharedPreferences.Editor

    lateinit var users: TextView

    private var guest_key: String? = "guests_in"
    private var VALUE_KEY = "Guest_"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        users = findViewById(R.id.users_textView)

        var intent = getIntent()


        var name: String? = intent.extras?.getString("Name")
        Log.d("Result", name.toString())
        var price: String? = intent.extras?.getString("Price")




        sharedPreferences = this.getSharedPreferences("com.example.week2_weekend_reservations", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        sharedPreferences.getInt(guest_key, 0)

        editor.putInt(guest_key, (numberOfGuests+1)).commit()

        if (numberOfGuests > 0)
        {
            var myGuests = StringBuilder()

            for(i in 0..numberOfGuests)
            {
                var guest: String? = sharedPreferences.getString(VALUE_KEY + i, "FAILED")

                myGuests.append(guest + "\n")


            }
            users_textView.setText(myGuests)
        }
        else {
            users_textView.setText("No guests residing!")
        }

        gotodelete_button.setOnClickListener {_->

            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
            intent.putExtra("Name2", name.toString())
            Log.d("Name2", name.toString())
            intent.putExtra("Price2",price.toString())

        }

    }

    override fun onResume() {
        super.onResume()
        if(numberOfGuests > 0)
        {

            var myUsers = StringBuilder()
            for(i in 0..numberOfGuests)
            {
                var guest: String? = sharedPreferences.getString(VALUE_KEY + i, "")
                myUsers.append(guest + "\n")
            }
            users_textView.setText(myUsers)
        }
        else {
            users_textView.setText("No guests residing!")
        }
    }


    override fun onStop() {
        super.onStop()
        sharedPreferences = this.getSharedPreferences("com.example.week2_weekend_reservations", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        sharedPreferences.getInt(guest_key, 0)
        editor.putInt(guest_key, (numberOfGuests+1)).commit()
        if(numberOfGuests > 0)
        {

            var myUsers = StringBuilder()
            for(i in 0..numberOfGuests)
            {
                var guest: String? = sharedPreferences.getString(VALUE_KEY + i, "")
                myUsers.append(guest + "\n")
            }
            users_textView.setText(myUsers)
        }
        else {
            users_textView.setText("No guests residing!")
        }
    }

}
