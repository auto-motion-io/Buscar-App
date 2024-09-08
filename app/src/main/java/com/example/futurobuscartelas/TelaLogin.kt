package com.example.futurobuscartelas

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun LoginScreenPreview(){
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}

@Composable
fun LoginScreen(navController: NavHostController) {
    val horizontalPadding = 24.dp
    val verticalPadding = 64.dp

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    FuturoBuscarTelasTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackGroundColor
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = horizontalPadding,
                            end = horizontalPadding,
                            top = verticalPadding,
                            bottom = 0.dp
                        )
                ) {

                    ArrowBackButton(navController = navController)


                    Spacer(modifier = Modifier.height(36.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
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
                            UpperLabelText(value = "Email*")
                            CustomOutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            UpperLabelText(value = "Senha*")
                            CustomOutlinedTextField(
                                value = senha,
                                onValueChange = { senha = it },
                                isPasswordField = true
                            )
                            Text(
                                text = "Esqueci minha senha",
                                modifier = Modifier.padding(6.dp),
                                textDecoration = TextDecoration.Underline,
                                fontFamily = PRODUCT_SANS_FAMILY,
                                fontSize = 14.sp,
                                color = Color(71, 71, 71, 255)
                                )
                            Spacer(modifier = Modifier.height(36.dp))

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .height(54.dp)
                                    .width(138.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = VerdeBuscar
                                )
                            ) {
                                Text(
                                    text = "Entrar",
                                    style = TextStyle(
                                        color = Color.White,
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
}



@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPasswordField: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
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
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        textStyle = TextStyle(fontSize = 16.sp),
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable

fun UpperLabelText(value:String){
    Text(
        text = value,
        modifier = Modifier.padding(0.dp,0.dp,6.dp,10.dp),
        style = TextStyle(
            fontFamily = PRODUCT_SANS_FAMILY,
            fontWeight = FontWeight.Bold,
            color = Color(71,71,71),
            fontSize = 18.sp
        )
    )
}
