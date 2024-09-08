package com.example.futurobuscartelas


import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.ui.theme.*


class TelaPreLogin() : ComponentActivity() {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPreLogin() {
    val navController = rememberNavController()
    PreLoginScreen(navController = navController)
}


@Composable
fun PreLoginScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp, 148.dp)
        ) {
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
            Spacer(modifier = Modifier.height(84.dp))
            Button(
                onClick = { navController.navigate("before-signup") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(54.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackGroundColor,
                ),
                border = BorderStroke(2.dp, VerdeBuscar)
            ) {
                Text(
                    text = "Cadastre-se",
                    style = TextStyle(
                        color = VerdeBuscar,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PRODUCT_SANS_FAMILY
                    ),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(54.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeBuscar
                )
            ) {
                Text(
                    text = "Login",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PRODUCT_SANS_FAMILY
                    ),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
            )
            {
                Text(
                    text = "Acessar com",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 16.dp),
                    style = TextStyle(
                        fontFamily = PRODUCT_SANS_FAMILY,
                        fontWeight = FontWeight.Bold,
                        color = Color(71, 71, 71, 255)
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = "Icone google login",
                    Modifier
                        .size(48.dp)
                        .align(Alignment.CenterHorizontally)
                )

            }
        }
        Image(
            painter = painterResource(id = R.drawable.cars_footer),
            contentDescription = "Carros decoração",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 24.dp)
                .size(130.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}



