package com.example.futurobuscartelas.ui.theme

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.dto.OficinaDTO
import com.example.futurobuscartelas.telas.os.TelaConsultaOS
import com.example.futurobuscartelas.telas.home.TelaInicial
import com.example.futurobuscartelas.telas.perfil.TelaPerfil
import com.example.futurobuscartelas.telas.sos.TelaSOS
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.login.UserData
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OficinaFavorita
import com.example.futurobuscartelas.models.OrdemServico
import com.example.futurobuscartelas.models.Produto
import com.example.futurobuscartelas.models.Servico
import com.example.futurobuscartelas.models.Usuario
import com.example.futurobuscartelas.telas.home.OficinaScreenActivity
import com.example.futurobuscartelas.telas.home.TelaInicialActivity
import com.example.futurobuscartelas.telas.home.TelaPesquisarOficinasActivity
import com.example.futurobuscartelas.telas.home.TelaPesquisarPecas
import com.example.futurobuscartelas.telas.home.TelaPesquisarPecasActivity
import com.example.futurobuscartelas.telas.home.TelaPesquisarServicosActivity
import com.example.futurobuscartelas.telas.os.TelaConsultaOSActivity
import com.example.futurobuscartelas.telas.perfil.TelaPerfilActivity
import com.example.futurobuscartelas.telas.sos.TelaSOSActivity
import com.example.futurobuscartelas.telas.viewmodels.SosViewModel
import com.example.futurobuscartelas.telas.viewmodels.TelaInicialViewModel

