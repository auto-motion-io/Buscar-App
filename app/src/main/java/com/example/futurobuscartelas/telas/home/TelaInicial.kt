package com.example.futurobuscartelas.telas.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.telas.viewmodels.TelaInicialViewModel
import com.example.futurobuscartelas.telas.viewmodels.TelasViewModel
import com.example.futurobuscartelas.ui.theme.InputContainerUnfocusedColor
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import com.example.futurobuscartelas.ui.theme.AddCategoria
import com.example.futurobuscartelas.ui.theme.BotaoPesquisa
import com.example.futurobuscartelas.ui.theme.FotoUsuarioSessao
import com.example.futurobuscartelas.ui.theme.ListarFavoritos
import com.example.futurobuscartelas.ui.theme.NavigationBar
import org.koin.android.ext.android.inject


class TelaInicialActivity : ComponentActivity() {

    private val sessaoUsuario: SessaoUsuario by inject()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelaInicial(selectedTabIndex = 0, sessaoUsuario = sessaoUsuario)
        }
    }
}

@Composable
fun TelaInicial(selectedTabIndex: Int, sessaoUsuario: SessaoUsuario) {
    val viewModel: TelaInicialViewModel = viewModel()
    val oficinas = viewModel.getOficinasFavoritas()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.listarOficinasFavoritas(sessaoUsuario.id)
    }

    var token by remember { mutableStateOf("") }
    Log.i("session", sessaoUsuario.nome)

    Scaffold(
        bottomBar = {
            NavigationBar(
                context,
                selectedTabIndex = selectedTabIndex
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 20.dp, start = 30.dp, end = 30.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.mipmap.logo_buscar),
                        contentDescription = "Logo do Buscar",
                        Modifier.width(60.dp)
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Column (Modifier.padding(end = 10.dp)) {
                        FotoUsuarioSessao(sessaoUsuario)
                    }
                    Column {
                        Text(
                            text = stringResource(id = R.string.label_bemvindo),
                            fontSize = 18.sp,
                            color = Color(59, 86, 60),
                            fontFamily = PRODUCT_SANS_FAMILY
                        )
                        Text(
                            text = sessaoUsuario.nome,
                            fontSize = 18.sp,
                            color = Color(59, 86, 60),
                            fontFamily = PRODUCT_SANS_FAMILY
                        )
                    }
                }

                Column(Modifier.padding(top = 30.dp)) {
                    Column(Modifier.padding(bottom = 20.dp)) {
                        Text(
                            text = stringResource(R.string.label_categorias),
                            fontSize = 36.sp,
                            color = Color(59, 86, 60),
                            fontWeight = FontWeight.Bold,
                            fontFamily = PRODUCT_SANS_FAMILY
                        )
                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AddCategoria(categoria = "Oficinas", context, TelaPesquisarOficinasActivity::class.java)
                        AddCategoria(categoria = "Serviços", context, TelaPesquisarServicosActivity::class.java)
                        AddCategoria(categoria = "Peças", context, TelaPesquisarPecasActivity::class.java)
                    }
                }
                Column(
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxSize()
                ) {
                    Column(Modifier.padding(bottom = 20.dp)) {
                        Text(
                            text = "Favoritos",
                            fontSize = 36.sp,
                            color = Color(59, 86, 60),
                            fontWeight = FontWeight.Bold,
                            fontFamily = PRODUCT_SANS_FAMILY
                        )
                    }
                    ListarFavoritos(Modifier, oficinas, context)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaInicialPreview() {
    TelaInicial(selectedTabIndex = 0, sessaoUsuario = SessaoUsuario())
}