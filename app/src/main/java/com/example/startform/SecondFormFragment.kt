package com.example.startform

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import com.example.startform.databinding.FragmentSecondFormBinding
import java.util.regex.Pattern


class SecondFormFragment : Fragment() {

    companion object {

        const val PERSONAL_INFORMATION = "userPersonalInformation"

    }

    private var _binding : FragmentSecondFormBinding? = null
    private val binding get() = _binding!!

    private lateinit var userPersonalInformation : Array<String>


    //Email Checker
    private fun checkEmail(email : String) : Boolean {

        val emailAddressPattern = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        return emailAddressPattern.matcher(email).matches()

    }

    private fun String.isLongEnough() = length >= 8
    private fun String.hasEnoughDigits() = count(Char::isDigit) > 0
    private fun String.hasLowerCase() = any(Char::isLowerCase)
    private fun String.hasUpperCase() = any(Char::isUpperCase)
    private fun String.hasSpecialChar() = any{ it in "!#$%&/()=@_-" }

    private fun String.hasWhiteSpace() = !any { it in " " }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userPersonalInformation = it.getStringArray(PERSONAL_INFORMATION) as Array<String>
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondFormBinding.inflate(inflater, container, false)
        val view = binding.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* TITLE */

        val titleBanner     = view.findViewById<TextView>(R.id.title_second_form)

        /* USER ACCOUNT DATA */

        val userName        = view.findViewById<EditText>(R.id.field_username_edit)
        val email           = view.findViewById<EditText>(R.id.field_email_edit)
        val password        = view.findViewById<EditText>(R.id.field_password_edit)

        /* PRIVACY CHECKS */

        val privacyAdvise   = view.findViewById<CheckBox>(R.id.privacy_checkbox)
        val termsAdvise     = view.findViewById<CheckBox>(R.id.terms_checkbox)

        /* CREATE ACCOUNT BUTTON */

        val createButton    = view.findViewById<Button>(R.id.button_create_account)
        createButton.isEnabled = false


        /* Modify Title with user´s name */
        userPersonalInformation.toMutableList()
        titleBanner.text = getString(R.string.title_second_form, userPersonalInformation[0])

        /* USER ACCOUNT DATA VALIDATION TO CONTINUE */

        var validUserName   = false
        var validMail       = false
        var validPassword   = false

        /* DATA LISTENERS */

        userName.addTextChangedListener {

            val userNameText = userName.text.toString()

            if(!userNameText.hasWhiteSpace()) {
                userName.error = getString(R.string.username_error)
            }

            validUserName = userNameText.isNotEmpty() && userNameText.hasWhiteSpace()

            createButton.isEnabled = validUserName && validMail && validPassword && privacyAdvise.isChecked && termsAdvise.isChecked

        }

        email.addTextChangedListener {

            val emailText = email.text.toString()
            var valid = false

            if (checkEmail(emailText)) {
                valid = true
            } else {
                email.error = getString(R.string.mail_error)
                valid = false
            }

            validMail = emailText.isNotEmpty() && valid

            createButton.isEnabled = validUserName && validMail && validPassword && privacyAdvise.isChecked && termsAdvise.isChecked

        }

        password.addTextChangedListener {

            val passwordText = password.text.toString()

            val enoughLengthLabel = view.findViewById<TextView>(R.id.password_description_title_1)
            var hasAnEnoughLength = false

            val lowerCaseLabel = view.findViewById<TextView>(R.id.password_description_title_2)
            var hasAnLowerCase = false

            val upperCaseLabel = view.findViewById<TextView>(R.id.password_description_title_3)
            var hasAnUpperCase = false

            val specialCharLabel = view.findViewById<TextView>(R.id.password_description_title_4)
            var hasAnSpecialChar = false

            val digitsLabel = view.findViewById<TextView>(R.id.password_description_title_5)
            var hasAnDigit = false

            //Check if the password is long enough
            if (passwordText.isLongEnough()) {
                enoughLengthLabel.setTextColor(Color.parseColor("#30A706"))
                hasAnEnoughLength = true
            } else {
                enoughLengthLabel.setTextColor(Color.parseColor("#D50000"))
                hasAnEnoughLength = false
            }

            //Check if the password has an lower case
            if (passwordText.hasLowerCase()) {
                //TRUE
                lowerCaseLabel.setTextColor(Color.parseColor("#30A706"))
                hasAnLowerCase = true
            } else {
                //FALSE
                lowerCaseLabel.setTextColor(Color.parseColor("#D50000"))
                hasAnLowerCase = false
            }

            //Check if the password has an upper case
            if (passwordText.hasUpperCase()) {
                upperCaseLabel.setTextColor(Color.parseColor("#30A706"))
                hasAnUpperCase = true
            } else {
                upperCaseLabel.setTextColor(Color.parseColor("#D50000"))
                hasAnUpperCase = false
            }

            //Check if the password has an especial character
            if (passwordText.hasSpecialChar()) {
                specialCharLabel.setTextColor(Color.parseColor("#30A706"))
                hasAnSpecialChar = true
            } else {
                specialCharLabel.setTextColor(Color.parseColor("#D50000"))
                hasAnSpecialChar = false
            }

            //Check if the password has at least a digit
            if (passwordText.hasEnoughDigits()) {
                digitsLabel.setTextColor(Color.parseColor("#30A706"))
                hasAnDigit = true
            } else {
                digitsLabel.setTextColor(Color.parseColor("#D50000"))
                hasAnDigit = false
            }


            validPassword = passwordText.isNotEmpty() && hasAnEnoughLength && hasAnLowerCase && hasAnUpperCase && hasAnSpecialChar && hasAnDigit

            createButton.isEnabled = validUserName && validMail && validPassword && privacyAdvise.isChecked && termsAdvise.isChecked

        }

        privacyAdvise.setOnCheckedChangeListener { _, _ ->

            createButton.isEnabled = validUserName && validMail && validPassword && privacyAdvise.isChecked && termsAdvise.isChecked

        }

        termsAdvise.setOnCheckedChangeListener { _, _ ->

            createButton.isEnabled = validUserName && validMail && validPassword && privacyAdvise.isChecked && termsAdvise.isChecked

        }

        createButton.setOnClickListener {

            val action = SecondFormFragmentDirections.actionSecondFormFragmentToAccountSuccessCreation()
            view.findNavController().navigate(action)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}