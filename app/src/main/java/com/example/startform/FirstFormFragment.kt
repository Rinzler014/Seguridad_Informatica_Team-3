package com.example.startform

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.startform.databinding.FragmentFirstFormBinding
import java.util.ArrayList


class FirstFormFragment : Fragment() {

    private var _binding : FragmentFirstFormBinding? = null
    private val binding  get() = _binding!!

    //External Function to validate Dates
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

    //View Created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //Layout Inflated
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstFormBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


    //View Inflated and Displayed
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* USER PERSONAL DATA */
        val userPersonalNameField       = view.findViewById<EditText>(R.id.field_name_edit)
        val userLastNameField           = view.findViewById<EditText>(R.id.field_last_name_edit)
        val userMotherLastNameField     = view.findViewById<EditText>(R.id.field_mother_last_name_edit)

        /* USER BIRTH DATE DATA */
        val dayField                    = view.findViewById<EditText>(R.id.field_birth_date_day_edit)
        val monthField                  = view.findViewById<EditText>(R.id.field_birth_date_month_edit)
        val yearField                   = view.findViewById<EditText>(R.id.field_birth_date_year_edit)

        /* CONTINUE BUTTON REFERENCE */
        val continueButton              = view.findViewById<Button>(R.id.button_continue)
        continueButton.isEnabled       = false

        /* USER PERSONAL DATA VALIDATION TO CONTINUE */
        var validName                   = false
        var validLastName               = false
        var validMomLastName            = false

        var validDate                   = false
        var validMonth                  = false
        var validYear                   = false

        /* USER PERSONAL DATA LISTENERS */
        userPersonalNameField.addTextChangedListener {

            val name = userPersonalNameField.text.toString()

            validName = name.isNotEmpty()

            continueButton.isEnabled = validName && validLastName && validMomLastName && validDate && validMonth && validYear

        }

        userLastNameField.addTextChangedListener {

            val lastName = userLastNameField.text.toString()

            validLastName = lastName.isNotEmpty()

            continueButton.isEnabled = validName && validLastName && validMomLastName && validDate && validMonth && validYear

        }

        userMotherLastNameField.addTextChangedListener {

            val momLastName = userMotherLastNameField.text.toString()

            validMomLastName = momLastName.isNotEmpty()

            continueButton.isEnabled = validName && validLastName && validMomLastName && validDate && validMonth && validYear

        }


        /* USER BIRTH DATE DATA LISTENERS */
        dayField.addTextChangedListener {

            val day = dayField.text.toString()

            if (day.isNotEmpty()){
                if(!checkValidDates(day, 1)) {
                    dayField.error = getString(R.string.day_error)
                    validDate = false
                } else {
                    validDate = true
                }
            } else {
                validDate = false
            }

            continueButton.isEnabled = validName && validLastName && validMomLastName && validDate && validMonth && validYear

        }

        monthField.addTextChangedListener {

            val month = monthField.text.toString()

            if (month.isNotEmpty()) {
                if (!checkValidDates(month, 2)) {
                    monthField.error = getString(R.string.month_error)
                    validMonth = false
                } else {
                    validMonth = true
                }
            } else {
                validMonth = false
            }

            continueButton.isEnabled = validName && validLastName && validMomLastName && validDate && validMonth && validYear

        }

        yearField.addTextChangedListener {

            val year = yearField.text.toString()

            if(year.isNotEmpty()) {
                if (!checkValidDates(year, 3)) {
                    yearField.error = getString(R.string.year_error)
                    validYear = false
                } else {
                    validYear = true
                }
            } else {
                validYear = false
            }

            continueButton.isEnabled = validName && validLastName && validMomLastName && validDate && validMonth && validYear


        }


        /* CONTINUE BUTTON LISTENER */
        continueButton.setOnClickListener {

            val genreOption = when(view.findViewById<RadioGroup>(R.id.buttons_gender).checkedRadioButtonId) {
                R.id.option_male -> getString(R.string.male_label)
                R.id.option_female -> getString(R.string.female_label)
                else -> "0"
            }

            val userPersonalInformation : Array<String> = arrayOf(
                userPersonalNameField.text.toString(),
                userLastNameField.text.toString(),
                userMotherLastNameField.text.toString(),
                dayField.text.toString(),
                monthField.text.toString(),
                yearField.text.toString(),
                genreOption)


            //NAVIGATE TO SECOND FORM WITH ARGUMENTS
            val action = FirstFormFragmentDirections.actionFirstFormFragmentToSecondFormFragment(userPersonalInformation = userPersonalInformation)
            view.findNavController().navigate(action)

            /*

            val intent = Intent(this, AccountCreation::class.java)
            intent.putExtra(AccountCreation.PERSONAL_INFORMATION, userPersonalInformation)
            startActivity(intent)

            */

        }

    }

    //View Destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}