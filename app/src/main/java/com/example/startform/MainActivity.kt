package com.example.startform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import java.io.Serializable
import java.util.ArrayList

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

        val userPersonalName = findViewById<EditText>(R.id.field_name_edit)
        val userLastName = findViewById<EditText>(R.id.field_last_name_edit)
        val userMotherLastName = findViewById<EditText>(R.id.field_mother_last_name_edit)

        val dayField = findViewById<EditText>(R.id.field_birth_date_day_edit)
        val monthField = findViewById<EditText>(R.id.field_birth_date_month_edit)
        val yearField = findViewById<EditText>(R.id.field_birth_date_year_edit)

        val continueButton = findViewById<Button>(R.id.button_continue)
        continueButton.isEnabled = false

        var aux1 = false
        var aux2 = false
        var aux3 = false


        dayField.addTextChangedListener {

            val day = dayField.text.toString()

            if (day.isNotEmpty()){
                if(!checkValidDates(day, 1)) {
                    dayField.error = getString(R.string.day_error)
                    aux1 = false
                } else {
                    aux1 = true
                }
            } else {
                aux1 = false
            }

            continueButton.isEnabled = aux1 && aux2 && aux3

        }

        monthField.addTextChangedListener {

            val month = monthField.text.toString()

            if (month.isNotEmpty()) {
                if (!checkValidDates(month, 2)) {
                    monthField.error = getString(R.string.month_error)
                    aux2 = false
                } else {
                    aux2 = true
                }
            } else {
                aux2 = false
            }

            continueButton.isEnabled = aux1 && aux2 && aux3

        }

        yearField.addTextChangedListener {

            val year = yearField.text.toString()

            if(year.isNotEmpty()) {
                if (!checkValidDates(year, 3)) {
                    yearField.error = getString(R.string.year_error)
                    aux3 = false
                } else {
                    aux3 = true
                }
            } else {
                aux3 = false
            }

            continueButton.isEnabled = aux1 && aux2 && aux3


        }

        continueButton.setOnClickListener {

            val intent = Intent(this, AccountCreation::class.java)
            val userPersonalInformation : ArrayList<String> = arrayListOf(userPersonalName.text.toString(), userLastName.text.toString(), userMotherLastName.text.toString())

            intent.putExtra(AccountCreation.PERSONAL_INFORMATION, userPersonalInformation)

            startActivity(intent)

        }

    }
}


