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

class ViewModel: ViewModel() {

    private val _personlistview = MutableStateFlow(listOf<Person>())
            val personlistview: StateFlow<List<Person>> = _personlistview.asStateFlow()

    lateinit var Cvapp : AppDatabase

    fun addperson(name: String, password: String, email: String) {
        val person = Person(email= email, name = name, password = password)
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            Cvapp.personDao().insertAll(person)
        }
    }

    @OptIn(UnstableApi::class)
    fun load() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val personlist = Cvapp.personDao().getAll()
            _personlistview.value = personlist

        }
    }


}