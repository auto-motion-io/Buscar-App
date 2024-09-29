package com.example.futurobuscartelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.ui.theme.ArrowBackButton
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import com.example.futurobuscartelas.ui.theme.botaoPesquisa
import com.example.futurobuscartelas.ui.theme.cardFiltro
import com.example.futurobuscartelas.ui.theme.listarOficinas

@Composable
fun TelaPesquisarOficinas(navController: NavController, modifier: Modifier) {
    Column (Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp)) {
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
                text = stringResource(R.string.label_tituloOficinas),
                fontSize = 32.sp,
                color = Color(59, 86, 60),
                fontFamily = PRODUCT_SANS_FAMILY,
                fontWeight = FontWeight.Bold
            )
            Row () {
                botaoPesquisa(false)
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
            cardFiltro(
                "Motos",
                painterResource(R.mipmap.icon_moto_branco),
                "Icone de Moto",
                Modifier.width(120.dp)
            )
            cardFiltro(
                "Combustão",
                painterResource(R.mipmap.icon_combustao),
                "Icone de Galão de Gasolina",
                Modifier.width(140.dp)
            )
        }
        Row (
            Modifier
                .padding(top = 20f.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listarOficinas(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPesquisarOficinasPreview() {
    val navController = rememberNavController()
    TelaPesquisarOficinas(navController, modifier = Modifier)
}