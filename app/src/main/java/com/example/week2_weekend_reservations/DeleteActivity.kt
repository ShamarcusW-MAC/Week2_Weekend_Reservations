package com.example.week2_weekend_reservations

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*


class DeleteActivity : AppCompatActivity() {


    lateinit var sharedPreferences: SharedPreferences

    private var guest_key: String? = MainActivity.guest_key
    private var VALUE_KEY = MainActivity.VALUE_KEY

    lateinit var editor: SharedPreferences.Editor
    lateinit var search: EditText
    lateinit var usersName: ListView
    lateinit var delete: Button
    lateinit var searchName: Button


    lateinit var adapter: ArrayAdapter<String>
    private var names: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        search = findViewById(R.id.search_user_edittext)
        usersName = findViewById(R.id.usersNames_listview)

        delete = findViewById(R.id.delete_button)

        searchName = findViewById(R.id.search_button)


        var intent = getIntent()
        var name = intent.getStringExtra("Name2")
        var price = intent.getStringExtra("Price2")

        sharedPreferences = this.getSharedPreferences("com.example.week2_weekend_reservations", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        sharedPreferences.getInt(guest_key, 0)
        editor.putString(VALUE_KEY + (MainActivity.numberOfGuests), ("" + name + "\t$" + price)).commit()
        editor.putInt(guest_key, (MainActivity.numberOfGuests +1)).commit()

        if (MainActivity.numberOfGuests > 0) {

            for (i in 0..MainActivity.numberOfGuests) {
                var guest: String? = sharedPreferences.getString(VALUE_KEY + i, "FAILED")

                names.add(guest + "\n")


            }
        }


        searchName.setOnClickListener { _ ->
        }


        adapter = ArrayAdapter(this, R.layout.list_item_layout, R.id.guest_info_textView, names)
        usersName.setAdapter(adapter)
         search.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                this@DeleteActivity.adapter.getFilter().filter(charSequence)

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })


    }
}
