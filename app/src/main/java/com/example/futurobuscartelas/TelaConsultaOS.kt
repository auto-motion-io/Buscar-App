package com.example.futurobuscartelas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.ui.theme.BotaoPesquisa
import com.example.futurobuscartelas.ui.theme.InputContainerUnfocusedColor
import com.example.futurobuscartelas.ui.theme.ListarOS
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar


@Composable
fun TelaConsultaOS(modifier: Modifier = Modifier) {

    var token by remember { mutableStateOf("") }

    Column (
        Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        Column (
            Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.mipmap.logo_buscar),
                contentDescription = "Logo do Buscar",
                Modifier.size(70.dp)
            )
        }
        Column (
            Modifier.padding(top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.tituloConsulta),
                color = VerdeBuscar,
                fontSize = 32.sp,
                fontFamily = PRODUCT_SANS_FAMILY,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.label_subtituloConsulta),
                color = Color(130,130,130),
                fontSize = 16.sp,
                fontFamily = PRODUCT_SANS_FAMILY
            )
        }
        Column (
            Modifier.padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.label_token),
                color = VerdeBuscar,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PRODUCT_SANS_FAMILY
            )
            Row (
                Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = token,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = VerdeBuscar,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = VerdeBuscar,
                        unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = VerdeBuscar,
                        unfocusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = InputContainerUnfocusedColor
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    onValueChange = { token = it }
                )
                BotaoPesquisa(true)
            }
        }
        Column (
            Modifier.padding(top = 30.dp)
        ) {
            Text(
                text = stringResource(R.string.label_minhasOs),
                color = VerdeBuscar,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PRODUCT_SANS_FAMILY
            )
            Text(
                text = stringResource(R.string.label_subtituloMinhaOs),
                color = Color(130,130,130),
                fontSize = 16.sp,
                fontFamily = PRODUCT_SANS_FAMILY
            )
        }
        ListarOS()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview4() {
    TelaConsultaOS()
}