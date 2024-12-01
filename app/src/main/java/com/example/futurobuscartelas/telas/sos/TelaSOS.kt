package com.example.futurobuscartelas.telas.sos

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.models.Oficina
import coil3.compose.AsyncImage
import com.example.futurobuscartelas.api.google.LocationRepository
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.models.CepInfo
import com.example.futurobuscartelas.signup.SignUpViewModel
import com.example.futurobuscartelas.telas.home.TelaInicial
import com.example.futurobuscartelas.telas.viewmodels.SosViewModel
import com.example.futurobuscartelas.telas.viewmodels.TelasViewModel
import com.example.futurobuscartelas.ui.theme.CardSOS
import com.example.futurobuscartelas.ui.theme.NavigationBar
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.math.roundToInt

class TelaSOSActivity : ComponentActivity() {

    private val sessaoUsuario: SessaoUsuario by inject()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelaSOS(selectedTabIndex = 0, onTabSelected = {},sessaoUsuario = sessaoUsuario)
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TelaSOS(selectedTabIndex: Int, onTabSelected: (Int) -> Unit, sessaoUsuario: SessaoUsuario) {
    val viewModel:SosViewModel = viewModel()
    val oficinas = viewModel.getOficinas()
    var showMessage by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val visibleCards = remember { mutableStateListOf<Oficina>() }

    LaunchedEffect(Unit) {
        // A primeira chamada que carrega as oficinas
        viewModel.listarOficinas(context)
    }

    LaunchedEffect(oficinas) {
        // Verifica se as oficinas estão carregadas e só então as adiciona aos cartões
        if (oficinas.isNotEmpty() && visibleCards.isEmpty()) {
            Log.i("Location", "Oficinas ${oficinas.toList()}")
            visibleCards.addAll(oficinas) // Adiciona as oficinas apenas quando carregadas
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.mipmap.logo_buscar),
                        contentDescription = "Imagem do Logo do Buscar",
                        Modifier.width(60.dp)
                    )

                }
                Row(
                    Modifier
                        .padding(top = 20.dp, bottom = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.label_tituloSOS),
                        fontSize = 30.sp,
                        fontFamily = PRODUCT_SANS_FAMILY,
                        fontWeight = FontWeight.Bold,
                        color = VerdeBuscar
                    )
                    Row(
                    ) {
                        Image(
                            painter = painterResource(R.mipmap.icon_engrenagem),
                            contentDescription = "Imagem de engrenagem(configurações)",
                            Modifier.size(26.dp)
                        )
                    }
                }

//                val oficinaExemplo = Oficina(
//                    id = 1,
//                    nome = "Oficina Exemplo",
//                    cnpj = "12.345.678/0001-90",
//                    cep = "12345-678",
//                    logradouro = "Rua Exemplo",
//                    bairro = "Bairro Exemplo",
//                    cidade = "Cidade Exemplo",
//                    numero = "123",
//                    complemento = "Apartamento 101",
//                    hasBuscar = true,
//                    logoUrl = "http://exemplo.com/logo.png",
//                    distance = 10
//                )

                // Box para exibir os cartões
                Box(Modifier.fillMaxSize()) {
                    var infoCep by remember { mutableStateOf<CepInfo?>(null) }
                    visibleCards.forEach { oficina ->
                        SwipeableCard(
                            cardContent = { CardSOS(sessaoUsuario.id, oficina) }, // Passa os dados da oficina para o CardSOS
                            onSwipeComplete = {
                                // Remove o cartão da lista após o swipe
                                visibleCards.remove(oficina) // Use remove para eliminar diretamente

                                // Verifica se não há mais cartões
                                if (visibleCards.isEmpty()) {
                                    showMessage = true // Exibe a mensagem
                                }
                            }
                        )
                    }
                    //CardSOS(1, oficinaExemplo)
                }


                // Exibição da mensagem quando todos os cartões forem removidos
                if (showMessage) {
                    Text(
                        text = "Acabaram as oficinas em sua área, aperte na tela para resetar a lista",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally) // Centraliza o texto
                            .pointerInput(Unit) {
                                detectTapGestures(onTap = {
                                    // Resetando a lista de cartões e escondendo a mensagem
                                    visibleCards.clear()
                                    visibleCards.addAll(oficinas) // Use addAll para restaurar
                                    showMessage = false
                                })
                            }
                    )
                }
            }
        }
    }
}


@Composable
fun SwipeableCard(
    cardContent: @Composable () -> Unit,
    onSwipeComplete: () -> Unit
) {
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        // Define limite para swipe completo
                        if (offsetX.value > 300f || offsetX.value < -300f) {
                            // Anima para fora da tela
                            scope.launch {
                                offsetX.animateTo(
                                    if (offsetX.value > 0) 1000f else -1000f,
                                    animationSpec = tween(durationMillis = 300)
                                )
                                onSwipeComplete() // Chama a função de completar o swipe
                                offsetX.snapTo(0f)
                            }
                        } else {
                            // Retorna o card para o centro se não completar o swipe
                            scope.launch {
                                offsetX.animateTo(0f, tween(durationMillis = 300))
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                        }
                    }
                )
            }
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(),
                    0
                )
            } // Controla a posição do card com o offset
    ) {
        cardContent() // Renderiza o conteúdo do cartão
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    TelaSOS(selectedTabIndex = 1, onTabSelected = {}, sessaoUsuario = SessaoUsuario())
}