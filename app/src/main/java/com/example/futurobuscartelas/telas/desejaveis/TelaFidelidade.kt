package com.example.futurobuscartelas.telas.desejaveis

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.ui.theme.ArrowBackButton
import com.example.futurobuscartelas.ui.theme.CardFidelidade
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar

@Composable
fun TelaFidelidade(modifier: Modifier = Modifier, navController: NavController) {
    Column (
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row {
            ArrowBackButton(navController = navController)
            Column (
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.mipmap.logo_buscar),
                    contentDescription = "Logo do Buscar",
                    Modifier
                        .padding(end = 40.dp)
                        .width(76.dp)
                        .height(30.dp)

                )
            }
        }
        Row (
            Modifier.padding(top = 50.dp)
        ) {
            Text(
                text = stringResource(R.string.label_fidelidade),
                fontSize = 32.sp,
                fontFamily = PRODUCT_SANS_FAMILY,
                color = VerdeBuscar,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            Text(
                text = stringResource(R.string.label_subtituloFidelidade),
                color = Color(160, 160, 160),
            )
        }
        Column (
            Modifier.padding(top = 20.dp)
        ) {
            CardFidelidade()
            CardFidelidade()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaFidelidadePreview() {
    val navController = rememberNavController()
    TelaFidelidade(Modifier, navController)
}