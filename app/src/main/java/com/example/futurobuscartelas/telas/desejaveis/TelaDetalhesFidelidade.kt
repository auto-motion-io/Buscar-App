package com.example.futurobuscartelas.telas.desejaveis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.ui.theme.NavigationBar
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar

@Composable
fun TelaDetalhes(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {

    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        bottomBar ={
            NavigationBar(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
            Column (
                Modifier
                    .fillMaxSize()
                    .background(color = Color(245, 245, 245))
            ) {
                Column (
                    Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(bottomStart = 54.dp, bottomEnd = 54.dp))
                        .background(color = VerdeBuscar)
                ) {
                    Column (
                        Modifier.padding(top = 20.dp, bottom = 20.dp, start = 30.dp, end = 30.dp)

                    ) {
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.logo_buscarwhite),
                                contentDescription = "Logo do Buscar",
                                Modifier.width(60.dp)
                            )
                        }
                        Column (
                            Modifier.padding(top = 20.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.label_tituloOficinas),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = PRODUCT_SANS_FAMILY,
                                color = Color(230,230,230)
                            )
                        }
                    }
                }
            }
        }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaDetalhesPreview() {
        TelaDetalhes(selectedTabIndex = 3, onTabSelected = {})
}