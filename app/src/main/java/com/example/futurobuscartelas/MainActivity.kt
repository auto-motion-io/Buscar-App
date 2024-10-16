package com.example.futurobuscartelas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futurobuscartelas.onboarding.BeforeSignUpScreen
import com.example.futurobuscartelas.onboarding.LoadingScreen
import com.example.futurobuscartelas.onboarding.MainViewModel
import com.example.futurobuscartelas.signup.SignUpScreen
import com.example.futurobuscartelas.ui.theme.MainScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //MyApp()
            var isLogged: Boolean = true;
            if(isLogged){
                MainScreen()
            }
        }
    }
}



@Composable
fun MyApp(){
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val isLoading by viewModel.isLoading // Acessa o estado diretamente

    if (isLoading) {
        LoadingScreen()
    } else {
        NavHost(navController = navController, startDestination = "pre_login") {
            composable("pre_login") { PreLoginScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("before-signup"){ BeforeSignUpScreen(navController = navController) }
            composable("oficina"){ OficinaScreen(name = "")}
            composable("signup"){ SignUpScreen(navController = navController) }
        }

}
}




@Composable
@Preview(showBackground = true, showSystemUi = true)
fun InitPreview() {
        MyApp()
}