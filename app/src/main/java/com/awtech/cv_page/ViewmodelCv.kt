package com.awtech.cv_page

import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel: ViewModel() {

    private val _personlistview = MutableStateFlow(listOf<Person>())
            val personlistview: StateFlow<List<Person>> = _personlistview.asStateFlow()

    var editperson = Person(email = "", name = "", password = "", lastname = "",
        phonenumber = "", Webpage = "", LinkedIn = "", Github = "", qrCode = "")

    lateinit var Cvapp : AppDatabase

    init {
        load()
    }

    fun addperson(name: String, lastname: String="", phonenumber: String="",
                      password: String, email: String, Webpage: String="",
                  LinkedIn: String="", Github: String="") {
            val vCard = """BEGIN:VCARD
        VERSION:3.0
        N:$name; $lastname
        TEL:$phonenumber
        EMAIL:$email
        URL:$Webpage
        URL:$LinkedIn
        URL:$Github
        
        END:VCARD
    """.trimIndent()


            val qrCode = QrCodeGenerator.generateQRCode(vCard)
            val person = Person(
                email = email,
                name = name,
                lastname = lastname,
                phonenumber = phonenumber,
                password = password,
                Webpage = Webpage,
                LinkedIn = LinkedIn,
                Github = Github,
                qrCode = qrCode
            )

            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                Cvapp.personDao().insertAll(person)
                load()
            }
        }


    fun load() {
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val personlist = Cvapp.personDao().getAll()
                _personlistview.value = personlist

            }
        }

    fun delete(person: Person) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            Cvapp.personDao().delete(person)
            load()
        }

    }
    fun update(person: Person, name: String="",
               phonenumber: String="", email: String="",
               Webpage: String="", LinkedIn: String="", Github: String="") {
        person.name = name
        person.phonenumber = phonenumber
        person.email = email
        person.Webpage = Webpage
        person.LinkedIn = LinkedIn
        person.Github = Github
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            Cvapp.personDao().update(person)
            load()

        }

    }

    suspend fun getPerson(email: String): Person? {
        return withContext(Dispatchers.IO) {
            Cvapp.personDao().getPersonByEmail(email)
        }
    }

}

