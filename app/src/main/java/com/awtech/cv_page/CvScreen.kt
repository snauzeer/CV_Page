package com.awtech.cv_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun Personinfo(viewmodel: ViewModel) {

    val listpersons by viewmodel.personlistview.collectAsState()
    Column {
        Text("hello")

        Button(onClick = {viewmodel.addperson("ahmad", "123", "wello.com")}) {

            Text("Add")

        }

        Button(onClick = {viewmodel.load()}) { Text("load")}

        LazyColumn {
            items(listpersons) {
                item -> Text(item.name)
            }
        }
    }
}