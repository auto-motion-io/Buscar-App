package com.example.futurobuscartelas.signup

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.LoginActivity
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.ui.theme.ArrowBackButton
import com.example.futurobuscartelas.ui.theme.BaseScreenLayout
import com.example.futurobuscartelas.ui.theme.CheckBoxColorsMotion
import com.example.futurobuscartelas.ui.theme.CustomInputMotion
import com.example.futurobuscartelas.ui.theme.DefaultButtonMotion
import com.example.futurobuscartelas.ui.theme.InputContainerUnfocusedColor
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.UpperLabelText
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import com.example.futurobuscartelas.ui.theme.CardFiltro
import com.example.futurobuscartelas.ui.theme.isKeyboardVisible

@Composable
fun SignUpScreen(navController: NavController) {
    val viewmodel = viewModel<SignUpViewModel>()
    val steps = remember {
        mutableIntStateOf(1)
    }

    when (steps.intValue) {
        1 -> FirstStepScreen(navController, steps, viewmodel)
        //2 -> SecondStepScreen(steps, viewmodel) TODO comentei pq a api não tanka colocar imagem nessa etapa
        2 -> ThirdStepScreen(steps, viewmodel)
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

    var showDialog by remember { mutableStateOf(false) }

    var completed by remember { mutableStateOf(false) }
    completed =
        checked && nome.isNotBlank() && sobrenome.isNotBlank() && email.isNotBlank() && senha.isNotBlank()

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
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            showDialog = true
                        },
                )
            }
            if (showDialog) {
                TermsAndConditionsDialog(onDismiss = { showDialog = false })
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
                },
                enabled = completed
            )

            Image(
                painter = painterResource(id = R.drawable.logo_buscar),
                contentDescription = "logo buscar",
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.CenterHorizontally),
                alignment = Alignment.BottomCenter
            )

        }
    }
}

