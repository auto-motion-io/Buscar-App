package com.example.futurobuscartelas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.ui.theme.FuturoBuscarTelasTheme
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
            FuturoBuscarTelasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
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
        }

}
}

@Composable
fun ArrowBackButton(navController: NavController)  {
    var clicked by remember { mutableStateOf(false) }

    // Definindo a cor de fundo animada
    val targetColor = if (clicked) ClickAnimationColor else BackGroundColor
    val animatedColor by animateColorAsState(targetColor, animationSpec = tween(durationMillis = 300))

    Box(
        contentAlignment = Alignment.Center, // Centraliza o conteúdo da Box
        modifier = Modifier
            .size(36.dp) // Tamanho da Box
            .clip(shape = CircleShape)
            .background(animatedColor) // Forma circular e cor animada
            .clickable {
                clicked = !clicked
                navController.popBackStack()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back_green),
            contentDescription = "Botão voltar",
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true,)
fun InitPreview() {
    FuturoBuscarTelasTheme {
        MyApp()
    }
}