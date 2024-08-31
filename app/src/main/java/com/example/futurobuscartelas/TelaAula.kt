package com.example.appteste

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.futurobuscartelas.ui.theme.FuturoBuscarTelasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FuturoBuscarTelasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Tela(
                        name = "Leonardo",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Tela(name: String, modifier: Modifier = Modifier) {
    Column (modifier = Modifier){
        Text(
            text = "Olá $name!",
            style = TextStyle(
                fontSize = 45.sp,
                color = Color(255,0,0),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "UUUUUUUUUUUUUUH ${name.uppercase()}!",
            style = TextStyle(
                fontSize = 15.sp,
                color = Color(213, 0, 249, 255),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {  }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "CLique aqui")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FuturoBuscarTelasTheme {
        Tela("Leonardo")
    }
}