package com.example.futurobuscartelas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.futurobuscartelas.ui.theme.CustomInputMotion
import com.example.futurobuscartelas.ui.theme.CustomInputPerfil
import com.example.futurobuscartelas.ui.theme.InputContainerUnfocusedColor
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.UpperLabelText
import com.example.futurobuscartelas.ui.theme.VerdeBuscar

@Composable
fun TelaPerfil(modifier: Modifier = Modifier) {

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }

    Column (
        Modifier
            .fillMaxSize()
            .background(color = Color(235, 235, 235))
    ) {
        Column (
            Modifier
                .fillMaxWidth()
                .weight(0.60f)
                .clip(RoundedCornerShape(bottomStart = 54.dp, bottomEnd = 54.dp))
                .background(color = VerdeBuscar)
        ) {
            Column (
                Modifier.padding(horizontal = 26.dp)
            ) {
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.mipmap.logo_buscarwhite),
                        contentDescription = "Logo do Buscar",
                        Modifier.size(76.dp)
                    )
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Image(
                            painter = painterResource(R.mipmap.icon_user),
                            contentDescription = "Icone do Usuário",
                            Modifier.size(70.dp)
                        )
                    }
                    Column (
                        Modifier.padding(horizontal = 10.dp)
                    ) {
                        Row {
                            Text(
                                text = stringResource(R.string.label_bemvindo),
                                color = Color(240,240,240),
                                fontSize = 14.sp,
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row (
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Marcos Gonzales",
                                color = Color(240,240,240),
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = PRODUCT_SANS_FAMILY,
                                fontSize = 22.sp
                            )
                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_editar),
                                    contentDescription = "Icone de Editar",
                                    Modifier.size(12.dp),
                                )
                            }
                        }
                    }
                }
                Row (
                    Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth()
                        .height(90.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = Color(230, 230, 230)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column (
                        Modifier.padding(start = 30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.mipmap.icon_calendario_colorido),
                            contentDescription = "Icone de calendário",
                            Modifier.size(24.dp)
                        )
                    }
                    Column (
                        Modifier.padding(start = 14.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.label_minhaAgenda),
                            fontSize = 16.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            color = VerdeBuscar,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "1 Agendamento próximo",
                            fontSize = 12.sp
                        )
                    }
                }
                Row (
                    Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .height(90.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = Color(230, 230, 230)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column (
                        Modifier.padding(start = 30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.mipmap.icon_calendario_colorido),
                            contentDescription = "Icone de calendário",
                            Modifier.size(24.dp)
                        )
                    }
                    Column (
                        Modifier.padding(start = 14.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.label_minhaAgenda),
                            fontSize = 16.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            color = VerdeBuscar,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "1 Agendamento próximo",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
        Column (
            Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 30.dp)
                .weight(0.40f),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Column {

                Text(
                    stringResource(R.string.label_nome),
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = VerdeBuscar
                )
                CustomInputPerfil(value = nome, onValueChange = { nome = it })
            }
            Column {
                Text(
                    stringResource(R.string.label_sobrenome),
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = VerdeBuscar
                )
                CustomInputPerfil(value = sobrenome, onValueChange = {sobrenome = it})
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview5() {
        TelaPerfil()
}