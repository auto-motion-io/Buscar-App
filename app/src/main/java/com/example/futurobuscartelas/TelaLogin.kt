package com.example.futurobuscartelas

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.ui.theme.FuturoBuscarTelasTheme

val PRODUCT_SANS_FAMILY = FontFamily(
    Font(R.font.product_sans_regular, FontWeight.Normal),
    Font(R.font.product_sans_bold, FontWeight.Bold)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FuturoBuscarTelasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingPreview(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview(modifier: Modifier = Modifier) {
    val horizontalPadding = 22.dp
    val verticalPadding = 42.dp

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    FuturoBuscarTelasTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(241, 241, 241, 255)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = horizontalPadding,
                        end = horizontalPadding,
                        top = verticalPadding,
                        bottom = 0.dp
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_buscar),
                    contentDescription = "Logo buscar",
                    modifier = Modifier
                        .size(136.dp)
                        .wrapContentHeight()
                        .aspectRatio(1.5f / 1f)
                )
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
                            append("busca ganha velocidade")
                        }
                    },
                    fontSize = 32.sp,
                    style = TextStyle(
                        lineHeight = 40.sp,
                        color = Color(59, 86, 60, 255)
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(64.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(
                            RoundedCornerShape(
                                topStart = 32.dp,
                                topEnd = 32.dp
                            )
                        )
                        .background(Color(230,230,230))
                        .padding(44.dp)
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Login",
                                style = TextStyle(
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                    color = Color(59, 86, 60, 255)
                                ),
                                modifier = Modifier.weight(1f)
                            )

                            Image(
                                painter = painterResource(id = R.drawable.fechar),
                                contentDescription = "close",
                                modifier = Modifier
                                    .offset(x = (16).dp, y = (-8).dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(59, 86, 60, 255)
                            )
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    modifier = Modifier.padding(8.dp),
                                    painter = painterResource(id = R.drawable.google_icon),
                                    contentDescription = "Google icon"
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontWeight = FontWeight.Normal,
                                            )
                                        ) {
                                            append("Login com ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontWeight = FontWeight.Bold,
                                            )
                                        ) {
                                            append("Google")
                                        }
                                    },
                                    style = TextStyle(
                                        color = Color.White
                                    ),
                                    fontSize = 16.sp,
                                    fontFamily = PRODUCT_SANS_FAMILY
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Column(modifier = Modifier.fillMaxWidth()) {

                            CustomOutlinedTextField(value = email, onValueChange = {email = it}, label = "Email*")

                            Spacer(modifier = Modifier.height(8.dp))

                            CustomOutlinedTextField(value = senha, onValueChange = {senha = it}, label = "Senha*", isPasswordField = true)

                            Spacer(modifier = Modifier.height(28.dp))

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .width(176.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(59, 86, 60, 255)
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
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isPasswordField: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = PRODUCT_SANS_FAMILY,
                ),
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color(59, 86, 60, 255),
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Color(59, 86, 60, 255),
            unfocusedLabelColor = Color.Gray,
            focusedBorderColor = Color(59, 86, 60, 255),
            unfocusedBorderColor = Color.Gray,
            focusedContainerColor = Color(230,230,230),
            unfocusedContainerColor = Color(230,230,230)
        ),
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(fontSize = 16.sp),
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None
    )
}
