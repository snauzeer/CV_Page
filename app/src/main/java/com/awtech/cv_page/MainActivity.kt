package com.awtech.cv_page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.awtech.cv_page.ui.theme.CV_PageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val viewmodel = ViewModel()
        viewmodel.Cvapp = db

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CV_PageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PageNav(viewmodel)

                }
            }
        }
    }
}

