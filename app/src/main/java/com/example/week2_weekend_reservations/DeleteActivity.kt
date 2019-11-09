package com.example.week2_weekend_reservations


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import com.example.week2_weekend_reservations.MainActivity.Companion.VALUE_KEY
import com.example.week2_weekend_reservations.MainActivity.Companion.editor
import com.example.week2_weekend_reservations.MainActivity.Companion.numberOfGuests
import com.example.week2_weekend_reservations.MainActivity.Companion.sharedPreferences


class DeleteActivity : AppCompatActivity() {


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


        adapter = ArrayAdapter(this, R.layout.list_item_layout, R.id.guest_info_textView, names)
        usersName.setAdapter(adapter)

        if (numberOfGuests > 0) {

            for (i in 0..numberOfGuests - 1) {

                Log.d("key", VALUE_KEY + i)
                var guest: String? = sharedPreferences.getString(VALUE_KEY + i.toString(), "")

                names.add(guest + "\n")


            }
        }

        Log.d("Number", "" + numberOfGuests)
        delete.setOnClickListener { _ ->

            editor.remove(VALUE_KEY + (numberOfGuests - 1)).apply()
            names.removeAt(numberOfGuests - 1)
            adapter.remove(VALUE_KEY + (numberOfGuests - 1))
            //adapter.remove(adapter.getItem(adapter.count-1))
            numberOfGuests--

        }

        adapter.notifyDataSetChanged()


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
