package com.example.futurobuscartelas.telas.perfil

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
import com.example.futurobuscartelas.ui.theme.ListarAgenda
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar

@Composable
fun TelaAgenda(modifier: Modifier = Modifier, navController: NavController) {
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
                        .padding(end = 20.dp)
                        .width(76.dp)
                        .height(30.dp)

                )
            }
        }
        Row (
            Modifier.padding(top = 50.dp)
        ) {
            Text(
                text = stringResource(R.string.label_minhaAgenda),
                fontSize = 32.sp,
                fontFamily = PRODUCT_SANS_FAMILY,
                color = VerdeBuscar,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            Text(
                text = stringResource(R.string.label_subtituloMinhaAgenda),
                color = Color(160, 160, 160)
            )
        }
        Row {
            Text(
                text = stringResource(R.string.label_esseMes),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp),
                color = Color(90, 90, 90)
            )
        }
        ListarAgenda()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaAgendaPreview() {
    val navController = rememberNavController()
    TelaAgenda(Modifier, navController)
}