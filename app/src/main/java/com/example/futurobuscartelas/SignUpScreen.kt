package com.example.futurobuscartelas

import android.app.Activity
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.ui.theme.ArrowBackButton
import com.example.futurobuscartelas.ui.theme.BaseScreenLayout
import com.example.futurobuscartelas.ui.theme.CheckBoxColorsMotion
import com.example.futurobuscartelas.ui.theme.CustomInputMotion
import com.example.futurobuscartelas.ui.theme.DefaultButtonMotion
import com.example.futurobuscartelas.ui.theme.InputContainerUnfocusedColor
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.UpperLabelText
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import com.example.futurobuscartelas.ui.theme.isKeyboardVisible

@Composable
fun SignUpScreen(navController: NavController) {
    val viewmodel = viewModel<SignUpViewModel>()
    val steps = remember {
        mutableIntStateOf(2)
    }

    when (steps.intValue) {
        1 -> FirstStepScreen(navController, steps, viewmodel)
        2 -> SecondStepScreen(steps)
        //2 -> ThirdStepScreen(navController, steps)
        // Adicione mais etapas conforme necessário
    }


}

@Composable
fun FirstStepScreen(
    navController: NavController,
    steps: MutableState<Int>,
    viewModel: SignUpViewModel
) {
    val isKeyboardVisible = isKeyboardVisible()
    var nome by viewModel.nome
    var sobrenome by viewModel.sobrenome
    var email by viewModel.email
    var senha by viewModel.senha


    var checked by viewModel.checked

    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    BaseScreenLayout {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .imePadding()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus() // Fecha o teclado ao tocar fora do campo de texto
                    })
                }
        ) {
            if (!isKeyboardVisible) {
                ArrowBackButton(navController = navController)
                Spacer(modifier = Modifier.height(36.dp))
                Text(
                    text = "Cadastrar-se",
                    style = TextStyle(
                        fontFamily = PRODUCT_SANS_FAMILY,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        color = VerdeBuscar
                    ),
                )

                Spacer(modifier = Modifier.height(36.dp))
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                UpperLabelText(value = "Nome")
                CustomInputMotion(value = nome, onValueChange = {
                    nome = it
                })
                Spacer(modifier = Modifier.height(16.dp))
                UpperLabelText(value = "Sobrenome")
                CustomInputMotion(value = sobrenome, onValueChange = {
                    sobrenome = it
                })
                Spacer(modifier = Modifier.height(16.dp))
                UpperLabelText(value = "Email")
                CustomInputMotion(value = email, onValueChange = {
                    email = it
                })
                Spacer(modifier = Modifier.height(16.dp))
                UpperLabelText(value = "Senha")
                CustomInputMotion(
                    value = senha, onValueChange = {
                        senha = it
                    },
                    isPasswordField = true
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(top = 6.dp)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = CheckBoxColorsMotion
                )
                Text(
                    text = "Eu li e concordo com os termos e condições de uso.",
                    fontSize = 11.sp,
                    fontFamily = PRODUCT_SANS_FAMILY,
                    color = Color(0x99474747),
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            DefaultButtonMotion(
                text = "Continuar", isFilled = true,
                modifier = Modifier
                    .height(50.dp)
                    .width(138.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    steps.value = 2
                }
            )
            Spacer(modifier = Modifier.height(46.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_buscar),
                contentDescription = "logo buscar",
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.CenterHorizontally)
            )

        }
    }
}

@Composable
fun SecondStepScreen(steps: MutableState<Int>) {
    var teste by remember {
        mutableStateOf(false)
    }
    BaseScreenLayout {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ArrowBackButton(onClick = {
                steps.value = 1
                teste = !teste
            })
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Próximo passo",
                style = TextStyle(
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontSize = 11.sp,
                    color = Color(0x99474747)
                )
            )


            Text(
                text = "Adicione uma foto de perfil",
                style = TextStyle(
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = VerdeBuscar
                ),
            )


            Box(
                modifier = Modifier
                    .size(320.dp)
                    .clip(CircleShape)
                    .background(InputContainerUnfocusedColor)
            ) {

            }
            Image(
                painter = painterResource(id = R.drawable.icon_edit_image),
                contentDescription = "Editar imagem",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .align(Alignment.End)

            )

            DefaultButtonMotion(
                text = "Continuar", isFilled = true,
                modifier = Modifier
                    .height(50.dp)
                    .width(138.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    steps.value = 3
                }
            )
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController)
}