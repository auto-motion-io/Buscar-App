package com.example.futurobuscartelas.telas.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.dto.AvaliacaoDTO
import com.example.futurobuscartelas.dto.OficinaDTO
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.models.Produto
import com.example.futurobuscartelas.models.Servico
import com.example.futurobuscartelas.telas.os.TelaConsultaOSActivity
import com.example.futurobuscartelas.telas.viewmodels.TelaInicialViewModel
import com.example.futurobuscartelas.ui.theme.*
import org.koin.android.ext.android.inject

class OficinaScreenActivity : ComponentActivity() {

    private val sessaoUsuario: SessaoUsuario by inject()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val oficina = intent.getSerializableExtra("OFICINA_KEY", OficinaDTO::class.java)
            val fav = intent.getStringExtra("OFICINA_KEY")
            OficinaScreen(oficina, sessaoUsuario, fav)
        }
    }
}

@Composable
fun OficinaScreen(oficina: OficinaDTO?, sessaoUsuario: SessaoUsuario, fav:String?) {
    val context = LocalContext.current
    val viewModel: TelaInicialViewModel = viewModel()
    val listaServicos = viewModel.getServicos()
    val listaPecas = viewModel.getPecas()
    val avaliacoes = viewModel.getAvaliacoes()
    var liked by remember { mutableStateOf(false) }
    val listaOficinasFavoritas = viewModel.getOficinasFavoritas()
    listaOficinasFavoritas.forEach { oficinaFav ->
        if (oficina != null) {
            if (oficinaFav.oficina.id == oficina.id && oficinaFav.usuario.idUsuario == sessaoUsuario.id) {
                liked = true;
            }
        }
    }

    LaunchedEffect(Unit) {
        oficina?.id?.let { viewModel.listarServicosPorOficina(it) }
        oficina?.id?.let { viewModel.listarPecasPorOficina(it) }
        oficina?.id?.let { viewModel.buscarAvaliacoes(it) }
        oficina?.id?.let { viewModel.listarOficinasFavoritas(it) }
    }

    val isLoading by viewModel.isLoading

    if(isLoading) {
        MotionLoading()
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color(250, 250, 250))
            .verticalScroll(rememberScrollState())
    ) {
        // Main content section
        Column(
            Modifier
                .padding(top = 20.dp, bottom = 70.dp)
                .padding(horizontal = 25.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp, top = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ArrowBackButtonIntent(context, TelaInicialActivity::class.java, null, 1)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 35.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.mipmap.logo_buscar),
                        contentDescription = "Logo do Buscar!"
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                oficina?.nome?.let {
                    Text(
                        text = it,
                        color = Color(59, 86, 60),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PRODUCT_SANS_FAMILY
                    )
                }
                Button(
                    onClick = {
                        if (liked) {
                            if (oficina != null) {
                                viewModel.removeOficina(sessaoUsuario.id, oficina.id)
                            }
                            liked = false;
                        } else {
                            if (oficina != null) {
                                viewModel.favoritarOficina(sessaoUsuario.id, oficina.id)
                            }
                            liked = true;
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(230, 230, 230) // Removendo a cor de fundo
                    ),
                    shape = RoundedCornerShape(100.dp),
                    elevation = null, // Removendo elevação
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Image(
                        painter = if (!liked) {
                            painterResource(R.mipmap.icon_fav_semcor)
                        } else {
                            painterResource(R.mipmap.icon_fav)
                        },
                        contentDescription = "Imagem de Coração(Favoritar)",
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(100.dp))
                    )
                }
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
                Column(
                    Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = oficina?.mediaAvaliacao?.nota.toString(),
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
                    .fillMaxWidth()
                    .height(220.dp),
                contentAlignment = Alignment.Center
            ) {
                if (oficina != null) {
                    AsyncImage(
                        model = oficina.logoUrl, // URL da imagem
                        contentDescription = "Logo da ${oficina.nome}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp) // Ajuste o tamanho conforme necessário
                    )
                }
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
                    if (oficina != null) {
                        Text(
                            text = "${oficina.informacoesOficina?.horarioIniSem} ás ${oficina.informacoesOficina?.horarioFimSem}",
                            fontSize = 14.sp,
                            color = Color(50, 50, 50, 240),
                            fontFamily = PRODUCT_SANS_FAMILY
                        )
                    }
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
                val listaCheck = oficina?.informacoesOficina?.diasSemanaAberto?.split(";")
                val listaBooleans = listaCheck?.map { it.toBoolean() }

                CheckSemana(listaBooleans)
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
                avaliacoes.forEach { avaliacao ->
                    AddAvaliacao(usuario = avaliacao.usuarioAvaliacao.nome, estrelas = avaliacao.nota.toInt(), mensagem = avaliacao.comentario, nomeUsuario = avaliacao.usuarioAvaliacao.nome, logoUsuario = avaliacao.usuarioAvaliacao.fotoUrl.toString())
                }
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