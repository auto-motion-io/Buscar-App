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
import com.example.futurobuscartelas.telas.viewmodels.OrdemServicoViewModel
import com.example.futurobuscartelas.telas.viewmodels.TelasViewModel
import com.example.futurobuscartelas.ui.theme.ArrowBackButton
import com.example.futurobuscartelas.ui.theme.ArrowBackButtonIntent
import com.example.futurobuscartelas.ui.theme.ListarPecas
import com.example.futurobuscartelas.ui.theme.ListarPecasOs
import com.example.futurobuscartelas.ui.theme.ListarServicos
import com.example.futurobuscartelas.ui.theme.ListarServicosOs
import com.example.futurobuscartelas.ui.theme.NavigationBar
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.Locale

class TelaOsActivity : ComponentActivity() {

    private val sessaoUsuario: SessaoUsuario by inject()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val token = intent.getStringExtra("TOKEN_KEY")
        setContent {
            TelaOS(selectedTabIndex = 0, onTabSelected = {},sessaoUsuario = sessaoUsuario, token = token)
        }
    }
}

@Composable
fun TelaOS(selectedTabIndex: Int, onTabSelected: (Int) -> Unit, sessaoUsuario: SessaoUsuario, token: String?) {
    val context = LocalContext.current
    val viewModel: OrdemServicoViewModel = viewModel()
    val ordemLista = viewModel.getListaOs()
    var ordem = ordemLista.firstOrNull()
    val oficina = viewModel.getOficinas().firstOrNull()
    val servicos = viewModel.getServicos()
    val pecas = viewModel.getPecas()
    val listaServicosOs = ordem?.servicos
    val listaPecasOs = ordem?.produtos

    LaunchedEffect(Unit) {
        if (token != null) {
            viewModel.listarOsPorToken(token)
        }
    }

    LaunchedEffect(ordemLista) {
        ordem = ordemLista.firstOrNull()
        ordem?.oficina?.let { viewModel.listarOficinaPorId(it.id) }
    }

    LaunchedEffect(oficina) {
        if (oficina != null) {
            viewModel.listarServicosPorOficina(oficina.id)
            viewModel.listarPecasPorOficina(oficina.id)
        }
    }


    if(ordem != null){
    Column (
        Modifier
            .fillMaxSize()
    ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp, top = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ArrowBackButtonIntent(context, TelaConsultaOSActivity::class.java, null, 2)
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
                            text = "#${ordem?.id}",
                            color = VerdeBuscar,
                            fontSize = 32.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.label_token) + ": " + ordem?.token,
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
                            ordem?.status?.let {
                                Text(
                                    text = it,
                                    color = Color(130, 130, 130),
                                    fontSize = 14.sp,
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
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
                                    ordem?.tipoOs?.let {
                                        Text(
                                            text = it,
                                            fontSize = 10.sp,
                                            fontFamily = PRODUCT_SANS_FAMILY,
                                            color = Color(70, 70, 70)
                                        )
                                    }
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
                                    modifier = Modifier.padding(end = 8.dp).size(14.dp)
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
                                    val dataInicioFormatada = ordem?.dataInicio?.let {
                                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                        formatter.format(it)
                                    } ?: "Data não disponível"
                                    Text(
                                        text = dataInicioFormatada,
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
                                    modifier = Modifier.padding(end = 8.dp).size(14.dp)
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
                                    val dataFimFormatada = ordem?.dataFim?.let {
                                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                        formatter.format(it)
                                    } ?: "Data não disponível"
                                    Text(
                                        text = dataFimFormatada,
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
                            ordem?.oficina?.nome?.let {
                                Text(
                                    text = it,
                                    color = VerdeBuscar,
                                    fontSize = 24.sp,
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                    fontWeight = FontWeight.Bold
                                )
                            }
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
                            ordem?.oficina?.informacoesOficinaDTO?.whatsapp?.let {
                                val textToDisplay = if (it.isNotEmpty()) it else "Sem número de telefone"
                                Text(
                                    text = textToDisplay,
                                    color = Color(80, 80, 80),
                                    fontSize = 14.sp,
                                    fontFamily = PRODUCT_SANS_FAMILY
                                )
                            } ?: Text(
                                text = "Sem número de telefone",
                                color = Color(80, 80, 80),
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
                                text = oficina?.logradouro + ", " + oficina?.numero + " - " + oficina?.cidade,
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
                            ordem?.veiculo?.cliente?.email?.let {
                                Text(
                                    text = it,
                                    color = Color(80,80,80),
                                    fontSize = 14.sp,
                                    fontFamily = PRODUCT_SANS_FAMILY
                                )
                            }
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
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_carro_nocolor),
                                    contentDescription = "Icone de Carro",
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 20.dp)
                                        .size(26.dp)
                                )
                                Column (
                                    Modifier.height(40.dp)
                                ) {
                                    ordem?.veiculo?.placa?.let {
                                        Text(
                                            text = it,
                                            color = Color(80,80,80),
                                            fontSize = 14.sp,
                                            fontFamily = PRODUCT_SANS_FAMILY,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        text = ordem?.veiculo?.modelo + " - " + ordem?.veiculo?.marca,
                                        color = Color(80,80, 80),
                                        fontSize = 10.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontWeight = FontWeight.Bold
                                    )
                                    ordem?.veiculo?.cor?.let {
                                        Text(
                                            text = it,
                                            color = Color(80,80,80),
                                            fontSize = 10.sp,
                                            fontFamily = PRODUCT_SANS_FAMILY,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
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
                                    ordem?.veiculo?.cliente?.nome?.let {
                                        Text(
                                            text = it,
                                            color = Color(80,80,80),
                                            fontSize = 14.sp,
                                            fontFamily = PRODUCT_SANS_FAMILY,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    ordem?.veiculo?.cliente?.telefone?.let {
                                        Text(
                                            text = it,
                                            color = Color(80,80,80),
                                            fontSize = 10.sp,
                                            fontFamily = PRODUCT_SANS_FAMILY,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    ordem?.veiculo?.cliente?.email?.let {
                                        Text(
                                            text = it,
                                            color = Color(80,80,80),
                                            fontSize = 10.sp,
                                            fontFamily = PRODUCT_SANS_FAMILY,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
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
                    if (listaPecasOs != null) {
                        ListarPecasOs(listaPecasOs)
                    } else{
                        Text(
                            text = stringResource(R.string.label_semPecas),
                            color = VerdeBuscar,
                            fontSize = 28.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                    if (listaServicosOs != null) {
                        ListarServicosOs(listaServicosOs)
                    } else{
                        Text(
                            text = stringResource(R.string.label_semServico),
                            color = VerdeBuscar,
                            fontSize = 28.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                        var valorTotal = 0.0;


                        if (listaServicosOs != null) {
                            for (servico in listaServicosOs){
                                valorTotal += servico.valorServico
                            }
                        }
                        if (listaPecasOs != null) {
                            for (peca in listaPecasOs){
                                valorTotal += peca.valorVenda
                            }
                        }
                        Text(
                            text = "R$${valorTotal}",
                            fontSize = 22.sp,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            color = Color(80,80,80)
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
    TelaOS(selectedTabIndex = 2, onTabSelected = {}, sessaoUsuario = SessaoUsuario(), "")
}