package com.example.week4_lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week4_lab.data.DataSource
import com.example.week4_lab.data.dummy_data
import com.example.week4_lab.ui.theme.Week4_LabTheme
import com.example.week4_lab.ui.views.MainView
import com.example.week4_lab.ui.views.Soal1
import com.example.week4_lab.ui.views.Soal2
import com.example.week4_lab.ui.views.Soal3
import com.example.week4_lab.ui.views.Soal4

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week4_LabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable("home") {
                                MainView(navController = navController)
                            }
                            composable("soal1") {
                                Soal1(dummy_data().get_data_line())
                            }
                            composable("soal2") {
                                Soal2(listCategory = dummy_data().get_data_tokopedia_category(), listProduct = dummy_data().get_data_tokopedia_product())
                            }
                            composable("soal3") {
                                Soal3(DataSource().loadFeed())
                            }
                            composable("soal4") {
                                Soal4()
                            }
                        }
                    }
                }
            }
        }
    }
}
