package com.example.week2_weekend_reservations

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.week2_weekend_reservations.MainActivity.Companion.editor
import com.example.week2_weekend_reservations.MainActivity.Companion.numberOfGuests
import com.example.week2_weekend_reservations.MainActivity.Companion.sharedPreferences
import com.example.week2_weekend_reservations.MainActivity.Companion.guestspot
import kotlinx.android.synthetic.main.activity_display.*
import kotlin.text.StringBuilder

class DisplayActivity : AppCompatActivity() {


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


        Log.d("Dumber","" + numberOfGuests)

        displayUsers()

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

       displayUsers()
    }


    override fun onStop() {
        super.onStop()
        sharedPreferences.getString(VALUE_KEY + (numberOfGuests), guestspot)
        sharedPreferences.getInt(guest_key, numberOfGuests)
    }

    fun displayUsers(){
        if (numberOfGuests > 0)
        {
            var myGuests = StringBuilder()

            for(i in 0..numberOfGuests)
            {

                var guest: String? = sharedPreferences.getString(VALUE_KEY + i, "")

                if(guest != null) {
                    myGuests.append(guest + "\n")
                }


            }
            users_textView.setText(myGuests)
        }
        else {
            users_textView.setText("No guests residing!")
        }
    }

}