@Composable
fun AddAvaliacao(usuario: String, estrelas: Int, mensagem: String, nomeUsuario: String, logoUsuario: String) {
    var estrelasAdd: Int = 0;

    Row(
        Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(238, 238, 238))
    ) {
        Column(
            Modifier.padding(start = 20.dp, top = 10.dp, bottom = 24.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FotoUsuario(nomeUsuario, logoUsuario)
                Column(
                    Modifier.padding(top = 10.dp, start = 10.dp)
                ) {
                    Row {
                        Text(
                            text = usuario,
                            fontSize = 14.sp,
                            fontFamily = PRODUCT_SANS_FAMILY
                        )
                    }
                    Row(
                        Modifier
                            .width(100.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (i in 1..estrelas) {
                            Image(
                                painterResource(id = R.mipmap.icon_stars),
                                contentDescription = "Icone de Avaliação (Estrela)",
                                Modifier.size(16.dp)
                            )
                            estrelasAdd++;
                        }

                        if (estrelasAdd < 5) {
                            val estrelasBranco: Int = 5 - estrelasAdd;

                            for (i in 1..estrelasBranco) {
                                Image(
                                    painterResource(id = R.mipmap.icon_stars_branco),
                                    contentDescription = "Icone de Avaliação (Estrela)",
                                    Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
            Row(
                Modifier
                    .padding(end = 22.dp, top = 10.dp)
            ) {
                Text(
                    text = mensagem,
                    fontSize = 12.sp,
                    fontFamily = PRODUCT_SANS_FAMILY
                )
            }
        }
    }
}

@Composable
fun CheckSemana(listaDias: List<Boolean>?) {
    val diasSemana = listOf("D", "S", "T", "Q", "Q", "S", "S");

    Row(
        Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (i in diasSemana.indices) {
            Column(
                Modifier.padding(start = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (listaDias?.get(i) == true) {
                    CheckOn()
                } else {
                    CheckOff()
                }
                Text(
                    text = diasSemana[i],
                    Modifier
                        .padding(4.dp),
                    color = Color(60, 60, 60),
                    fontSize = 14.sp,
                    fontFamily = PRODUCT_SANS_FAMILY
                )
            }
        }
    }
}

@Composable
fun CheckOn() {
    Image(
        painterResource(id = R.mipmap.icon_check),
        contentDescription = "Icone de Check",
        Modifier.size(24.dp)
    )
}

@Composable
fun CheckOff() {
    Image(
        painterResource(id = R.mipmap.icon_check_semcor),
        contentDescription = "Icone de Check sem cor",
        Modifier.size(24.dp)
    )
}

@Composable
fun ListarServicos(modifier: Modifier, lista: List<Servico>) {
    val listaServicos = lista

    // Agrupa os serviços em pares
    val groupedServicos = listaServicos.chunked(2)

    Column(modifier = modifier) {
        for (grupo in groupedServicos) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (servico in grupo) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 14.dp)
                            .clickable{

                            }
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_servico_padrao),
                                contentDescription = "Icone de Serviço",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize().padding(0.dp)
                            )
                        }
                        Row {
                            Text(
                                text = servico.nome,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row {
                            Text(
                                text = "R$" + servico.valorServico,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListarServicos(modifier: Modifier, lista: List<Servico>, context: Context) {
    val listaServicos = lista

    // Agrupa os serviços em pares
    val groupedServicos = listaServicos.chunked(2)

    Column(modifier = modifier) {
        for (grupo in groupedServicos) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (servico in grupo) {
                    val viewModel: TelaInicialViewModel = viewModel()
                    val listaOficinas = viewModel.getOficinas()

                    val novaOficina: OficinaDTO? = listaOficinas.firstOrNull { oficina ->
                        oficina.id == servico.oficina.id
                    }

                    LaunchedEffect(Unit) {
                        viewModel.listarOficinas2()
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 14.dp)
                            .clickable{
                                val intent = Intent(context, OficinaScreenActivity::class.java)
                                intent.putExtra("OFICINA_KEY", novaOficina)
                                context.startActivity(intent)
                            }
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_servico_padrao),
                                contentDescription = "Icone de Serviço",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize().padding(0.dp)
                            )
                        }
                        Row {
                            Text(
                                text = servico.nome,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row {
                            Text(
                                text = "R$" + servico.valorServico,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListarPecas(modifier: Modifier, lista: List<Produto>) {
    val listaPecas = lista

    // Agrupa as peças em pares
    val groupedPecas = listaPecas.chunked(2)

    Column(modifier = modifier) {
        for (grupo in groupedPecas) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (peca in grupo) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 14.dp)
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_peca_padrao),
                                contentDescription = "Icone de Peça",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize().padding(0.dp)
                            )
                        }
                        Row{
                            Text(
                                text = peca.nome,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row{
                            Text(
                                text = "R$" + peca.valorVenda,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListarPecas(modifier: Modifier, lista: List<Produto>, context: Context) {
    val listaPecas = lista

    // Agrupa as peças em pares
    val groupedPecas = listaPecas.chunked(2)

    Column(modifier = modifier) {
        for (grupo in groupedPecas) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (peca in grupo) {
                    val viewModel: TelaInicialViewModel = viewModel()
                    val listaOficinas = viewModel.getOficinas()

                    val novaOficina: OficinaDTO? = listaOficinas.firstOrNull { oficina ->
                        oficina.id == peca.oficina.id
                    }

                    LaunchedEffect(Unit) {
                        viewModel.listarOficinas2()
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 14.dp)
                            .clickable{
                                val intent = Intent(context, OficinaScreenActivity::class.java)
                                intent.putExtra("OFICINA_KEY", novaOficina)
                                context.startActivity(intent)
                            }
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_peca_padrao),
                                contentDescription = "Icone de Peça",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize().padding(0.dp)
                            )
                        }
                        Row{
                            Text(
                                text = peca.nome,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                        Row{
                            Text(
                                text = "R$" + peca.valorVenda,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddCategoria(categoria: String, context: Context, activity: Class<out ComponentActivity>) {
    Column(
        Modifier
            .width(100.dp)
            .clickable {
                // Navegando para a TelaPesquisarOficinasActivity usando Intent
                val intent = Intent(context, activity)
                context.startActivity(intent)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(46.dp))
                .background(color = Color(241, 240, 237)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = when (categoria) {
                    "Oficinas" -> painterResource(R.mipmap.icon_oficina)
                    "Serviços" -> painterResource(R.mipmap.icon_servico)
                    else -> painterResource(R.mipmap.icon_ferramenta) // Ícone padrão se nenhuma condição for atendida
                },
                contentDescription = "Icone de Lupa Branca",
                Modifier.size(35.dp),
            )
        }
        Row(
            Modifier.padding(10.dp)
        ) {
            Text(
                text = categoria,
                color = Color(59, 86, 60),
                fontWeight = FontWeight.Bold,
                fontFamily = PRODUCT_SANS_FAMILY,
            )
        }

    }
}

@Composable
fun ListarFavoritos(modifier: Modifier, oficinas: List<OficinaFavorita>, context: Context) {

    // Agrupa os serviços em pares
    val groupedFavoritos = oficinas.chunked(2)

    var imageUrl =
        "https://blog.engecass.com.br/wp-content/uploads/2023/09/inovacoes-e-tendencias-para-auto-centers-e-oficinas-mecanicas.jpg"

    Column(
        modifier = modifier
    ) {
        if (oficinas.isNotEmpty()) {
            for (grupo in groupedFavoritos) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (oficinaFav in grupo) {
                        val viewmodel: TelaInicialViewModel = viewModel()
                        val listaOficinas = viewmodel.getOficinas()

                        val novaOficina: OficinaDTO? = listaOficinas.firstOrNull { oficina ->
                            oficina.id == oficinaFav.oficina.id
                        }

                        LaunchedEffect(Unit) {
                            viewmodel.listarOficinas2()
                        }

                        if (!oficinaFav.oficina.logoUrl.isNullOrBlank()) {
                            imageUrl = oficinaFav.oficina.logoUrl
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 14.dp)
                                .clickable {
                                    val intent = Intent(context, OficinaScreenActivity::class.java)
                                    intent.putExtra("OFICINA_KEY", novaOficina)
                                    context.startActivity(intent)
                                }
                        ) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color(240, 239, 236))
                            ) {
                                // Imagem de fundo do card
                                AsyncImage(
                                    model = imageUrl, // URL da imagem
                                    contentDescription = "Logo da ${oficinaFav.oficina.nome}",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(0.dp) // Ajuste o tamanho conforme necessário
                                )
                            }
                            Text(
                                text = oficinaFav.oficina.nome,
                                color = Color(59, 86, 60),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                                fontFamily = PRODUCT_SANS_FAMILY
                            )
                        }
                    }
                }
            }
        } else {
            Column {
                Text(
                    text = stringResource(R.string.label_semFavoritos),
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontSize = 16.sp,
                    color = Color(50, 50, 50)
                )
            }
        }
    }
}

@Composable
fun BotaoPesquisa(fundo: Boolean, onClick: () -> Unit) {
    if (fundo) {
        Button(
            onClick = {
                onClick()
            },
            Modifier
                .padding(start = 5.dp, top = 5.dp)
                .size(48.dp)
                .clip(RoundedCornerShape(220.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeBuscar,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Image(
                painter = painterResource(R.mipmap.icon_lupa_white),
                contentDescription = "Icone de Lupa Branca",
                Modifier.size(16.dp)
            )
        }
    } else {
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .padding(0.dp)
                .width(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0, 0, 0, 0)
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Image(
                painter = painterResource(R.mipmap.icon_lupa),
                contentDescription = "Botão de Pesquisa",
                Modifier
                    .size(22.dp)
                    .wrapContentSize()
            )
        }
    }
}

@Composable
fun CardFiltro(
    tituloCard: String,
    imagePainter: Painter,
    descricaoConteudo: String,
    modifier: Modifier,
    fundo: Boolean,
    onclick: () -> Unit
) {
    if (fundo) {
        Button(
            onClick = { onclick() },
            modifier
                .height(50.dp)
                .clip(RoundedCornerShape(220.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeBuscar,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White
            ),
            contentPadding = PaddingValues(16.dp)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = descricaoConteudo,
                Modifier.size(22.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                text = tituloCard,
                fontFamily = PRODUCT_SANS_FAMILY,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    } else {
        Button(
            onClick = { onclick() },
            modifier
                .height(50.dp)
                .clip(RoundedCornerShape(220.dp))
                .border(2.dp, VerdeBuscar, RoundedCornerShape(220.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0, 0, 0, 0),
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White
            ),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = descricaoConteudo,
                Modifier.size(22.dp)
            )
            Text(
                text = tituloCard,
                fontFamily = PRODUCT_SANS_FAMILY,
                modifier = Modifier.padding(start = 8.dp),
                color = VerdeBuscar
            )
        }
    }
}

@Composable
fun ListarOficinas(modifier: Modifier, lista: List<OficinaDTO>, context: Context) {
    val listaOficinas = lista

    // Agrupa os serviços em pares
    val groupedOficinas = listaOficinas.chunked(2)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (grupo in groupedOficinas) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (oficina in grupo) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 7.dp, end = 7.dp)
                            .clickable {
                                val intent = Intent(context, OficinaScreenActivity::class.java)
                                intent.putExtra("OFICINA_KEY", oficina) // Passa o objeto
                                context.startActivity(intent)
                            }
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color(240, 239, 236, 210))
                        ) {
                            AsyncImage(
                                model = oficina.logoUrl,
                                contentDescription = "Logo da ${oficina.nome}",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(0.dp) // Ajuste o tamanho conforme necessário
                            )
                        }

                        Column(
                            Modifier.padding(bottom = 10.dp)
                        ) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp, top = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = oficina.nome,
                                    fontSize = 16.sp,
                                    color = Color(59, 86, 60),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 5.dp),
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                )
                            }
                            Row(
                                Modifier.padding(top = 7.dp)
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_stars),
                                    contentDescription = "Imagem de Estrela",
                                    Modifier
                                        .padding(end = 2.dp)
                                        .size(12.dp)
                                )
                                Text(
                                    text = oficina.mediaAvaliacao?.nota?.toString() ?: "N/A",
                                    fontSize = 12.sp,
                                    fontFamily = PRODUCT_SANS_FAMILY
                                )
                            }
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp, top = 6.dp),
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_local),
                                    contentDescription = "Icone de ponto de localização",
                                    Modifier.size(11.dp)
                                )
                                Row(
                                    Modifier.padding(start = 4.dp)
                                ) {
                                    Text(
                                        text = oficina.logradouro + " - " + oficina.numero,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontSize = 10.sp,
                                        color = Color(0, 0, 0, 180)
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

@Composable
fun <T> TelaBaseOSP(titulo: String, context: Context, lista: List<T>, userData: UserData?, tipoFiltro: String, veiculosSelecionados: List<String>, propulsoesSelecionadas: List<String>) {
    var showFilterDialog by remember { mutableStateOf(false) }

    Column(
        Modifier
            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Row() {
            ArrowBackButtonIntent(context, TelaInicialActivity::class.java, userData, 1)
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
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = titulo,
                fontSize = 32.sp,
                color = Color(59, 86, 60),
                fontFamily = PRODUCT_SANS_FAMILY,
                fontWeight = FontWeight.Bold
            )
            Row() {
                Button(
                    onClick = { showFilterDialog = true },
                    Modifier
                        .padding(0.dp)
                        .width(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0, 0, 0, 0)
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Image(
                        painter = painterResource(R.mipmap.icon_filtro),
                        contentDescription = "Botão de Pesquisa",
                        Modifier
                            .size(22.dp)
                            .wrapContentSize()
                    )
                }
            }
        }
        Row (
            Modifier
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for(veiculo in veiculosSelecionados){
                if (veiculo == "carro"){
                    CardFiltro(
                        "Carros",
                        painterResource(R.mipmap.icon_carro),
                        "Icone de Carro",
                        Modifier.width(120.dp),
                        true,
                        onclick = {}
                    )
                } else if(veiculo == "moto"){
                    CardFiltro(
                        "Motos",
                        painterResource(R.mipmap.icon_moto),
                        "Icone de Moto",
                        Modifier.width(120.dp),
                        true,
                        onclick = {}
                    )
                } else if(veiculo == "caminhao"){
                    CardFiltro(
                        "Caminhão",
                        painterResource(R.mipmap.icon_caminhao),
                        "Icone de Caminhão",
                        Modifier.width(120.dp),
                        true,
                        onclick = {}
                    )
                } else{
                    CardFiltro(
                        "Ônibus",
                        painterResource(R.mipmap.icon_onibus),
                        "Icone de Ônibus",
                        Modifier.width(120.dp),
                        true,
                        onclick = {}
                    )
                }
            }
            for (propulsao in propulsoesSelecionadas){
                if(propulsao == "combustão"){
                    CardFiltro(
                        "Combustão",
                        painterResource(R.mipmap.icon_combustivel),
                        "Icone de Combustão",
                        Modifier.width(140.dp),
                        true,
                        onclick = {}
                    )
                } else if(propulsao == "Elétrico"){
                    CardFiltro(
                        "Combustão",
                        painterResource(R.mipmap.icon_combustivel),
                        "Icone de Combustão",
                        Modifier.width(140.dp),
                        true,
                        onclick = {}
                    )
                } else if(propulsao == "Hibrido"){
                    CardFiltro(
                        "Híbridos",
                        painterResource(R.mipmap.icon_hibridos),
                        "Icone de Híbridos",
                        Modifier.width(130.dp),
                        true,
                        onclick = {}
                    )
                }
            }
        }
        Row(
            Modifier
                .padding(top = 20f.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (titulo) {
                "Oficinas" -> {
                    val listaOficinas = lista as? List<OficinaDTO> ?: emptyList()
                    ListarOficinas(modifier = Modifier, listaOficinas, context)
                }

                "Peças" -> {
                    val listaPecas = lista as? List<Produto> ?: emptyList()
                    ListarPecas(modifier = Modifier, listaPecas, context)
                }

                "Serviços" -> {
                    val listaServicos = lista as? List<Servico> ?: emptyList()
                    ListarServicos(modifier = Modifier, listaServicos, context)
                }
            }

        }
    }
    if (showFilterDialog) {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp // Altura total da tela em dp
        val height: Dp
        if(tipoFiltro == "Oficinas"){
            height = (screenHeight * 0.52).dp // 50% da altura da tela
        } else {
            height = (screenHeight * 0.36).dp
        }

        val isCarroSelected = remember { mutableStateOf(false) }
        val isMotoSelected = remember { mutableStateOf(false) }
        val isCaminhaoSelected = remember { mutableStateOf(false) }
        val isOnibusSelected = remember { mutableStateOf(false) }
        val tipoVeiculoSelecionado = remember { mutableListOf<String>() }

        val isCombustaoSelected = remember { mutableStateOf(false) }
        val isEletricoSelected = remember { mutableStateOf(false) }
        val isHibridoSelected = remember { mutableStateOf(false) }
        val tipoPropulsaoSelecionada = remember { mutableListOf<String>() }

        val sliderValue = remember { mutableStateOf(0f) }

        Dialog(
            onDismissRequest = { showFilterDialog = false },
        ) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .size(450.dp, height)
                    .background(Color. White),
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth().padding(10.dp),
                ) {
                    Column(
                        Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.label_filtro),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = VerdeBuscar
                        )
                        Text(
                            text = stringResource(R.string.label_subtituloFiltro),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontSize = 14.sp,
                            color = (Color(170, 170, 170)),
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                        if (tipoFiltro == "Oficinas") {
                            Column {
                                Text(
                                    text = stringResource(R.string.label_tipoVeiculo),
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                                Row(
                                    Modifier
                                        .horizontalScroll(rememberScrollState()) // Adiciona o comportamento de scroll horizontal
                                        .padding(8.dp)
                                        .padding(bottom = 20.dp)
                                        .width(530.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    CardFiltro(
                                        "Carros",
                                        painterResource(R.mipmap.icon_carro),
                                        "Icone de Carro",
                                        Modifier.width(120.dp),
                                        isCarroSelected.value,
                                        onclick = {
                                            isCarroSelected.value = !isCarroSelected.value
                                            if (isCarroSelected.value) {
                                                tipoVeiculoSelecionado.add("carro")
                                            } else {
                                                tipoVeiculoSelecionado.remove("carro")
                                            }
                                        }
                                    )
                                    CardFiltro(
                                        "Motos",
                                        painterResource(R.mipmap.icon_moto),
                                        "Icone de Moto",
                                        Modifier.width(120.dp),
                                        isMotoSelected.value,
                                        onclick = {
                                            isMotoSelected.value = !isMotoSelected.value
                                            if (isMotoSelected.value) {
                                                tipoVeiculoSelecionado.add("moto")
                                            } else {
                                                tipoVeiculoSelecionado.remove("moto")
                                            }
                                        }
                                    )
                                    CardFiltro(
                                        "Caminhão",
                                        painterResource(R.mipmap.icon_caminhao),
                                        "Icone de Caminhão",
                                        Modifier.width(140.dp),
                                        isCaminhaoSelected.value,
                                        onclick = {
                                            isCaminhaoSelected.value = !isCaminhaoSelected.value
                                            if (isCaminhaoSelected.value) {
                                                tipoVeiculoSelecionado.add("caminhao")
                                            } else {
                                                tipoVeiculoSelecionado.remove("caminhao")
                                            }
                                        }
                                    )
                                    CardFiltro(
                                        "Ônibus",
                                        painterResource(R.mipmap.icon_onibus),
                                        "Icone de Ônibus",
                                        Modifier.width(120.dp),
                                        isOnibusSelected.value,
                                        onclick = {
                                            isOnibusSelected.value = !isOnibusSelected.value
                                            if (isOnibusSelected.value) {
                                                tipoVeiculoSelecionado.add("onibus")
                                            } else {
                                                tipoVeiculoSelecionado.remove("onibus")
                                            }
                                        }
                                    )
                                }
                            }
                            Column {
                                Text(
                                    text = stringResource(R.string.label_tipoVeiculo),
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                                Row(
                                    Modifier
                                        .horizontalScroll(rememberScrollState()) // Adiciona o comportamento de scroll horizontal
                                        .padding(8.dp)
                                        .padding(bottom = 20.dp)
                                        .width(420.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    CardFiltro(
                                        "Combustão",
                                        painterResource(R.mipmap.icon_combustivel),
                                        "Icone de Combustão",
                                        Modifier.width(140.dp),
                                        isCombustaoSelected.value,
                                        onclick = {
                                            isCombustaoSelected.value = !isCombustaoSelected.value
                                            if (isCombustaoSelected.value) {
                                                tipoPropulsaoSelecionada.add("combustão")
                                            } else {
                                                tipoVeiculoSelecionado.remove("combustão")
                                            }
                                        }
                                    )
                                    CardFiltro(
                                        "Elétricos",
                                        painterResource(R.mipmap.icon_eletricos),
                                        "Icone de Elétricos",
                                        Modifier.width(120.dp),
                                        isEletricoSelected.value,
                                        onclick = {
                                            isEletricoSelected.value = !isEletricoSelected.value
                                            if (isEletricoSelected.value) {
                                                tipoPropulsaoSelecionada.add("Elétrico")
                                            } else {
                                                tipoPropulsaoSelecionada.remove("Elétrico")
                                            }
                                        }
                                    )
                                    CardFiltro(
                                        "Híbridos",
                                        painterResource(R.mipmap.icon_hibridos),
                                        "Icone de Híbridos",
                                        Modifier.width(130.dp),
                                        isHibridoSelected.value,
                                        onclick = {
                                            isHibridoSelected.value = !isHibridoSelected.value
                                            if (isHibridoSelected.value) {
                                                tipoPropulsaoSelecionada.add("Hibrido")
                                            } else {
                                                tipoPropulsaoSelecionada.remove("Hibrido")
                                            }
                                        }
                                    )
                                }
                            }
                        } else {
                            Column {
                                Text(
                                    text = stringResource(R.string.label_valor),
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Slider(
                                    value = sliderValue.value,
                                    onValueChange = { newValue ->
                                        sliderValue.value = newValue
                                    },
                                    valueRange = 0f..1000f,  // Definindo o intervalo do valor do Slider
                                    steps = 1000,  // Definindo o número de divisões no Slider
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = SliderDefaults.colors(
                                        thumbColor = VerdeBuscar,  // Cor do polegar do slider
                                        activeTrackColor = VerdeBuscar,  // Cor da barra ativa do slider
                                        inactiveTrackColor = Color.Gray.copy(alpha = 0.3f)  // Cor da barra inativa do slider
                                    )
                                )
                                Text(
                                    text = "Preço selecionado: R$ ${sliderValue.value.toInt()}",
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 8.dp, bottom = 10.dp)
                                )
                            }
                        }
                        Column {
                            Row(
                                Modifier.padding(bottom = 5.dp)
                            ) {
                                Button(
                                    onClick = {
                                        // Reset all the selected filters and values
                                        isCarroSelected.value = false
                                        isMotoSelected.value = false
                                        isCaminhaoSelected.value = false
                                        isOnibusSelected.value = false
                                        tipoVeiculoSelecionado.clear()

                                        isCombustaoSelected.value = false
                                        isEletricoSelected.value = false
                                        isHibridoSelected.value = false
                                        tipoPropulsaoSelecionada.clear()

                                        sliderValue.value = 0f
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .border(2.dp, VerdeBuscar, RoundedCornerShape(8.dp)),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White
                                    )
                                ) {
                                    Text(
                                        text = "Limpar Filtro",
                                        color = VerdeBuscar,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Button(
                                    onClick = {
                                        showFilterDialog = false
                                    }, // Ação do botão Cancelar
                                    modifier = Modifier.weight(1f), // Para os botões ficarem do mesmo tamanho
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = VerdeBuscar
                                    )
                                ) {
                                    Text(
                                        text = "Cancelar",
                                        color = Color.White,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Button(
                                    onClick = {
                                        // Convertendo as listas para ArrayList
                                        if (tipoFiltro == "Oficinas") {
                                            val veiculosSelecionados =
                                                ArrayList(tipoVeiculoSelecionado)
                                            val propulsoesSelecionadas =
                                                ArrayList(tipoPropulsaoSelecionada)

                                            // Criando a Intent e passando os extras
                                            val intent = Intent(
                                                context,
                                                TelaPesquisarOficinasActivity::class.java
                                            ).apply {
                                                putStringArrayListExtra(
                                                    "VEICULOS_SELECIONADOS",
                                                    veiculosSelecionados
                                                )
                                                putStringArrayListExtra(
                                                    "PROPULSOES_SELECIONADAS",
                                                    propulsoesSelecionadas
                                                )
                                            }
                                            context.startActivity(intent)

                                        } else if (tipoFiltro == "Peças") {
                                            val intent = Intent(
                                                context,
                                                TelaPesquisarPecasActivity::class.java
                                            ).apply {
                                                putExtra("SLIDER_VALUE", sliderValue.value)
                                            }
                                            context.startActivity(intent)
                                        } else if (tipoFiltro == "Serviços"){
                                            val intent = Intent(
                                                context,
                                                TelaPesquisarServicosActivity::class.java
                                            ).apply {
                                                putExtra("SLIDER_VALUE", sliderValue.value)
                                            }
                                            context.startActivity(intent)
                                        }
                                    },
                                    modifier = Modifier.weight(1f), // Para os botões ficarem do mesmo tamanho
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = VerdeBuscar
                                    )
                                ) {
                                    Text(
                                        text = "Filtrar",
                                        color = Color.White,
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
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

@Composable
fun ListarOS(ordensDeServico: List<OrdemServico>) {
    val clipboardManager = LocalClipboardManager.current
    Column(
        Modifier.padding(top = 36.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        for (os in ordensDeServico) {
            val color = when (os.status) {
                "EM ABERTO" -> Color(253, 216, 53)
                "CONCLUIDO" -> Color(0, 176, 80)
                else -> Color(253, 216, 53)
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(240, 240, 240)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "#${os.token}",
                        fontSize = 22.sp,
                        fontFamily = PRODUCT_SANS_FAMILY,
                        fontWeight = FontWeight.Bold,
                        color = VerdeBuscar,
                        modifier = Modifier.clickable {
                            clipboardManager.setText(AnnotatedString(os.token))
                        }
                    )
                    Text(
                        text = os.placaVeiculo,
                        color = Color(130, 130, 130)
                    )
                }
                Row(
                    Modifier.padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = color)
                    )
                    Text(
                        text = os.status,
                        fontSize = 12.sp,
                        fontFamily = PRODUCT_SANS_FAMILY
                    )
                }
            }
        }
    }
}

@Composable
fun CustomInputPerfil(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPasswordField: Boolean = false,
    isEnabled: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = VerdeBuscar,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = VerdeBuscar,
            unfocusedLabelColor = VerdeBuscar,
            focusedBorderColor = VerdeBuscar,
            unfocusedBorderColor = Color.Transparent,
            unfocusedContainerColor = Color(222, 222, 222)
        ),
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        textStyle = TextStyle(fontSize = 16.sp),
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
        enabled = isEnabled
    )
}

@Composable
fun CardAgenda(dia: String, mes: String) {
    Column(
        Modifier
            .width(160.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(VerdeBuscar)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = dia,
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mes,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                Modifier.padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.mipmap.icon_lixeira),
                    contentDescription = "Icone de excluir(lixeira)",
                    Modifier
                        .size(16.dp)
                )
            }
        }
        Row {
            Column {
                Text(
                    text = stringResource(R.string.label_exemploOficina),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = stringResource(R.string.label_exemploHorario),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = PRODUCT_SANS_FAMILY,
                )
            }
        }
    }
}

@Composable
fun ListarAgenda() {
    val listaCompromissos = listOf(
        Pair(11, "SET"),
        Pair(19, "OUT"),
        Pair(7, "NOV")
    )

    val groupedCompromissos = listaCompromissos.chunked(2)
    Column(
        Modifier.padding(top = 0.dp),
    ) {
        for (grupo in groupedCompromissos) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                for (compromisso in grupo) {
                    CardAgenda(compromisso.first.toString(), compromisso.second)
                }
            }
        }
    }
}

@Composable
fun NavigationBar(
    context: Context, // Context necessário para iniciar Activities
    selectedTabIndex: Int // Tab atualmente selecionada
) {
    BottomNavigation(
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            )
        ),
        backgroundColor = Color(238, 238, 238),
        contentColor = Color(59, 86, 60)
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.mipmap.icon_home_nocolor),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icone de Home"
                )
            },
            selected = selectedTabIndex == 0,
            onClick = {
                val intent = Intent(context, TelaInicialActivity::class.java)
                context.startActivity(intent)
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.mipmap.icon_alert_nocolor),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icone de Alerta"
                )
            },
            selected = selectedTabIndex == 1,
            onClick = {
                val intent = Intent(context, TelaSOSActivity::class.java)
                context.startActivity(intent)
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.mipmap.icon_order_nocolor),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icone de Ordem de Serviço"
                )
            },
            selected = selectedTabIndex == 2,
            onClick = {
                val intent = Intent(context, TelaConsultaOSActivity::class.java)
                intent.putExtra("SELECTED_TAB_INDEX", 2) // Envia a aba selecionada
                context.startActivity(intent)
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.mipmap.icon_profile_nocolor),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icone de Perfil"
                )
            },
            selected = selectedTabIndex == 3,
            onClick = {
                val intent = Intent(context, TelaPerfilActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(sessaoUsuario: SessaoUsuario, context: Context) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                context,
                selectedTabIndex = selectedTabIndex
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = selectedTabIndex == 0,
                enter = slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut()
            ) {
                TelaInicial(
                    selectedTabIndex = selectedTabIndex,
                    sessaoUsuario = sessaoUsuario
                )
            }

            AnimatedVisibility(
                visible = selectedTabIndex == 1,
                enter = slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut()
            ) {
                TelaSOS(
                    selectedTabIndex = selectedTabIndex,
                    sessaoUsuario = sessaoUsuario
                )
            }

            AnimatedVisibility(
                visible = selectedTabIndex == 2,
                enter = slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut()
            ) {
                TelaConsultaOS(
                    selectedTabIndex = selectedTabIndex,
                    sessaoUsuario = sessaoUsuario
                )
            }

            AnimatedVisibility(
                visible = selectedTabIndex == 3,
                enter = slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut()
            ) {
                TelaPerfil(
                    selectedTabIndex = selectedTabIndex,
                    sessaoUsuario = sessaoUsuario
                )
            }
        }
    }
}

