package com.example.futurobuscartelas.telas.os

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.telas.viewmodels.TelasViewModel
import com.example.futurobuscartelas.ui.theme.ArrowBackButton
import com.example.futurobuscartelas.ui.theme.ListarProdutos
import com.example.futurobuscartelas.ui.theme.NavigationBar
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import org.koin.android.ext.android.inject

class TelaOsActivity : ComponentActivity() {

    private val sessaoUsuario: SessaoUsuario by inject()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelaOS(selectedTabIndex = 0, onTabSelected = {},sessaoUsuario = sessaoUsuario)
        }
    }
}

@Composable
fun TelaOS(selectedTabIndex: Int, onTabSelected: (Int) -> Unit, sessaoUsuario: SessaoUsuario) {
    val viewModel: TelasViewModel = viewModel()
    var token by remember { mutableStateOf("") }


    var ordens = viewModel.getOrdensDeServico()

    LaunchedEffect(Unit) {
        viewModel.listarOsPorCliente(sessaoUsuario.id)
    }

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
                .padding(paddingValues)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ArrowBackButton {}
                    Row (
                        Modifier
                            .fillMaxWidth()
                            .padding(end = 30.dp)
                        ,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.mipmap.logo_buscar),
                            contentDescription = "Logo do Buscar",
                            Modifier.width(60.dp)
                        )
                    }
                }
                Row (
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.label_exemploId),
                            color = VerdeBuscar,
                            fontSize = 32.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.label_token) + ": " + stringResource(R.string.label_exemploToken),
                            color = Color(130, 130, 130),
                            fontSize = 16.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                    Column(
                        Modifier
                            .width(110.dp)
                            .padding(top = 25.dp, bottom = 10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.label_status),
                            color = Color(100, 100, 100),
                            fontSize = 18.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            Row (
                                Modifier
                                    .padding(top = 10.dp, end = 5.dp)
                                    .width(8.dp)
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(250, 200, 0))

                            ) {}
                            Text(
                                text = stringResource(R.string.label_statusDesc ),
                                color = Color(130, 130, 130),
                                fontSize = 14.sp,
                                fontFamily = PRODUCT_SANS_FAMILY,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                }
                Column (
                    Modifier.verticalScroll(rememberScrollState())
                ) {
                    Row (
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column (
                            Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .width(110.dp)
                                .height(50.dp)
                                .background(Color(240, 240, 240)),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row (
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_doc),
                                    contentDescription = "Icone de Documento",
                                    modifier = Modifier.size(18.dp)
                                )
                                Column (
                                    Modifier.padding(start = 5.dp)
                                ) {
                                    Text(
                                        stringResource(R.string.label_classificacao),
                                        fontSize = 12.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(70, 70, 70)
                                    )
                                    Text(stringResource(R.string.label_orcamento),
                                        fontSize = 10.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        color = Color(70, 70, 70)
                                    )
                                }
                            }
                        }
                        Column (
                            Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .width(110.dp)
                                .height(50.dp)
                                .background(Color(240, 240, 240)),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row (
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_calendario_os),
                                    contentDescription = "Icone de calendario",
                                    modifier = Modifier.size(14.dp)
                                )
                                Column (
                                    Modifier.padding(start = 5.dp)
                                ) {
                                    Text(
                                        stringResource(R.string.label_inicio),
                                        fontSize = 12.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(70, 70, 70)
                                    )
                                    Text(stringResource(R.string.label_dataInicio),
                                        fontSize = 10.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        color = Color(70, 70, 70)
                                    )
                                }
                            }
                        }
                        Column (
                            Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .width(110.dp)
                                .height(50.dp)
                                .background(Color(240, 240, 240)),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row (
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_calendario_filled_os),
                                    contentDescription = "Icone de calendario",
                                    modifier = Modifier.size(14.dp)
                                )
                                Column (
                                    Modifier.padding(start = 5.dp)
                                ) {
                                    Text(
                                        stringResource(R.string.label_termino),
                                        fontSize = 12.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(70, 70, 70)
                                    )
                                    Text(stringResource(R.string.label_dataFim),
                                        fontSize = 10.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        color = Color(70, 70, 70)
                                    )
                                }
                            }
                        }
                    }
                    Column (
                        Modifier.padding(horizontal = 2.dp)
                    ) {
                        Row (
                            Modifier.padding(top = 20.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.label_exemploOficina),
                                color = VerdeBuscar,
                                fontSize = 24.sp,
                                fontFamily = PRODUCT_SANS_FAMILY,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row (
                            Modifier.padding(top = 5.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.label_oficinaDesc),
                                color = Color(80,80,80),
                                fontSize = 14.sp,
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row (
                            Modifier.padding(top = 5.dp)
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_whatsapp),
                                contentDescription = "Icone do WhatsApp",
                                modifier = Modifier
                                    .padding(top = 2.dp, end = 4.dp)
                                    .size(15.dp)
                            )
                            Text(
                                text = stringResource(R.string.label_exemploTelefone),
                                color = Color(80,80,80),
                                fontSize = 14.sp,
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row (
                            Modifier.padding(top = 5.dp)
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_local),
                                contentDescription = "Icone de Local",
                                modifier = Modifier
                                    .padding(top = 2.dp, end = 4.dp)
                                    .size(15.dp)
                            )
                            Text(
                                text = stringResource(R.string.label_exemploLocal),
                                color = Color(80,80,80),
                                fontSize = 14.sp,
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row (
                            Modifier.padding(top = 5.dp)
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_email),
                                contentDescription = "Icone de Email",
                                modifier = Modifier
                                    .padding(top = 2.dp, end = 4.dp)
                                    .size(15.dp)
                            )
                            Text(
                                text = stringResource(R.string.label_exemploEmail),
                                color = Color(80,80,80),
                                fontSize = 14.sp,
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                    }
                    Row (
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column (
                            Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .weight(0.4f)
                                .height(70.dp)
                                .background(Color(240, 240, 240)),
                        ) {
                            Row (
                                Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_carro_nocolor),
                                    contentDescription = "Icone de Carro",
                                    modifier = Modifier
                                        .padding(end = 15.dp)
                                        .size(20.dp)
                                )
                                Column (
                                    Modifier.height(40.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.label_placa),
                                        color = Color(80,80,80),
                                        fontSize = 14.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = stringResource(R.string.label_descricao_veiculo),
                                        color = Color(80,80,80),
                                        fontSize = 10.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = stringResource(R.string.label_cor_veiculo),
                                        color = Color(80,80,80),
                                        fontSize = 10.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.weight(0.02f))
                        Column (
                            Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .weight(0.4f)
                                .height(70.dp)
                                .background(Color(240, 240, 240))
                        ) {
                            Row (
                                Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_user_nocolor),
                                    contentDescription = "Icone de Carro",
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .size(20.dp)
                                )
                                Column (
                                    Modifier.height(40.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.label_nome_exemplo),
                                        color = Color(80,80,80),
                                        fontSize = 14.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = stringResource(R.string.label_exemploTelefone),
                                        color = Color(80,80,80),
                                        fontSize = 10.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = stringResource(R.string.label_exemploEmail),
                                        color = Color(80,80,80),
                                        fontSize = 10.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                    Row (
                        Modifier
                            .padding(top = 20.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.label_produtos),
                            color = VerdeBuscar,
                            fontSize = 28.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    ListarProdutos()
                    Row (
                        Modifier
                            .padding(top = 20.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.label_servicos),
                            color = VerdeBuscar,
                            fontSize = 28.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    ListarProdutos()
                    Row (
                        Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.label_valor_total),
                            color = VerdeBuscar,
                            fontSize = 28.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.label_total),
                            fontSize = 22.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaOsPreview() {
    TelaOS(selectedTabIndex = 2, onTabSelected = {}, sessaoUsuario = SessaoUsuario())
}