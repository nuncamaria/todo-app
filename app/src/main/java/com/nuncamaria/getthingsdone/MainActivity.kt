package com.nuncamaria.getthingsdone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nuncamaria.getthingsdone.ui.theme.GetThingsDoneTheme
import com.nuncamaria.getthingsdone.ui.todoentry.AddToDoView
import com.nuncamaria.getthingsdone.ui.todolist.ToDoListView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController: NavHostController = rememberNavController()
            GetThingsDoneTheme {
                ToDoNavHost(navController)
            }
        }
    }
}

@Composable
private fun ToDoNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            ToDoListView(
                navigateToAddToDo = { navController.navigate("AddToDo") },
            )
        }
        composable(route = "AddToDo") {
            AddToDoView(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

