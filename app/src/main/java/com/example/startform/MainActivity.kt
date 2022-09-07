package com.example.startform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private fun checkValidDates (item : String, dateType : Int) : Boolean {
        val dayRange : IntRange = 1..31
        val montRange : IntRange = 1..12
        val yearRange : IntRange = 1902..2023

        val itemInt : Int = item.toInt()

        return when(dateType) {
            1 -> dayRange.contains(itemInt)
            2 -> montRange.contains(itemInt)
            else -> yearRange.contains(itemInt)

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dayField = findViewById<EditText>(R.id.field_birth_date_day_edit)
        val monthField = findViewById<EditText>(R.id.field_birth_date_month_edit)
        val yearField = findViewById<EditText>(R.id.field_birth_date_year_edit)

        dayField.setOnFocusChangeListener { _, _ ->

            val day = dayField.text.toString()

            if (day.isNotEmpty()){
                if(!checkValidDates(day, 1)) {
                    dayField.error = "Dia Invalido"
                }
            }

        }

        monthField.setOnFocusChangeListener {_, _ ->

            val month = monthField.text.toString()

            if (month.isNotEmpty()) {
                if (!checkValidDates(month, 2)) {
                    monthField.error = "Mes Invalido"
                }
            }

        }

        yearField.setOnFocusChangeListener { _, _ ->

            val year = yearField.text.toString()

            if(year.isNotEmpty()) {
                if (!checkValidDates(year, 3)) {
                    yearField.error = "Año Invalido"
                }
            }

        }


    }
}


