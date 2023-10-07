package com.example.week4_lab.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainView(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "Week 4 Lab")
        Buttons(navController = navController, title = "soal1", pageTitle = "Soal 1 - Chat")
        Buttons(navController = navController, title = "soal2", pageTitle = "Soal 2 - Tokopedia")
        Buttons(navController = navController, title = "soal3", pageTitle = "Soal 3 - Instagram Home")
        Buttons(navController = navController, title = "soal4", pageTitle = "Soal 4 - Instagram Explore")
    }
}

@Composable
fun Buttons(
    navController: NavController,
    title: String,
    pageTitle: String
){

    Button(
        onClick = {
            navController.navigate(title)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = pageTitle,
            color = Color.White

        )
    }
}
