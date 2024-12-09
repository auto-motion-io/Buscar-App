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

class TelaPesquisarServicosActivity : ComponentActivity() {

    private val sessaoUsuario: SessaoUsuario by inject()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sliderValue = intent.getFloatExtra("SLIDER_VALUE", 0f) // 50f é o valor padrão caso o extra não exista
            TelaPesquisarServicos(sliderValue)
        }
    }
}

@Composable
fun TelaPesquisarServicos(valorPreco: Float) {
    val context = LocalContext.current
    var viewmodel:TelaInicialViewModel = viewModel()
    var listaServicos = viewmodel.getServicos()
    var userData by remember { mutableStateOf<UserData?>(null) }
    val userRepository = remember { UserRepository(context) }

    LaunchedEffect(Unit) {
        viewmodel.listarServicos()
        userRepository.getUserData().collect { data ->
            userData = data
        }
    }
    val isLoading by viewmodel.isLoading

    if(isLoading) {
        MotionLoading()
    }

    val servicosFiltrados = if (valorPreco == 0f) {
        listaServicos // Se valorPreco for null, retorna a lista completa
    } else {
        listaServicos.filter { servico ->
            servico.valorServico >= valorPreco // Filtra as peças cujo valorVenda é menor ou igual ao sliderValue
        }
    }

    TelaBaseOSP(
        titulo = stringResource(R.string.label_servicos),
        context,
        servicosFiltrados,
        userData,
        "Serviços",
        emptyList(),
        emptyList()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPesquisarServicosPreview() {
    //TelaPesquisarServicos()
}