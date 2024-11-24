package com.example.futurobuscartelas.telas.perfil

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.MainActivity
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.login.UserRepository
import com.example.futurobuscartelas.ui.theme.CustomInputPerfil
import com.example.futurobuscartelas.ui.theme.NavigationBar
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import kotlinx.coroutines.launch

@Composable
fun TelaPerfil(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }


    val context = LocalContext.current
    val userRepository = remember { UserRepository(context) }

    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        bottomBar ={
            NavigationBar(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
            Column (
                Modifier
                    .fillMaxSize()
                    .background(color = Color(245, 245, 245))
            ) {
                Column (
                    Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(bottomStart = 54.dp, bottomEnd = 54.dp))
                        .background(color = VerdeBuscar)
                ) {
                    Column (
                        Modifier.padding(top = 20.dp, bottom = 20.dp, start = 30.dp, end = 30.dp)

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
                                Modifier.width(60.dp)
                            )
                        }
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(R.mipmap.icon_user),
                                    contentDescription = "Icone do Usuário",
                                    Modifier.size(64.dp)
                                )
                            }
                            Column (
                                Modifier.padding(horizontal = 10.dp)
                            ) {
                                Row {
                                    Text(
                                        text = stringResource(R.string.label_bemvindo),
                                        color = Color(240,240,240),
                                        fontSize = 12.sp,
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
                                        fontSize = 18.sp
                                    )
                                    Row(

                                    ){
                                        Button(
                                            onClick = { /* Lógica de edição */ },
                                            modifier = Modifier.size(20.dp), // Tamanho definido para equivalência visual
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.Transparent,
                                                contentColor = Color.Unspecified
                                            ),
                                            contentPadding = PaddingValues(0.dp) // Remove o padding interno do Button
                                        ) {
                                            Image(
                                                painter = painterResource(R.mipmap.icon_editar),
                                                contentDescription = "Ícone de Editar",
                                                modifier = Modifier.size(12.dp) // Tamanho do ícone
                                            )
                                        }

                                        Button(
                                            onClick = {
                                                coroutineScope.launch {
                                                    userRepository.clearUserData()
                                                }
                                                val intent = Intent(context, MainActivity::class.java).apply {
                                                    // Se necessário, passe dados extras
                                                }
                                                context.startActivity(intent)
                                                // Finaliza a Activity atual para remover da pilha (similar ao popUpTo)
                                                (context as? Activity)?.finish()
                                            },
                                            modifier = Modifier.size(20.dp), // Tamanho definido para equivalência visual
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.Transparent,
                                                contentColor = Color.Unspecified
                                            ),
                                            contentPadding = PaddingValues(0.dp) // Remove o padding interno do Button
                                        ) {
                                            Image(
                                                painter = painterResource(R.mipmap.icon_logout),
                                                contentDescription = "Ícone de Sair",
                                                modifier = Modifier.size(16.dp) // Tamanho do ícone
                                            )
                                        }

                                    }
                                }
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
                                    painter = painterResource(R.mipmap.icon_fidelidade),
                                    contentDescription = "Icone de Preço",
                                    Modifier.size(24.dp)
                                )
                            }
                            Column (
                                Modifier.padding(start = 14.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.label_fidelidade),
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
                        .padding(horizontal = 30.dp, vertical = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Column {

                        Text(
                            stringResource(R.string.label_nome),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        CustomInputPerfil(value = nome, onValueChange = { nome = it })
                    }
                    Column {
                        Text(
                            stringResource(R.string.label_sobrenome),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        CustomInputPerfil(value = sobrenome, onValueChange = {sobrenome = it})
                    }
                }
            }
        }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPerfilPreview() {
        TelaPerfil(selectedTabIndex = 3, onTabSelected = {})
}