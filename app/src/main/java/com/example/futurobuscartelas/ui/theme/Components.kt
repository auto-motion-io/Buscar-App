package com.example.futurobuscartelas.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.futurobuscartelas.R

@Composable
fun AddAvaliacao(usuario: String, estrelas: Int, mensagem: String) {
    var estrelasAdd: Int = 0;

    Row(
        Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(238, 238, 238))
    ) {
        Column(
            Modifier.padding(start = 20.dp, top = 20.dp, bottom = 24.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Image(
                        painterResource(id = R.mipmap.icon_user),
                        contentDescription = "Imagem de Usuário",
                        Modifier.size(56.dp)
                    )
                }
                Column(
                    Modifier.padding(start = 10.dp)
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
                            var estrelasBranco: Int = 5 - estrelasAdd;

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
                Column(
                    Modifier.align(Alignment.Bottom)
                ) {
                    Text(
                        text = "23/04/2024 - 13:55",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 12.sp,
                        color = Color(190, 190, 190),
                        fontFamily = PRODUCT_SANS_FAMILY
                    )
                }
            }
            Row(
                Modifier
                    .padding(end = 22.dp, top = 10.dp)
                    .height(130.dp)
            ) {
                Text(
                    text = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para preencher os espaços de texto em publicações para testar e ajustar aspectos visuais antes de utilizar conteúdo real.",
                    fontSize = 12.sp,
                    fontFamily = PRODUCT_SANS_FAMILY
                )
            }
        }
    }
}

