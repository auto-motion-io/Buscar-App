package com.example.futurobuscartelas.telas.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.models.Produto
import com.example.futurobuscartelas.models.Servico
import com.example.futurobuscartelas.ui.theme.*

@Composable
fun OficinaScreen(name: String, modifier: Modifier = Modifier, listaServicos: List<Servico>, listaPecas: List<Produto>) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color(250, 250, 250))
            .verticalScroll(rememberScrollState())
    )
    {
        // Logo section
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 46.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.logo_buscar),
                contentDescription = "Logo Buscar",
                Modifier
                    .size(90.dp)
            )
        }

        // Main content section
        Column(
            Modifier
                .padding(bottom = 70.dp)
                .padding(horizontal = 25.dp)
        ) {
            // Header row with title and favorite icon
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Fast Motors",
                    color = Color(59, 86, 60),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = PRODUCT_SANS_FAMILY
                )
                Image(
                    painter = painterResource(id = R.mipmap.icon_fav),
                    contentDescription = "Botão de Favoritar",
                    Modifier.size(18.dp)
                )
            }

            // Rating row
            Row(
                Modifier
                    .padding(top = 5.dp, bottom = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.icon_stars),
                    contentDescription = "Icone de Estrela - Avaliação",
                    Modifier
                        .size(20.dp)
                        .padding(top = 2.dp)
                )
                Column {
                    Text(
                        text = "5.0",
                        Modifier.padding(bottom = 4.dp, start = 5.dp),
                        fontSize = 14.sp,
                        color = Color(30, 30, 30),
                        fontFamily = PRODUCT_SANS_FAMILY
                    )
                }
            }

            // Image section
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(46.dp))
                    .background(color = Color(185, 185, 185, 28))
                    .fillMaxWidth()
                    .height(220.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.logo_buscar_nocolor),
                    contentDescription = "Imagem da Oficina",
                    modifier = Modifier.size(150.dp)
                )
            }

            // Bottom section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                // Left column
                Column(
                    modifier = Modifier
                ) {
                    Row(
                        modifier = Modifier
                            .width(220.dp)
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(2.dp, Color(59, 86, 60)),
                                    shape = RoundedCornerShape(26.dp)
                                )
                                .padding(horizontal = 18.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(Modifier.padding(top = 4.dp, end = 6.dp)) {
                                Image(
                                    painter = painterResource(id = R.mipmap.icon_carro),
                                    contentDescription = "Icone de Carro",
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Text(
                                text = "Carros", fontSize = 14.sp,
                                color = Color(59, 86, 60),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row(
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(2.dp, Color(59, 86, 60)),
                                    shape = RoundedCornerShape(26.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                                .width(80.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(Modifier.padding(end = 6.dp, top = 2.dp)) {
                                Image(
                                    painter = painterResource(id = R.mipmap.icon_moto),
                                    contentDescription = "Icone de Moto",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Text(
                                text = "Motos", fontSize = 14.sp,
                                color = Color(59, 86, 60),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(2.dp, Color(59, 86, 60)),
                                    shape = RoundedCornerShape(26.dp)
                                )
                                .padding(horizontal = 18.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(Modifier.padding(top = 4.dp, end = 6.dp)) {
                                Image(
                                    painter = painterResource(id = R.mipmap.icon_combustivel),
                                    contentDescription = "Icone de Combustivel",
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Text(
                                text = "Combustão", fontSize = 14.sp,
                                color = Color(59, 86, 60),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                    }
                }
            }

            // ---------

            Row(
                Modifier.padding(top = 20.dp)
            ) {
                Column(Modifier.padding(end = 20.dp)) {
                    Image(
                        painterResource(id = R.mipmap.icon_relogio),
                        contentDescription = "Icone de Relógio",
                        Modifier.size(25.dp)
                    )
                }
                Column(Modifier.padding(top = 1.dp)) {
                    Text(
                        text = "9H00 ás 16H00",
                        fontSize = 14.sp,
                        color = Color(50, 50, 50, 240),
                        fontFamily = PRODUCT_SANS_FAMILY
                    )
                }
            }

            // ---------

            Row(Modifier.padding(top = 20.dp)) {
                Column(
                    Modifier
                        .padding(start = 3.dp)
                        .padding(top = 15.dp)
                ) {
                    Image(
                        painterResource(id = R.mipmap.icon_calendario),
                        contentDescription = "Icone de Calendário",
                        Modifier.size(20.dp)
                    )
                }
                CheckSemana(listOf(false, true, true, true, true, false, true))
            }

            //--------------
            Column(
                Modifier.padding(top = 20.dp)
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.label_servicos),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(59, 86, 60),
                        fontFamily = PRODUCT_SANS_FAMILY
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ListarServicos(Modifier.weight(0.4f), listaServicos)
                }
            }
            Column(
                Modifier.padding(top = 20.dp)
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.label_pecas),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(59, 86, 60),
                        fontFamily = PRODUCT_SANS_FAMILY
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ListarPecas(Modifier.weight(0.4f), listaPecas)
                }
            }

            Row(
                Modifier.padding(top = 30.dp)
            ) {
                Text(
                    text = stringResource(R.string.label_avaliacoes),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(59, 86, 60),
                    fontFamily = PRODUCT_SANS_FAMILY
                )
            }
            Column{
                AddAvaliacao(usuario = "Mario Gonzales", estrelas = 5, mensagem = "")
            }
        }
    }
}




@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {

}