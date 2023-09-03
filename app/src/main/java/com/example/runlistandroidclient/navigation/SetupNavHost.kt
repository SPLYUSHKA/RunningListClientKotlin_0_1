package com.example.runlistandroidclient.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.runlistandroidclient.ViewModel.*
import com.example.runlistandroidclient.screens.*
import com.example.runlistandroidclient.utils.Constans

sealed class Screens(val route:String)
{
    object Splash : Screens(route = Constans.Screens.SPLASH_SCREEN)
    object Login : Screens(route = Constans.Screens.LOG_SCREEN)
    object Sign : Screens(route = Constans.Screens.SIGN_SCREEN)
    object Navigate : Screens(route = Constans.Screens.NAVIGATE_SCREEN)
    object Main : Screens(route = Constans.Screens.MAIN_SCREEN)
    object CalendarWeek : Screens(route = Constans.Screens.CALENDAR_WEEK_SCREEN)
    object EditTask : Screens(route = Constans.Screens.EDIT_TASK_SCREEN)
    object AddTask : Screens(route = Constans.Screens.ADD_TASK_SCREEN)
    object AboutSystem : Screens(route = Constans.Screens.ABOUT_SYSTEM_SCREEN)

}
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterialApi
@Composable
fun SetupNavHost(navController: NavHostController, viewModel :MainViewModel, authViewModel: AuthViewModel, tokenViewModel: TokenViewModel ,userViewModel: UserViewModel ,MainViewModelTask : MainViewModelTask, EditTaskViewModel : EditTaskViewModel,AddViewModel : AddViewModel, RegisterViewModel :RegisterViewModel  )
{
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    )
    {
        composable(route = Screens.Splash.route){
            SplashScreen(navController, viewModel)
        }
        composable(route = Screens.Login.route)
        {
            LogInScreen(navController,viewModel,authViewModel,tokenViewModel,userViewModel)
        }
        composable(route = Screens.Sign.route){
            RegistrScreen(navController = navController,RegistViewModel = RegisterViewModel)
        }
        composable(route = Screens.Navigate.route){
            NavigateScreen(navController = navController, mainViewModelTask = MainViewModelTask,AddViewModel)
        }
        composable(route = Screens.Main.route)
        {
            MainScreenWithTask(navController = navController, mainViewModelTask = MainViewModelTask,EditTaskViewModel)
        }
        composable(route = Screens.CalendarWeek.route){}
        composable(route = Screens.EditTask.route){
            EditTaskScreen(navController = navController, editTaskViewModel = EditTaskViewModel)
        }
        composable(route = Screens.AddTask.route){
            AddTaskScreen(navController = navController,AddViewModel)
        }
        composable(route = Screens.AboutSystem.route){
            AboutSystem(navController = navController)

        }

    }

}