@Composable
fun CardSOS(idUsuario: Int, oficina: OficinaDTO) {
    val viewModel: SosViewModel = viewModel()
    val context = LocalContext.current
    val listaOficinasFavoritas = viewModel.getOficinasFavoritas()
    var liked by remember { mutableStateOf(false) }
    listaOficinasFavoritas.forEach { oficinaFav ->
        if (oficinaFav.oficina.id == oficina.id && oficinaFav.usuario.idUsuario == idUsuario) {
            liked = true;
        }
    }
    val hasNumeroWhatsapp =
        oficina.informacoesOficina != null && oficina.informacoesOficina.whatsapp.isNotBlank()

    LaunchedEffect(Unit) {
        viewModel.listarOficinasFavoritas(idUsuario)
    }

    val text = if (oficina.distance != null) {
       formatarDistancia(oficina.distance!!)
    } else {
        "Distância indisponível"
    }

    var imageUrl =
        "https://blog.engecass.com.br/wp-content/uploads/2023/09/inovacoes-e-tendencias-para-auto-centers-e-oficinas-mecanicas.jpg" // Altere para o ID correto da imagem padrão

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val boxHeight = (screenHeight * 0.75f)

    if (oficina.logoUrl.isNotBlank()) {
        imageUrl = oficina.logoUrl;
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxHeight)
            .clip(RoundedCornerShape(40.dp))
            .background(color = Color(240, 240, 240))
    ) {
        // Imagem de fundo do card
        AsyncImage(
            model = imageUrl, // URL da imagem
            contentDescription = "Logo da ${oficina.nome}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp) // Ajuste o tamanho conforme necessário
        )

        // Conteúdo do Card sobreposto
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                Modifier
                    .weight(0.60f)
                    .fillMaxSize()
            ) {
                Button(
                    onClick = {
                        if (liked) {
                            viewModel.removeOficina(idUsuario, oficina.id)
                            liked = false;
                        } else {
                            viewModel.favoritarOficina(idUsuario, oficina.id)
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

            Column(
                Modifier
                    .weight(0.55f)
                    .fillMaxSize()
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 5.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        Modifier
                            .width(50.dp)
                            .height(50.dp)
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
                                .clickable {
                                    val logradouro = oficina.logradouro
                                    val numero = oficina.numero
                                    val bairro = oficina.bairro
                                    val cidade = oficina.cidade
                                    val cep = oficina.cep

                                    val endereco =
                                        "$logradouro, $numero, $bairro, $cidade, CEP $cep"
                                    Log.i("address", endereco)
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse(
                                            "https://www.google.com/maps/dir/?api=1&destination=${
                                                Uri.encode(endereco)
                                            }"
                                        )
                                    }
                                    context.startActivity(intent)
                                }
                        )
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 5.dp, bottom = 5.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (hasNumeroWhatsapp) {
                        Row(
                            Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .clip(RoundedCornerShape(100.dp))
                                .background(color = Color(230, 230, 230)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val mensagem =
                                "Olá, encontrei a oficina pelo SOS do Buscar, está disponível para atender?"
                            Image(
                                painter = painterResource(R.mipmap.icon_whatsapp),
                                contentDescription = "Imagem de WhatsApp",
                                Modifier
                                    .size(20.dp)
                                    .clickable {
                                        val numeroTelefone = oficina.informacoesOficina!!.whatsapp

                                        val intent = Intent(Intent.ACTION_VIEW).apply {
                                            data =
                                                Uri.parse("https://wa.me/+55$numeroTelefone?text=$mensagem")
                                        }
                                        context.startActivity(intent)
                                    }
                            )
                        }
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .weight(2.5f)
                        .clip(RoundedCornerShape(40.dp))
                        .background(Color(240, 240, 240, 200))
                ) {
                    Column(
                        Modifier
                            .weight(0.8f)
                            .padding(5.dp)
                            .fillMaxSize()
                    ) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp, start = 20.dp)
                        ) {
                            Text(
                                text = oficina.nome,
                                fontSize = 30.sp,
                                fontFamily = PRODUCT_SANS_FAMILY,
                                fontWeight = FontWeight.Bold,
                                color = VerdeBuscar
                            )
                            Text(
                                text = text,
                                fontSize = 16.sp,
                                fontFamily = PRODUCT_SANS_FAMILY,
                                color = if (oficina.distance != null) Color(
                                    50,
                                    50,
                                    50
                                ) else Color.Gray,
                                modifier = Modifier.padding(top = 15.dp)
                            )

                            Row(
                                Modifier.padding(top = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_local),
                                    contentDescription = "Imagem de indicação de local",
                                    Modifier.size(20.dp)
                                )
                                Text(
                                    text = oficina.logradouro + ", " + oficina.numero + " - " + oficina.bairro,
                                    color = Color(50, 50, 50),
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                        }
                    }
                    Column(
                        Modifier
                            .weight(0.25f)
                            .fillMaxSize()
                            .padding(top = 18.dp, end = 5.dp)
                    ) {
                        Row {
                            Image(
                                painter = painterResource(R.mipmap.icon_stars),
                                contentDescription = "Ícone de Avaliação (Estrela)",
                                Modifier.size(16.dp)
                            )
                            Text(
                                text = oficina.mediaAvaliacao?.nota?.toString()
                                    ?: "N/A",  // Caso não tenha nota, exibe "N/A"
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

@Composable
fun CardFidelidade() {
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(Color(235, 235, 235))
    ) {
        Column(
            Modifier
                .padding(20.dp)
        ) {
            Row() {
                Text(
                    text = stringResource(R.string.label_tituloOficinas),
                    fontSize = 18.sp,
                    fontFamily = PRODUCT_SANS_FAMILY,
                    color = VerdeBuscar,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.label_desconto),
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontSize = 14.sp,
                    color = Color(70, 70, 70)
                )
            }
            Row {
                Column {
                    Row(
                        Modifier
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(5.dp)
                            .fillMaxWidth()
                            .background(Color(200, 200, 200))
                    ) {}
                    Row(
                        Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = stringResource(R.string.label_exemploVisitas),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontSize = 14.sp,
                            color = Color(100, 100, 100)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResultDialog(title: String, text: String, icon: ImageVector, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        icon = {
            Image(
                painter = painterResource(id = R.drawable.logo_buscar),
                contentDescription = "Example Icon"
            )
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss()
                    Log.i("api", "ondismiss")
                }
            ) {
                Text("Ok", color = VerdeBuscar)
            }
        },
    )
}

@Composable
fun MotionLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = VerdeBuscar,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable
fun ListarPecasOs(lista: List<OrdemServico.OSProduto>) {

    for (peca in lista) {
        Row(
            Modifier
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(12.dp))
                .height(60.dp)
                .fillMaxWidth()
                .background(Color(240, 240, 240)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                Modifier
                    .padding(horizontal = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.mipmap.icon_chave_filtro),
                    contentDescription = "Icone de chave de boca",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(25.dp)
                )
                Column {
                    Text(
                        text = peca.nome,
                        fontSize = 14.sp,
                        fontFamily = PRODUCT_SANS_FAMILY,
                        fontWeight = FontWeight.Bold,
                        color = Color(80, 80, 80)
                    )
                    Text(
                        text = peca.quantidade.toString() + " Unidade(s)",
                        fontSize = 10.sp,
                        fontFamily = PRODUCT_SANS_FAMILY,
                        fontWeight = FontWeight.Bold,
                        color = Color(80, 80, 80)
                    )
                }
            }
            Text(
                text = "R$${peca.valor}",
                modifier = Modifier.padding(end = 20.dp)
            )
        }
    }
}

@Composable
fun ListarServicosOs(lista: List<OrdemServico.OSServico>) {

    for (servico in lista) {
        Row(
            Modifier
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(12.dp))
                .height(60.dp)
                .fillMaxWidth()
                .background(Color(240, 240, 240)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                Modifier
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.mipmap.icon_chave_filtro),
                    contentDescription = "Icone de chave de boca",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(25.dp)
                )
                Column {
                    Text(
                        text = servico.nome,
                        fontSize = 14.sp,
                        fontFamily = PRODUCT_SANS_FAMILY,
                        fontWeight = FontWeight.Bold,
                        color = Color(80, 80, 80)
                    )
                }
            }
            Text(
                text = "R$" + servico.valor,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
    }
}

@Composable
fun FotoUsuario(nomeUsuario: String, logoUsuario: String){
    if(logoUsuario.isNotEmpty() && logoUsuario != "null"){
        Column (
            Modifier
                .clip(RoundedCornerShape(50.dp))
                .width(50.dp)
                .height(50.dp)
        ) {
            AsyncImage(
                model = logoUsuario, // URL da imagem
                contentDescription = "Logo do usuário",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp) // Ajuste o tamanho conforme necessário
            )
        }
    } else {
        Column (
            Modifier
                .clip(RoundedCornerShape(50.dp))
                .width(50.dp)
                .height(50.dp)
                .background(Color(230,230,230)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = nomeUsuario[0].toString(),
                fontFamily = PRODUCT_SANS_FAMILY,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color(70,70,70)
            )
        }
    }
}

@Composable
fun FotoUsuarioSessao(sessaoUsuario: SessaoUsuario){
    if(!sessaoUsuario.fotoUrl.isNullOrEmpty()){
        Column (
            Modifier
                .clip(RoundedCornerShape(50.dp))
                .width(50.dp)
                .height(50.dp)
        ) {
            AsyncImage(
                model = sessaoUsuario.fotoUrl, // URL da imagem
                contentDescription = "Logo do usuário",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp) // Ajuste o tamanho conforme necessário
            )
        }
    } else {
        Column (
            Modifier
                .clip(RoundedCornerShape(50.dp))
                .width(50.dp)
                .height(50.dp)
                .background(Color(230,230,230)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = sessaoUsuario.nome[0].toString(),
                fontFamily = PRODUCT_SANS_FAMILY,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color(70,70,70)
            )
        }
    }
}


private fun formatarDistancia(distancia: Int): String {
    return if (distancia < 1000) {
        "$distancia Metros"
    } else {
        val km = distancia / 1000.0 // Converte para quilômetros com decimal
        String.format("%.1f km", km) // Formata com uma casa decimal
    }
}
