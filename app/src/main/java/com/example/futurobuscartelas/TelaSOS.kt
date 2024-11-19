package com.example.futurobuscartelas

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
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
import coil3.compose.AsyncImage
import com.example.futurobuscartelas.api.google.LocationRepository
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.signup.SignUpViewModel
import com.example.futurobuscartelas.telas.viewmodels.SosViewModel
import com.example.futurobuscartelas.telas.viewmodels.TelasViewModel
import com.example.futurobuscartelas.ui.theme.CardSOS
import com.example.futurobuscartelas.ui.theme.NavigationBar
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TelaSOS(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    val viewModel:SosViewModel = viewModel()
    val oficinas = viewModel.getOficinas() // Obtém a lista de oficinas atualizadas do ViewModel
    var showMessage by remember { mutableStateOf(false) } // Estado para controlar a mensagem
    val context = LocalContext.current
    // Chamamos listarOficinas quando a tela é composta
    LaunchedEffect(Unit) {
        viewModel.listarOficinas(context)
    }


    // Use derivedStateOf para garantir que visibleCards atualize com oficinas
    val visibleCards = remember { mutableStateListOf<Oficina>() }

    LaunchedEffect(oficinas) {
        //FIXME Aqui a distancia da oficina vem null (mesmo quando nao e para vir), na viewmodel nunca vem
        Log.i("Location","Oficinas ${oficinas.toList()}")
        visibleCards.clear()
        visibleCards.addAll(oficinas)
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

                //CardSOS() FIXME david arruma isso aq

                // Box para exibir os cartões
                Box(Modifier.fillMaxSize()) {
                    visibleCards.forEach { oficina ->
                        SwipeableCard(
                            cardContent = { CardSOS(oficina) }, // Passa os dados da oficina para o CardSOS
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
    TelaSOS(selectedTabIndex = 1, onTabSelected = {})
}