package com.example.futurobuscartelas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
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
import com.example.futurobuscartelas.ui.theme.NavigationBar
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar

@Composable
fun TelaSOS(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {

    Scaffold (
        bottomBar = {
            NavigationBar(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        Column (
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Column (
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 20.dp, start = 30.dp, end = 30.dp)
            ) {
                Row (
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
                Row (
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
                    Row (
                    ) {
                        Image(
                            painter = painterResource(R.mipmap.icon_engrenagem),
                            contentDescription = "Imagem de engrenagem(configurações)",
                            Modifier.size(26.dp)
                        )
                    }
                }
                Column (
                    Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(color = Color(240, 240, 240)),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row (
                        Modifier
                            .weight(0.43f)
                            .fillMaxSize()
                    ) {
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .padding(30.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_fav_semcor),
                                contentDescription = "Imagem de Coração(Favoritar)",
                                Modifier.size(20.dp)
                            )
                        }

                    }
                    Column (
                        Modifier
                            .weight(0.60f)
                            .fillMaxSize()
                    ) {
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(end = 8.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row (
                                Modifier
                                    .width(55.dp)
                                    .height(55.dp)
                                    .clip(RoundedCornerShape(100.dp))
                                    .background(color = Color(230, 230, 230)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_local),
                                    contentDescription = "Imagem de indicação de local",
                                    Modifier
                                        .size(20.dp)

                                )
                            }
                        }
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(end = 8.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row (
                                Modifier
                                    .width(55.dp)
                                    .height(55.dp)
                                    .clip(RoundedCornerShape(100.dp))
                                    .background(color = Color(230, 230, 230)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_whatsapp),
                                    contentDescription = "Imagem de indicação de local",
                                    Modifier
                                        .size(20.dp)

                                )
                            }
                        }
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .weight(2.5f)
                                .background(color = Color(240, 240, 240))
                        ) {
                            Column (
                                Modifier
                                    .weight(0.8f)
                                    .fillMaxSize()
                            ) {
                                Column (
                                    Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp, start = 20.dp),
                                ) {
                                    Text(
                                        text = stringResource(R.string.label_tituloExemplo),
                                        fontSize = 30.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold,
                                        color = VerdeBuscar
                                    )
                                    Text(
                                        text = "600 Metros",
                                        fontSize = 16.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        color = Color(50, 50, 50),
                                        modifier = Modifier.padding(top = 5.dp)
                                    )
                                    Row (
                                        Modifier.padding(top = 5.dp)
                                    ) {
                                        Text(
                                            text = "$$$",
                                            color = VerdeBuscar,
                                            fontFamily = PRODUCT_SANS_FAMILY,
                                            fontSize = 16.sp
                                        )
                                        Text(
                                            text = "$",
                                            color = Color(50, 50, 50),
                                            fontFamily = PRODUCT_SANS_FAMILY,
                                            fontSize = 16.sp
                                        )
                                    }
                                    Row (
                                        Modifier.padding(top = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(R.mipmap.icon_local),
                                            contentDescription = "Imagem de indicação de local",
                                            Modifier.size(20.dp)
                                        )
                                        Text(
                                            text = "Rua Miguel Ferreira de Melo, 789",
                                            color = Color(50, 50, 50),
                                            modifier = Modifier.padding(start = 5.dp)
                                        )
                                    }
                                }
                            }
                            Column (
                                Modifier
                                    .weight(0.20f)
                                    .fillMaxSize()
                                    .padding(top = 18.dp)
                            ) {
                                Row {
                                    Image(
                                        painter = painterResource(R.mipmap.icon_stars),
                                        contentDescription = "Icone de Avaliação (Estrela)",
                                        Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = stringResource(R.string.label_exemploAvaliacao),
                                        modifier = Modifier.padding(start = 4.dp, bottom = 5.dp),
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    TelaSOS(selectedTabIndex = 1, onTabSelected = {})
}