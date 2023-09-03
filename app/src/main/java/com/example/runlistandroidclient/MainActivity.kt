package com.example.runlistandroidclient

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.runlistandroidclient.ViewModel.*
import com.example.runlistandroidclient.navigation.SetupNavHost
import com.example.runlistandroidclient.ui.theme.Brow
import com.example.runlistandroidclient.ui.theme.RunListAndroidClientTheme
import dagger.hilt.android.AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunListAndroidClientTheme {

                val navController = rememberNavController()
                val viewModel = hiltViewModel<MainViewModel>()
                val authModel: AuthViewModel by viewModels()
                val tokenViewModel =hiltViewModel<TokenViewModel>()
                val userViewModel = hiltViewModel<UserViewModel>()
                val MainModelTask = hiltViewModel<MainViewModelTask>()
                val EditTaskViewModel = hiltViewModel<EditTaskViewModel>()
                val AddViewModel = hiltViewModel<AddViewModel>()
                val RegisterViewModel = hiltViewModel<RegisterViewModel>()
                SetupNavHost(
                    navController = navController,
                    viewModel = viewModel,
                    authViewModel = authModel,
                    tokenViewModel = tokenViewModel,
                    userViewModel = userViewModel,
                    MainModelTask,
                    EditTaskViewModel,
                    AddViewModel,
                    RegisterViewModel
                )
            }
        }
    }
}



