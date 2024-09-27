package com.example.futurobuscartelas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futurobuscartelas.ui.theme.*

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                    MyApp()
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
            composable("before-signup"){ BeforeSignUpScreen(navController = navController)}
            composable("oficina"){ OficinaScreen(name = "")}
            composable("signup"){ SignUpScreen(navController = navController)}
        }

}
}




@Composable
@Preview(showBackground = true, showSystemUi = true)
fun InitPreview() {
        MyApp()
}