@Composable
fun CheckSemana(listaDias: List<Boolean>){
    val diasSemana = listOf("D", "S", "T", "Q", "Q", "S", "S");

    Row(
        Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for(i in diasSemana.indices){
            Column(
                Modifier.padding(start = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(listaDias[i]){
                    CheckOn()
                } else{
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
fun CheckOn(){
    Image(
        painterResource(id = R.mipmap.icon_check),
        contentDescription = "Icone de Check",
        Modifier.size(24.dp)
    )
}

@Composable
fun CheckOff(){
    Image(
        painterResource(id = R.mipmap.icon_check_semcor),
        contentDescription = "Icone de Check sem cor",
        Modifier.size(24.dp)
    )
}

@Composable
fun ListarServicos(modifier: Modifier) {
    val listaServicos = listOf("Troca de Óleo", "Troca de Pneu", "Alinhamento", "Balanceamento", "Teste")

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
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color(240, 239, 236))
                        ) {}
                        Text(
                            text = servico,
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

@Composable
fun ListarPecas(modifier: Modifier) {
    val listaPecas = listOf("Pneu", "Farois", "Bateria", "Freios", "Filtros", "Correias")

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
                                .background(color = Color(240, 239, 236))
                        ) {}
                        Text(
                            text = peca,
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

@Composable
fun AddCategoria(categoria: String){
    Column (
        Modifier.width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(46.dp))
                .background(color = Color(241, 240, 237))
        ) {}
        Row (
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
fun ListarFavoritos(modifier: Modifier) {
    val listaFavoritos = listOf("Favorito 1","Favorito 2","Favorito 3","Favorito 4","Favorito 5","Favorito 6")

    // Agrupa os serviços em pares
    val groupedFavoritos = listaFavoritos.chunked(2)

    Column(modifier = modifier) {
        for (grupo in groupedFavoritos) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (servico in grupo) {
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
                                .background(color = Color(240, 239, 236))
                        ) {}
                        Text(
                            text = servico,
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

@Composable
fun BotaoPesquisa(fundo: Boolean){
    if(fundo){
        Button(onClick = { /*TODO*/ },
            Modifier
                .padding(start = 5.dp, top = 5.dp)
                .size(48.dp)
                .clip(RoundedCornerShape(220.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeBuscar,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White),
            contentPadding = PaddingValues(0.dp)
        ) {
            Image(
                painter = painterResource(R.mipmap.icon_lupa_white),
                contentDescription = "Icone de Lupa Branca",
                Modifier.size(16.dp)
            )
        }
    } else{
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .padding(0.dp)
                .width(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0,0,0, 0)
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
fun CardFiltro(tituloCard: String, imagePainter: Painter, descricaoConteudo: String, modifier: Modifier, fundo: Boolean, onclick:()->Unit){
    if(fundo){
        Button (
            onClick = { onclick() },
            modifier
                .height(50.dp)
                .clip(RoundedCornerShape(220.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeBuscar,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White),
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
    }
    else{
        Button (
            onClick = { onclick() },
            modifier
                .height(50.dp)
                .clip(RoundedCornerShape(220.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0, 0, 0, 0),
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White),
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
fun ListarOficinas(modifier: Modifier) {
    val listaOficinas = listOf("Fast Motors","Fast Motors","Fast Motors","Fast Motors","Fast Motors","Fast Motors")

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
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color(240, 239, 236, 210))
                        ) {}

                        Column (
                            Modifier.padding(bottom = 10.dp)
                        ) {
                            Row (
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp, top = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = oficina,
                                    fontSize = 16.sp,
                                    color = Color(59, 86, 60),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 5.dp),
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                )
                                Row (
                                    Modifier.padding(top = 7.dp)
                                ){
                                    Image(
                                        painter = painterResource(R.mipmap.icon_stars),
                                        contentDescription = "Imagem de Estrela",
                                        Modifier
                                            .padding(end = 2.dp)
                                            .size(12.dp)
                                    )
                                    Text(
                                        text = "5.0",
                                        fontSize = 12.sp,
                                        fontFamily = PRODUCT_SANS_FAMILY
                                    )
                                }
                            }
                            Row (
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp),
                            ) {
                                Text(
                                    text = "$$$",
                                    color = Color(59, 86, 60),
                                    fontSize = 14.sp,
                                    fontFamily = PRODUCT_SANS_FAMILY
                                )
                                Text(
                                    text = "$",
                                    color = Color(190, 190, 190),
                                    fontSize = 14.sp, fontFamily = PRODUCT_SANS_FAMILY
                                )
                            }
                            Row (
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp, top = 6.dp),
                            ) {
                                Image(
                                    painter = painterResource(R.mipmap.icon_local),
                                    contentDescription = "Icone de ponto de localização",
                                    Modifier.size(11.dp)
                                )
                                Row (
                                    Modifier.padding(start = 4.dp)
                                ) {
                                    Text(
                                        text = "Av. Miguel Ferreira de Melo. 500",
                                        fontFamily = PRODUCT_SANS_FAMILY,
                                        fontSize = 10.sp,
                                        color = Color(0,0,0, 180)
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
fun TelaBaseOSP(navController: NavController, titulo: String){
    Column (
        Modifier
            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Row(){
            ArrowBackButton(navController = navController)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 35.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(R.mipmap.logo_buscar),
                    contentDescription = "Logo do Buscar!"
                )
            }
        }
        Row (
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
            Row () {
                BotaoPesquisa(false)
                Button(
                    onClick = { /*TODO*/ },
                    Modifier
                        .padding(0.dp)
                        .width(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0,0,0, 0)
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
            Modifier.padding(top = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CardFiltro(
                "Motos",
                painterResource(R.mipmap.icon_moto_branco),
                "Icone de Moto",
                Modifier.width(120.dp),
                true,
                onclick = {}
            )
            CardFiltro(
                "Combustão",
                painterResource(R.mipmap.icon_combustao),
                "Icone de Galão de Gasolina",
                Modifier.width(140.dp),
                true,
                onclick = {}
            )
        }
        Row (
            Modifier
                .padding(top = 20f.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ListarOficinas(modifier = Modifier)
        }
    }
}

@Composable
fun ListarOS(){
    val listaOs = listOf("01258", "01258", "01258")

    Column (
        Modifier.padding(top = 36.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        for (os in listaOs){
            Row (
                Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(240, 240, 240)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "#$os",
                        fontSize = 22.sp,
                        fontFamily = PRODUCT_SANS_FAMILY,
                        fontWeight = FontWeight.Bold,
                        color = VerdeBuscar
                    )
                    Text(
                        text = "CMM-5861",
                        color = Color(130,130,130)
                    )
                }
                Row (
                    Modifier.padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = Color(253, 216, 53))
                    )
                    Text(
                        text = "Em andamento",
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
    isPasswordField: Boolean = false
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
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None
    )
}