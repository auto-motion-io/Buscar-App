package com.example.futurobuscartelas

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.ui.theme.*


class LoginActivity() : ComponentActivity() {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}

@Composable
fun LoginScreen(navController: NavHostController) {


    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var isSenhaValid by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackGroundColor
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = DefaultHorizontalPadding,
                        end = DefaultHorizontalPadding,
                        top = DefaultVerticalPadding,
                        bottom = 0.dp
                    )
            ) {

                ArrowBackButton(navController = navController)


                Spacer(modifier = Modifier.height(36.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                        .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focusManager.clearFocus() // Fecha o teclado ao tocar fora do campo de texto
                        })
                        })
                {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Login",
                            style = TextStyle(
                                fontFamily = PRODUCT_SANS_FAMILY,
                                fontWeight = FontWeight.Bold,
                                fontSize = 36.sp,
                                color = VerdeBuscar
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(36.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        UpperLabelText(value = "Email")
                        CustomInputMotion(
                            value = email,
                            onValueChange = {
                                email = it
                                if (email == "motion@gmail.com") isEmailValid = true
                            },
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        UpperLabelText(value = "Senha")
                        CustomInputMotion(
                            value = senha,
                            onValueChange = {
                                senha = it
                                if (senha == "motion") isSenhaValid = true
                            },
                            isPasswordField = true
                        )
                        Text(
                            text = "Esqueci minha senha",
                            modifier = Modifier.padding(6.dp),
                            textDecoration = TextDecoration.Underline,
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        Spacer(modifier = Modifier.height(36.dp))

                        Button(
                            onClick = {
                                if (!isEmailValid || !isSenhaValid) {
                                    Toast.makeText(
                                        context,
                                        "Email ou senha inválidos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    navController.navigate("oficina")
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .height(50.dp)
                                .width(138.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = VerdeBuscar,
                                disabledContainerColor = Color.LightGray,
                                disabledContentColor = Color.White,
                            ),
                            enabled = senha.isNotBlank() && email.isNotBlank()
                        ) {
                            Text(
                                text = "Entrar",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = PRODUCT_SANS_FAMILY
                                ),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
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
}



