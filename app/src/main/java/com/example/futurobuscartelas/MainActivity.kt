package com.example.futurobuscartelas

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.location.LocationManager
import com.example.futurobuscartelas.location.LocationPermissions
import com.example.futurobuscartelas.location.UserLocation
import com.example.futurobuscartelas.login.ForgotPasswordScreen
import com.example.futurobuscartelas.login.LoginScreen
import com.example.futurobuscartelas.login.UserData
import com.example.futurobuscartelas.login.UserRepository
import com.example.futurobuscartelas.onboarding.BeforeSignUpScreen
import com.example.futurobuscartelas.onboarding.LoadingScreen
import com.example.futurobuscartelas.onboarding.MainViewModel
import com.example.futurobuscartelas.signup.SignUpScreen
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
            LocationManager.initialize(context)
            // Coletar dados diretamente do repositório
            LaunchedEffect(Unit) {
                userRepository.getUserData().collect { data ->
                    userData = data
                }
            }

            RequestPermission {
                logLocation(context = context)
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
            composable("oficina"){ OficinaScreen(name = "")}
            composable("signup"){ SignUpScreen(navController = navController) }
        }

}
}

@Composable
fun RequestPermission(onPermissionGranted: () -> Unit) {
    val context = LocalContext.current
    val permission = Manifest.permission.ACCESS_FINE_LOCATION

    // Cria o launcher para gerenciar o resultado da permissão
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.i("Permission", "Permissão concedida.")
            onPermissionGranted()
        } else {
            Log.i("Permission", "Permissão negada.")
        }
    }

    // Verifica o status da permissão e, se necessário, solicita
    LaunchedEffect(Unit) {
        when (ContextCompat.checkSelfPermission(context, permission)) {
            PackageManager.PERMISSION_GRANTED -> {
                Log.i("Permission", "Permissão já concedida.")
                onPermissionGranted()
            }
            else -> {
                Log.i("Permission", "Solicitando permissão.")
                launcher.launch(permission)
            }
        }
    }
}


fun logLocation(context:Context){
        if (LocationPermissions.hasLocationPermission(context)) {
            LocationManager.getCurrentLocation { location ->
                location?.let {
                    Log.i("Location", "Latitude: ${it.latitude}, Longitude: ${it.longitude}")
                } ?: run {
                    Log.i("Location", "Localização indisponível.")
                }
            }
        } else {
            Log.i("Location", "Permissão de localização não concedida.")
        }
}




@Composable
@Preview(showBackground = true, showSystemUi = true)
fun InitPreview() {
        MyApp(sessaoUsuario = SessaoUsuario())
}