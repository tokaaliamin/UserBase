package com.example.userbase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.userbase.feature.usersList.presentation.models.Gender
import com.example.userbase.feature.usersList.presentation.models.UserUIModel
import com.example.userbase.feature.usersList.presentation.screens.UsersListScreen
import com.example.userbase.feature.usersList.presentation.screens.components.AddUserButton
import com.example.userbase.ui.theme.UserBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val users = listOf(
            UserUIModel(
                1, "Jane Doe", age = 29, jobTitle = "Teacher",
                gender = Gender.Female
            ), UserUIModel(
                1, "John Doe", age = 25, jobTitle = "Software Engineer",
                gender = Gender.Male
            ), UserUIModel(
                1, "John Doe", age = 20, jobTitle = "Software Engineer",
                gender = Gender.Male
            ), UserUIModel(
                1, "John Doe", age = 20, jobTitle = "Software Engineer",
                gender = Gender.Male
            )
        )
        enableEdgeToEdge()
        setContent {
            UserBaseTheme {
                Scaffold(
                    floatingActionButton = { AddUserButton({}) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    UsersListScreen(users, innerPadding)
                }
            }
        }
    }
}
