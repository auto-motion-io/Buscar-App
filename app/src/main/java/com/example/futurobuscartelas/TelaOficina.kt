package com.example.futurobuscartelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.ui.theme.*

class TelaOficinaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FuturoBuscarTelasTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        Column {
                            Column (
                                Modifier
                                    .align(Alignment.End)
                                    .padding(bottom = 5.dp, end = 14.dp)
                                    .height(290.dp),
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                Row (
                                    Modifier
                                        .width(60.dp)
                                        .height(60.dp)
                                        .clip(RoundedCornerShape(80.dp))
                                        .background(color = Color(235, 235, 235)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painterResource(id = R.mipmap.icon_calendario_colorido),
                                        contentDescription = "Icone de calendário",
                                        Modifier
                                            .size(20.dp)
                                    )
                                }
                                Row (
                                    Modifier
                                        .width(60.dp)
                                        .height(60.dp)
                                        .clip(RoundedCornerShape(80.dp))
                                        .background(color = Color(235, 235, 235)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painterResource(id = R.mipmap.icon_stars),
                                        contentDescription = "Icone de Favorito (Estrela)",
                                        Modifier
                                            .size(20.dp)
                                    )
                                }
                                Row (
                                    Modifier
                                        .width(60.dp)
                                        .height(60.dp)
                                        .clip(RoundedCornerShape(80.dp))
                                        .background(color = Color(235, 235, 235)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painterResource(id = R.mipmap.icon_local),
                                        contentDescription = "Icone de identificação de local",
                                        Modifier
                                            .size(20.dp)
                                    )
                                }
                                Row (
                                    Modifier
                                        .width(60.dp)
                                        .height(60.dp)
                                        .clip(RoundedCornerShape(80.dp))
                                        .background(color = Color(235, 235, 235)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painterResource(id = R.mipmap.icon_whatsapp),
                                        contentDescription = "Icone do Whatsapp",
                                        Modifier
                                            .size(20.dp)
                                    )
                                }
                            }
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(color = Color(238, 238, 238))
                                    .height(60.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painterResource(
                                    id = R.mipmap.icon_home),
                                    contentDescription = "Icone Home",
                                    Modifier.size(20.dp)
                                )
                                Image(painterResource(
                                    id = R.mipmap.icon_alerta),
                                    contentDescription = "Icone Alerta",
                                    Modifier.size(26.dp)
                                )
                                Image(painterResource(
                                    id = R.mipmap.icon_ordem),
                                    contentDescription = "Icone Ordem",
                                    Modifier.size(20.dp)
                                )
                                Image(painterResource(
                                    id = R.mipmap.icon_perfil),
                                    contentDescription = "Icone Perfil",
                                    Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    OficinaScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun OficinaScreen(name: String, modifier: Modifier = Modifier) {
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
            Row(Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Fast Motors",
                    color = Color(59, 86, 60),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
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
                        color = Color(30, 30, 30)
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
                    Row (
                        modifier = Modifier
                            .width(220.dp)
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(2.dp, Color(59, 86, 60)),
                                    shape = RoundedCornerShape(26.dp)
                                )
                                .padding(horizontal = 18.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column (Modifier.padding(top = 4.dp, end = 6.dp)) {
                                Image(
                                    painter = painterResource(id = R.mipmap.icon_carro),
                                    contentDescription = "Icone de Carro",
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Text(text = "Carros", fontSize = 14.sp,
                                color = Color(59, 86, 60))
                        }
                        Row (
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(2.dp, Color(59, 86, 60)),
                                    shape = RoundedCornerShape(26.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                                .width(80.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        )  {
                            Column (Modifier.padding( end = 6.dp, top = 2.dp)) {
                                Image(
                                    painter = painterResource(id = R.mipmap.icon_moto),
                                    contentDescription = "Icone de Moto",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Text(text = "Motos", fontSize = 14.sp,
                                color = Color(59, 86, 60))
                        }
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(2.dp, Color(59, 86, 60)),
                                    shape = RoundedCornerShape(26.dp)
                                )
                                .padding(horizontal = 18.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column (Modifier.padding(top = 4.dp, end = 6.dp)) {
                                Image(
                                    painter = painterResource(id = R.mipmap.icon_combustivel),
                                    contentDescription = "Icone de Combustivel",
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Text(text = "Combustão", fontSize = 14.sp,
                                color = Color(59, 86, 60))
                        }
                    }
                }
            }

            // ---------

            Row (
                Modifier.padding(top = 20.dp)
            ) {
                Column (Modifier.padding(end = 20.dp)) {
                    Image(
                        painterResource(id = R.mipmap.icon_relogio),
                        contentDescription = "Icone de Relógio",
                        Modifier.size(25.dp)
                    )
                }
                Column (Modifier.padding(top = 1.dp)) {
                    Text(
                        text = "9H00 ás 16H00",
                        fontSize = 14.sp,
                        color = Color(50, 50, 50, 240),
                    )
                }
            }

            // ---------

            Row (Modifier.padding(top = 20.dp)) {
                Column (
                    Modifier
                        .padding(start = 3.dp)
                        .padding(top = 15.dp)) {
                    Image(
                        painterResource(id = R.mipmap.icon_calendario),
                        contentDescription = "Icone de Calendário",
                        Modifier.size(20.dp)
                    )
                }
                Row (
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column (
                        Modifier.padding(start = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.mipmap.icon_check_semcor),
                            contentDescription = "Icone de Check sem cor",
                            Modifier.size(24.dp)
                        )
                        Text(text = "D",
                            Modifier
                                .padding(4.dp),
                            color = Color(60, 60, 60),
                            fontSize = 14.sp
                        )
                    }
                    Column (
                        Modifier.padding(start = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.mipmap.icon_check),
                            contentDescription = "Icone de Check",
                            Modifier.size(24.dp)
                        )
                        Text(text = "S",
                            Modifier
                                .padding(4.dp),
                            color = Color(60, 60, 60),
                            fontSize = 14.sp
                        )
                    }
                    Column (
                        Modifier.padding(start = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.mipmap.icon_check),
                            contentDescription = "Icone de Check",
                            Modifier.size(24.dp)
                        )
                        Text(text = "T", Modifier
                            .padding(4.dp),
                            color = Color(60, 60, 60),
                            fontSize = 14.sp
                        )
                    }
                    Column (
                        Modifier.padding(start = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.mipmap.icon_check),
                            contentDescription = "Icone de Check",
                            Modifier.size(24.dp)
                        )
                        Text(text = "Q", Modifier
                            .padding(4.dp),
                            color = Color(60, 60, 60),
                            fontSize = 14.sp
                        )
                    }
                    Column (
                        Modifier.padding(start = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.mipmap.icon_check),
                            contentDescription = "Icone de Check",
                            Modifier.size(24.dp)
                        )
                        Text(text = "Q", Modifier
                            .padding(4.dp),
                            color = Color(60, 60, 60),
                            fontSize = 14.sp
                        )
                    }
                    Column (
                        Modifier.padding(start = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.mipmap.icon_check),
                            contentDescription = "Icone de Check",
                            Modifier.size(24.dp)
                        )
                        Text(text = "S", Modifier
                            .padding(4.dp),
                            color = Color(60, 60, 60),
                            fontSize = 14.sp
                        )
                    }
                    Column (
                        Modifier.padding(start = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.mipmap.icon_check_semcor),
                            contentDescription = "Icone de Check sem cor",
                            Modifier.size(24.dp)
                        )
                        Text(text = "S", Modifier
                            .padding(4.dp),
                            color = Color(60, 60, 60),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            //--------------
            Column (
                Modifier.padding(top = 20.dp)
            ) {
                Row {
                    Text(
                        text = "Serviços",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(59, 86, 60)
                    )
                }
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column (
                        Modifier
                            .weight(0.4f)
                            .padding(end = 14.dp)
                    ) {
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color(240, 239, 236))
                        ) {}
                        Text(
                            text = "Fast Motors",
                            color = Color(59, 86, 60),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                    Column (
                        Modifier
                            .weight(0.4f)
                            .padding(end = 14.dp)
                    ) {
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                                .padding(top = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color(240, 239, 236))
                        ) {}
                        Text(
                            text = "Fast Motors",
                            color = Color(59, 86, 60),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
            }
            Column (
                Modifier.padding(top = 20.dp)
            ) {
                Row {
                    Text(
                        text = "Peças",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(59, 86, 60)
                    )
                }
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column (
                        Modifier
                            .weight(0.4f)
                            .padding(end = 14.dp)
                    ) {
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                                .padding(top = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color(240, 239, 236))
                        ) {}
                        Text(
                            text = "Fast Motors",
                            color = Color(59, 86, 60),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                    Column (
                        Modifier
                            .weight(0.4f)
                            .padding(end = 14.dp)
                    ) {
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                                .padding(top = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color(240, 239, 236))
                        ) {}
                        Text(
                            text = "Fast Motors",
                            color = Color(59, 86, 60),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
            }

            Row (
                Modifier.padding(top = 30.dp)
            ) {
                Text(
                    text = "Avaliações",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(59, 86, 60),
                )
            }
            Row (
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(238, 238, 238))
            ) {
                Column (
                    Modifier.padding(start = 20.dp, top = 20.dp, bottom = 24.dp)
                ) {
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Image(
                                painterResource(id = R.mipmap.icon_user),
                                contentDescription = "Imagem de Usuário",
                                Modifier.size(56.dp)
                            )
                        }
                        Column (
                            Modifier.padding(start = 10.dp)
                        ) {
                            Row {
                                Text(
                                    text = "Marcos Gonzales",
                                    fontSize = 14.sp
                                )
                            }
                            Row (
                                Modifier
                                    .width(100.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Image(
                                    painterResource(id = R.mipmap.icon_stars),
                                    contentDescription = "Icone de Avaliação (Estrela)",
                                    Modifier.size(16.dp)
                                )
                                Image(
                                    painterResource(id = R.mipmap.icon_stars),
                                    contentDescription = "Icone de Avaliação (Estrela)",
                                    Modifier.size(16.dp)
                                )
                                Image(
                                    painterResource(id = R.mipmap.icon_stars),
                                    contentDescription = "Icone de Avaliação (Estrela)",
                                    Modifier.size(16.dp)
                                )
                                Image(
                                    painterResource(id = R.mipmap.icon_stars),
                                    contentDescription = "Icone de Avaliação (Estrela)",
                                    Modifier.size(16.dp)
                                )
                                Image(
                                    painterResource(id = R.mipmap.icon_stars),
                                    contentDescription = "Icone de Avaliação (Estrela)",
                                    Modifier.size(16.dp)
                                )
                            }
                        }
                        Column (
                            Modifier.align(Alignment.Bottom)
                        ) {
                            Text(
                                text = "23/04/2024 - 13:55",
                                modifier = Modifier.padding(bottom = 8.dp),
                                fontSize = 12.sp,
                                color = Color(190, 190, 190)
                            )
                        }
                    }
                    Row (
                        Modifier
                            .padding(end = 10.dp, top = 10.dp)
                            .height(130.dp)
                    ) {
                        Text(
                            text = "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para preencher os espaços de texto em publicações para testar e ajustar aspectos visuais antes de utilizar conteúdo real.",
                            fontSize = 12.sp
                        )
                    }
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
    FuturoBuscarTelasTheme {
        OficinaScreen("Android")
    }
}