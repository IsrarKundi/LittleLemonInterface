package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                AppScreen()
            }
        }
    }
}

@Composable
private fun AppScreen() {
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MyNavigation()
        }
    }
}

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.route
    ) {
        composable(Destinations.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Destinations.Menu.route) {
            MenuListScreen(navController)
        }
        composable(
            Destinations.ItemDetails.route + "/{name}" + "/{price}" + "/{description}" + "/{image}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("price"){
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("description"){
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("image"){
                    type = NavType.IntType
                    nullable = false
                }
            )
        ){ entry ->
            entry.arguments?.getInt("image")?.let {
                entry.arguments?.getString("name")?.let { it1 ->
                    ItemDetails(
                        name = it1,
                        price = entry.arguments?.getString("price")!!,
                        description = entry.arguments?.getString("description")!!,
                        image = it
                    )
                }
            }
        }

    }
}