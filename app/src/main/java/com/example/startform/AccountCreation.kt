package com.example.startform

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.util.ArrayList


class AccountCreation : AppCompatActivity() {

    companion object {

        const val PERSONAL_INFORMATION = "PersonalInformation"

    }

    private fun checkPasswordFirstPoint(pass : String) {

        val rgx = "[A-Z]+"

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_layout)

        val titleBanner = findViewById<TextView>(R.id.title_second_form)

        val returnButton = findViewById<Button>(R.id.button_return)

        val personalInformation = intent.extras?.getStringArrayList(PERSONAL_INFORMATION) as ArrayList<String>
        personalInformation.toMutableList()

        titleBanner.text = getString(R.string.title_second_form, personalInformation[0])

        Log.d("MAIN", personalInformation.toString())

        returnButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }

}