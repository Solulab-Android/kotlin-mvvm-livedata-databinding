package com.solulab.example.view.activities.login

import android.util.Patterns


class LoginUser(private val strEmailAddress: String?, private val strPassword: String?) {

    var email=strEmailAddress
    var password=strPassword

    fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordLengthGreaterThan5(): Boolean {
        return password!!.length > 5
    }

}