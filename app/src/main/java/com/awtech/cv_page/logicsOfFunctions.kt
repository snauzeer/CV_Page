package com.awtech.cv_page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


fun getErrorMessage(value: String): String {

    if (value == "emailexist") {
        return "this email exist"
    }
    if(value == "email") {
        return "the email can not be empty"
    }
    if(value == "password") {
        return "the password can not be empty"
    }
    if(value == "name") {
        return "the name can not be empty"
    }
    return ""

}

data class CreateAccountResult (
    val emailexist: String,
    val passwordexist: String,
    val nameexist: String,
    val emailcheck: Boolean,
)


fun createaccountlogic(email: String, password: String,
                       name: String ,listpersons: List<Person>): CreateAccountResult {

    var emailexist = ""
    var passwordexist = ""
    var nameexist = ""
    var emailcheck = false

    if(email.isBlank()) {
        emailexist = getErrorMessage("email")
    }

    for (item in listpersons) {
        if (item.email == email) {
            emailcheck = true
        }
    }
    if (emailcheck) {
        emailexist = getErrorMessage("emailexist")
    }


    if(password.isBlank()) {
        passwordexist = getErrorMessage("password")
    }

    if(name.isBlank()) {
        nameexist = getErrorMessage("name")
    }
    return CreateAccountResult (
        emailexist = emailexist,
        passwordexist = passwordexist,
        nameexist = nameexist,
        emailcheck = emailcheck,
    )
}