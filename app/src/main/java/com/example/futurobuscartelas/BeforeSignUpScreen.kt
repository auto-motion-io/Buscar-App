package com.example.futurobuscartelas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.ui.theme.*

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BeforeSignUpPreview() {
    val navController = rememberNavController()
    BeforeSignUpScreen(navController)
}

@Composable
fun BeforeSignUpScreen(navController: NavController) {
    BaseScreenLayout {
        Column {
            ArrowBackButton(navController = navController)

            Image(
                painter = painterResource(id = R.drawable.logo_buscar),
                contentDescription = "Logo buscar",
                modifier = Modifier
                    .width(128.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontFamily = PRODUCT_SANS_FAMILY,
                        )
                    ) {
                        append("Onde a ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontFamily = PRODUCT_SANS_FAMILY,
                        )
                    ) {
                        append("busca ganha velocidade.")
                    }
                },
                fontSize = 32.sp,
                style = TextStyle(
                    lineHeight = 40.sp,
                    color = VerdeBuscar
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .offset(18.dp)
            )
            Spacer(modifier = Modifier.height(128.dp))

            Column(
                modifier = Modifier.fillMaxWidth().offset(18.dp)
            ) {

            Text(
                modifier = Modifier
                    .padding(vertical = 6.dp),
                text = "Vamos começar",
                color = VerdeBuscar,
                fontWeight = FontWeight.Bold,
                fontFamily = PRODUCT_SANS_FAMILY,
                fontSize = 32.sp
            )
            Text(
                text = "Se cadastre para procurar pelas melhores oficinas, serviços ou produtos",
                fontFamily = PRODUCT_SANS_FAMILY,
                color = Color(0xFF646464),
                fontSize = 15.sp
            )
            }
        }
    }
}
