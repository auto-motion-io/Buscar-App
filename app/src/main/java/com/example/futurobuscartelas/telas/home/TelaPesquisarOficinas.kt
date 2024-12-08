package com.example.futurobuscartelas.telas.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.login.UserData
import com.example.futurobuscartelas.login.UserRepository
import com.example.futurobuscartelas.telas.viewmodels.TelaInicialViewModel
import com.example.futurobuscartelas.ui.theme.MotionLoading
import com.example.futurobuscartelas.ui.theme.TelaBaseOSP
import org.koin.android.ext.android.inject

class TelaPesquisarOficinasActivity : ComponentActivity() {

    private val sessaoUsuario: SessaoUsuario by inject()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelaPesquisarOficinas(sessaoUsuario)
        }
    }
}

@Composable
fun TelaPesquisarOficinas(sessaoUsuario: SessaoUsuario) {
    val context = LocalContext.current;
    val viewmodel: TelaInicialViewModel = viewModel()
    val listaOficinas = viewmodel.getOficinas()
    var userData by remember { mutableStateOf<UserData?>(null) }
    val userRepository = remember { UserRepository(context) }

    LaunchedEffect(Unit) {
        viewmodel.listarOficinas()
        userRepository.getUserData().collect { data ->
            userData = data
        }
    }

    val isLoading by viewmodel.isLoading

    if(isLoading) {
        MotionLoading()
    }

    TelaBaseOSP(
        titulo = stringResource(id = R.string.label_tituloOficinas),
        context,
        listaOficinas,
        userData
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPesquisarOficinasPreview() {
    val navController = rememberNavController()
    //TelaPesquisarOficinas()
}