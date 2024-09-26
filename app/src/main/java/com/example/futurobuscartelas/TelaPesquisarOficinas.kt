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

class TelaPesquisarOficinas : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                TelaPesquisarOficinas()
            }
        }
    }
}

@Composable
fun TelaPesquisarOficinas(navController: NavController) {
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
            Modifier.padding(top = 5.dp)
        ) {
            Column (
                Modifier
                    .width(120.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(color = Color(59, 86, 60))
            ) {
                Image(
                    painter = painterResource(R.mipmap.icon_moto),
                    contentDescription = "Icone de Moto",
                    Modifier.size(20.dp)
                )
            }
            Column (
                Modifier
                    .width(120.dp)
                    .height(50.dp)
                    .padding(start = 5.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(color = Color(59, 86, 60))
            ) {
                Image(
                    painter = painterResource(R.mipmap.icon_moto),
                    contentDescription = "Icone de Moto",
                    Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPesquisarOficinasPreview() {
    val navController = rememberNavController()
    TelaPesquisarOficinas(navController)
}