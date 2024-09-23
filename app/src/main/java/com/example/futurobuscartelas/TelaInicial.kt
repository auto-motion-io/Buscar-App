package com.example.futurobuscartelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.ui.theme.InputContainerUnfocusedColor
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import com.example.futurobuscartelas.ui.theme.addCategoria

class TelaInicialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                TelaInicial(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun TelaInicial(modifier: Modifier = Modifier) {

    var token by remember { mutableStateOf("") }

    Column (
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 30.dp, end = 30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.label_bemvindo),
            fontSize = 18.sp,
            color = Color(59, 86, 60),
            fontFamily = PRODUCT_SANS_FAMILY
        )
        Text(
            text = "Marcos Gonzales",
            fontSize = 18.sp,
            color = Color(59, 86, 60),
            fontFamily = PRODUCT_SANS_FAMILY
        )
        Column (Modifier.padding(top = 30.dp)) {
            Column (Modifier.padding(bottom = 20.dp)) {
                Text(
                    text = "Categorias",
                    fontSize = 36.sp,
                    color = Color(59, 86, 60),
                    fontWeight = FontWeight.Bold,
                    fontFamily = PRODUCT_SANS_FAMILY
                )
            }
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                addCategoria(categoria = "Oficinas")
                addCategoria(categoria = "Serviços")
                addCategoria(categoria = "Peças")
            }
        }
        Column (Modifier.padding(top = 30.dp)) {
            Column (Modifier.padding(bottom = 5.dp)) {
                Text(
                    text = "Consulta OS",
                    fontSize = 36.sp,
                    color = Color(59, 86, 60),
                    fontWeight = FontWeight.Bold,
                    fontFamily = PRODUCT_SANS_FAMILY
                )
            }
            Text(
                text = stringResource(id = R.string.label_tokenOs)
            )
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
                        onValueChange = { token = it },
                    )
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(50.dp)
                            .width(140.dp)
                            .padding(start = 10.dp)
                            .clip(RoundedCornerShape(200.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = VerdeBuscar,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.White,
                        )
                    ) {}
                }
            }
        }
        Column (Modifier.padding(top = 30.dp)) {
            Column (Modifier.padding(bottom = 20.dp)) {
                Text(
                    text = "Favoritos",
                    fontSize = 36.sp,
                    color = Color(59, 86, 60),
                    fontWeight = FontWeight.Bold,
                    fontFamily = PRODUCT_SANS_FAMILY
                )
            }
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column (
                    Modifier
                        .width(160.dp)
                        .height(110.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color(235, 235, 235))
                ) {}
                Column (
                    Modifier
                        .width(160.dp)
                        .height(110.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color(235, 235, 235))
                ) {}
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    TelaInicial()
}