package com.example.serinidadedesons.Navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.serinidadedesons.Screens.DormirScreen
import com.example.serinidadedesons.Screens.MeditarScreen
import com.example.serinidadedesons.Screens.EstudarScreen
import com.example.serinidadedesons.Screens.HomeScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Home"
    ){

        composable("Home"){
            HomeScreen(navController)
        }

        composable("Dormir"){
            DormirScreen(navController)
        }

        composable("Estudar"){
            EstudarScreen(navController)
        }

        composable("Meditar"){
            MeditarScreen(navController)
        }
    }
}