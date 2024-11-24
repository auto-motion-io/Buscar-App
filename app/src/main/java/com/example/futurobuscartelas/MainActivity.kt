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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.login.ForgotPasswordScreen
import com.example.futurobuscartelas.login.LoginScreen
import com.example.futurobuscartelas.login.UserData
import com.example.futurobuscartelas.login.UserRepository
import com.example.futurobuscartelas.onboarding.BeforeSignUpScreen
import com.example.futurobuscartelas.onboarding.LoadingScreen
import com.example.futurobuscartelas.onboarding.MainViewModel
import com.example.futurobuscartelas.signup.SignUpScreen
import com.example.futurobuscartelas.telas.home.OficinaScreen
import com.example.futurobuscartelas.ui.theme.MainScreen
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val sessaoUsuario:SessaoUsuario by inject()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //MyApp()
            val context = LocalContext.current
            val userRepository = remember { UserRepository(context) }
            var userData by remember { mutableStateOf<UserData?>(null) }

            // Coletar dados diretamente do repositório
            LaunchedEffect(Unit) {
                userRepository.getUserData().collect { data ->
                    userData = data
                }
            }

            var isLogged: Boolean = userData != null
            if(isLogged){
                sessaoUsuario.id = userData!!.idUsuario
                sessaoUsuario.nome = userData!!.nome
                sessaoUsuario.token = userData!!.token
                sessaoUsuario.fotoUrl = userData!!.fotoUrl
                MainScreen(sessaoUsuario)
            }else{
                MyApp(sessaoUsuario)
            }
        }
    }
}



@Composable
fun MyApp(sessaoUsuario: SessaoUsuario){
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val isLoading by viewModel.isLoading // Acessa o estado diretamente

    if (isLoading) {
        LoadingScreen()
    } else {
        NavHost(navController = navController, startDestination = "pre_login") {
            composable("pre_login") { PreLoginScreen(navController) }
            composable("login") { LoginScreen(navController,sessaoUsuario) }
            composable("forgot-password"){ ForgotPasswordScreen(navController)}
            composable("before-signup"){ BeforeSignUpScreen(navController = navController) }
            composable("oficina"){ OficinaScreen(name = "") }
            composable("signup"){ SignUpScreen(navController = navController) }
        }

}
}




@Composable
@Preview(showBackground = true, showSystemUi = true)
fun InitPreview() {
        MyApp(sessaoUsuario = SessaoUsuario())
}