@Composable
fun SecondStepScreen(steps: MutableState<Int>, viewModel: SignUpViewModel) {


    BaseScreenLayout {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ArrowBackButton(onClick = {
                steps.value = 1
            })
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Próximo passo",
                style = TextStyle(
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontSize = 12.sp,
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
            Text(
                text = "Continuar sem foto",
                color = VerdeBuscar,
                style = TextStyle(
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(6.dp)
                    .clickable {
                        steps.value = 3
                    },
            )

            Image(
                painter = painterResource(id = R.drawable.logo_buscar),
                contentDescription = "logo buscar",
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.CenterHorizontally),
                alignment = Alignment.BottomCenter
            )
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ThirdStepScreen(steps: MutableState<Int>, viewModel: SignUpViewModel) {

    var carroPreference by viewModel.carroPreference
    var motoPreference by viewModel.motoPreference
    var caminhaoPreference by viewModel.caminhaoPreference
    var onibusPreference by viewModel.onibusPreference

    var combustaoPreference by viewModel.combustaoPreference
    var eletricoPreference by viewModel.eletricoPreference
    var hibridoPreference by viewModel.hibridoPreference

    var statusCode by remember { mutableStateOf(-1) }


    val context = LocalContext.current
    val intent = Intent(context, LoginActivity::class.java)


    var showResultDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogText by remember { mutableStateOf("") }
    var dialogIcon by remember { mutableStateOf(Icons.Default.Check) }


    when (statusCode) {
        201 -> {
            dialogTitle = "Cadastro realizado"
            dialogText = "Cadastro realizado, voltando para login"
            dialogIcon = Icons.Default.Check
            showResultDialog = true
        }

        409 -> {
            dialogTitle = "Erro ao cadastrar"
            dialogText = "Email já cadastrado, vá para login ou tente novamente."
            dialogIcon = Icons.Default.Check
            showResultDialog = true
        }

        in 0..599 -> {
            dialogTitle = "Erro ao cadastrar"
            dialogText = "Erro ao cadastrar, tente novamente mais tarde."
            dialogIcon = Icons.Default.Close
            showResultDialog = true
        }

    }

    if (showResultDialog) {
        ResultDialog(
            title = dialogTitle,
            text = dialogText,
            icon = dialogIcon,
            onDismiss = {
                showResultDialog = false
                if (statusCode == 201) {
                    // Navegar para a tela de login após o cadastro
                    context.startActivity(intent)
                }
                statusCode=-1
            }
        )
    }
    
    Log.i("api","showdialog ${showResultDialog.toString()}")

    BaseScreenLayout {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ArrowBackButton(onClick = {
                steps.value = 1
            })
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Falta pouco!",
                style = TextStyle(
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontSize = 12.sp,
                    color = Color(0x99474747)
                )
            )


            Text(
                text = "Selecione suas preferências",
                style = TextStyle(
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = VerdeBuscar
                ),
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Você está a procura atendimento para qual tipo de veículo?",
                style = TextStyle(
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontSize = 16.sp,
                    color = Color(0x99474747)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CardFiltro(
                    tituloCard = "Carros",
                    imagePainter = painterResource(R.mipmap.icon_carro),
                    descricaoConteudo = "Icone de Carro",
                    fundo = carroPreference,
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .border(2.dp, VerdeBuscar, CircleShape),
                    onclick = {
                        carroPreference = !carroPreference
                    },
                )


                CardFiltro(
                    tituloCard = "Motos",
                    imagePainter = painterResource(R.mipmap.icon_moto),
                    descricaoConteudo = "Icone de Moto",
                    fundo = motoPreference,
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .border(2.dp, VerdeBuscar, CircleShape),
                    onclick = {
                        motoPreference = !motoPreference
                    },
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            )
            {
                CardFiltro(
                    tituloCard = "Caminhões",
                    imagePainter = painterResource(R.drawable.icon_caminhao),
                    descricaoConteudo = "Icone de Caminhão",
                    fundo = caminhaoPreference,
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .border(2.dp, VerdeBuscar, CircleShape),
                    onclick = {
                        caminhaoPreference = !caminhaoPreference
                    },
                )

                CardFiltro(
                    tituloCard = "Ônibus",
                    imagePainter = painterResource(R.drawable.icon_onibus),
                    descricaoConteudo = "Icone de Ônibus",
                    fundo = onibusPreference,
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .border(2.dp, VerdeBuscar, CircleShape),
                    onclick = {
                        onibusPreference = !onibusPreference
                    },
                )

            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "E quanto ao tipo de propulsão dos veículos?",
                style = TextStyle(
                    fontFamily = PRODUCT_SANS_FAMILY,
                    fontSize = 16.sp,
                    color = Color(0x99474747)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CardFiltro(
                    tituloCard = "Combustão",
                    imagePainter = painterResource(R.drawable.icon_combustao),
                    descricaoConteudo = "Icone de galão de gasolina",
                    fundo = combustaoPreference,
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .border(2.dp, VerdeBuscar, CircleShape),
                    onclick = {
                        combustaoPreference = !combustaoPreference
                    },
                )


                CardFiltro(
                    tituloCard = "Elétricos",
                    imagePainter = painterResource(R.drawable.icon_eletrico),
                    descricaoConteudo = "Icone de eletricidade",
                    fundo = eletricoPreference,
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .border(2.dp, VerdeBuscar, CircleShape),
                    onclick = {
                        eletricoPreference = !eletricoPreference
                    },
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CardFiltro(
                    tituloCard = "Híbridos",
                    imagePainter = painterResource(R.drawable.icon_hibrido),
                    descricaoConteudo = "Icone híbrido",
                    fundo = hibridoPreference,
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .border(2.dp, VerdeBuscar, CircleShape),
                    onclick = {
                        hibridoPreference = !hibridoPreference
                    },
                )


            }

            Spacer(modifier = Modifier.height(24.dp))

            DefaultButtonMotion(
                text = "Cadastrar", isFilled = true,
                modifier = Modifier
                    .height(50.dp)
                    .width(138.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    viewModel.criarUsuario { code ->
                        statusCode = code;
                    }
                }
            )

            Image(
                painter = painterResource(id = R.drawable.logo_buscar),
                contentDescription = "logo buscar",
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.CenterHorizontally),
                alignment = Alignment.BottomCenter
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

@Composable
fun TermsAndConditionsDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Termos e Condições")
        },
        text = {

            Text(
                text = "Ao utilizar os serviços oferecidos pela Motion, você concorda que seus dados pessoais serão utilizados apenas para finalidades internas da aplicação. Isso inclui o armazenamento e processamento de suas informações para melhorar a experiência do usuário, oferecer suporte e permitir o uso contínuo das funcionalidades do aplicativo.\nGarantimos que os dados não serão divulgados para terceiros sem o seu consentimento explícito, exceto nos casos em que for exigido por lei. Seus dados são tratados com o máximo de segurança e confidencialidade.\nEm caso de vazamento de dados a responsável direta é Thaisa Nobrega de Costa, dona e coofundadora da motion"
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text(
                    "Fechar",
                    color = VerdeBuscar
                )
            }
        }
    )
}

@Composable
fun ResultDialog(title: String, text: String, icon: ImageVector, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        icon = {
            Image(
                painter = painterResource(id = R.drawable.logo_buscar),
                contentDescription = "Example Icon"
            )
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss()
                    Log.i("api", "ondismiss")
                }
            ) {
                Text("Ok", color = VerdeBuscar)
            }
        },
    )
}





