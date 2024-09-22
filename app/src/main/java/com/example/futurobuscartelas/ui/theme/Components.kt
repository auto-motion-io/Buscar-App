package com.example.futurobuscartelas.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.R

@Composable
fun addAvaliacao(usuario: String, estrelas: Int, mensagem: String) {
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