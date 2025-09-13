package com.example.userbase.utils

import androidx.compose.runtime.Composable
import com.example.userbase.feature.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun AppNavigation() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}