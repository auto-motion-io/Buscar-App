package com.example.futurobuscartelas.telas.perfil

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futurobuscartelas.MainActivity
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.api.ErrorState
import com.example.futurobuscartelas.api.SuccessState
import com.example.futurobuscartelas.dto.UpdateUsuarioDTO
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.location.LocationManager
import com.example.futurobuscartelas.login.UserData
import com.example.futurobuscartelas.login.UserRepository
import com.example.futurobuscartelas.onboarding.LoadingScreen
import com.example.futurobuscartelas.ui.theme.CustomInputMotion
import com.example.futurobuscartelas.ui.theme.CustomInputPerfil
import com.example.futurobuscartelas.ui.theme.DefaultButtonMotion
import com.example.futurobuscartelas.ui.theme.MotionLoading
import com.example.futurobuscartelas.ui.theme.NavigationBar
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.ResultDialog
import com.example.futurobuscartelas.ui.theme.VerdeBuscar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaPerfil(selectedTabIndex: Int, onTabSelected: (Int) -> Unit, sessaoUsuario: SessaoUsuario) {
    val context = LocalContext.current

    val userRepository = remember { UserRepository(context) }
    val viewModel = remember { PerfilViewModel() }

    // Gerencia a navegação e ações
    val coroutineScope = rememberCoroutineScope()

    // Estados para os campos
    var nome by remember { mutableStateOf(sessaoUsuario.nome) }
    var sobrenome by remember { mutableStateOf(sessaoUsuario.sobrenome) }
    var email by remember { mutableStateOf(sessaoUsuario.email) }

    var senhaAtual by remember { mutableStateOf("") }
    var novaSenha by remember { mutableStateOf("") }
    var confirmacaoNovaSenha by remember { mutableStateOf("") }

    var enableInputs by remember { mutableStateOf(false) }
    var isEdicaoSenha by remember { mutableStateOf(false) }

    var showSenhaInvalidaDialog by remember { mutableStateOf(false) }
    var senhaInvalidaMensagem by remember { mutableStateOf("") }

    val errorState by viewModel.errorState
    val successState by viewModel.successState
    val isLoading by viewModel.isLoading
    Log.i("session", sessaoUsuario.nome)
    if (isLoading) {
        MotionLoading()
    }

    if (errorState.hasError) {
        ResultDialog(
            title = stringResource(id = R.string.dialog_erro),
            text = errorState.message,
            icon = Icons.Default.Check
        ) {
            viewModel.errorState.value = ErrorState()
        }
    }
    if (successState.isSuccessfull) {
        ResultDialog(
            title = stringResource(id = R.string.dialog_sucesso),
            text = successState.message,
            icon = Icons.Default.Check
        ) {
            coroutineScope.launch {
                userRepository.saveUserData(
                    UserData(
                        idUsuario = sessaoUsuario.id,
                        email = sessaoUsuario.email,
                        nome = sessaoUsuario.nome,
                        sobrenome = sessaoUsuario.sobrenome,
                        token = sessaoUsuario.token,
                        fotoUrl = sessaoUsuario.fotoUrl
                    )
                )
            }
            enableInputs = !enableInputs
            isEdicaoSenha = false // Resetar estado de edição de senha
            viewModel.successState.value = SuccessState()
        }
    }

    if (showSenhaInvalidaDialog) {
        ResultDialog(
            title = stringResource(id = R.string.dialog_erro),
            text = senhaInvalidaMensagem,
            icon = Icons.Default.Check
        ) {
            showSenhaInvalidaDialog = false
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color(245, 245, 245))
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(bottomStart = 54.dp, bottomEnd = 54.dp))
                    .background(color = VerdeBuscar),
            ) {
                Column(
                    Modifier.padding(top = 20.dp, bottom = 20.dp, start = 30.dp, end = 30.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween // Logo no meio e botão no final
                    ) {
                        // Logo no início
                        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(R.mipmap.logo_buscarwhite),
                                contentDescription = "Logo do Buscar",
                                Modifier.width(80.dp)
                            )
                        }

                        LogoutButton(
                            context = context,
                            userRepository = userRepository,
                            coroutineScope = coroutineScope
                        )

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.mipmap.icon_user),
                                contentDescription = "Ícone do Usuário",
                                Modifier.size(64.dp)
                            )
                        }
                        Column(
                            Modifier.padding(horizontal = 10.dp)
                        ) {
                            Row {
                                Text(
                                    text = stringResource(R.string.label_bemvindo),
                                    color = Color(240, 240, 240),
                                    fontSize = 18.sp,
                                    fontFamily = PRODUCT_SANS_FAMILY
                                )
                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${sessaoUsuario.nome} ${sessaoUsuario.sobrenome}",
                                    color = Color(240, 240, 240),
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = PRODUCT_SANS_FAMILY,
                                    fontSize = 22.sp
                                )


                            }
                        }
                    }
                }
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 30.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {

                if (isEdicaoSenha) {
                    Column {
                        Text(
                            stringResource(R.string.label_senha_atual),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        CustomInputMotion(
                            value = senhaAtual,
                            onValueChange = { senhaAtual = it },
                            isPasswordField = true
                        )
                    }
                    Column {
                        Text(
                            stringResource(R.string.label_nova_senha),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        CustomInputMotion(
                            value = novaSenha,
                            onValueChange = { novaSenha = it },
                            isPasswordField = true
                        )
                    }
                    Column {
                        Text(
                            stringResource(R.string.label_confirmacao_senha),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        CustomInputMotion(
                            value = confirmacaoNovaSenha,
                            onValueChange = { confirmacaoNovaSenha = it },
                            isPasswordField = true
                        )
                    }
                } else {


                    Column {
                        Text(
                            stringResource(R.string.label_nome),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        CustomInputPerfil(
                            value = nome,
                            onValueChange = { nome = it },
                            isEnabled = enableInputs && !isEdicaoSenha
                        )
                    }
                    Column {
                        Text(
                            stringResource(R.string.label_sobrenome),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        CustomInputPerfil(
                            value = sobrenome,
                            onValueChange = { sobrenome = it }, isEnabled = enableInputs && !isEdicaoSenha
                        )
                    }
                    Column {
                        Text(
                            stringResource(R.string.label_email),
                            fontFamily = PRODUCT_SANS_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = VerdeBuscar
                        )
                        CustomInputPerfil(
                            value = email,
                            onValueChange = { email = it },
                            isEnabled = enableInputs && !isEdicaoSenha
                        )
                    }


                }


                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    // Botão principal
                    DefaultButtonMotion(
                        text = if (enableInputs) "Salvar" else "Editar",
                        isFilled = true,
                        modifier = Modifier
                            .height(50.dp)
                            .width(138.dp),
                        onClick = {
                            if (enableInputs) {
                                if (!isEdicaoSenha) {
                                    // Salvar dados do usuário
                                    val usuarioNovo = UpdateUsuarioDTO(
                                        nome = nome,
                                        sobrenome = sobrenome,
                                        email = email
                                    )
                                    viewModel.atualizarUsuario(usuarioNovo, sessaoUsuario)
                                }else {
                                    // Salvar senha
                                    if (senhaAtual.isEmpty() || novaSenha.isEmpty() || confirmacaoNovaSenha.isEmpty()) {
                                        Log.i("senha", "campos vazios")
                                        showSenhaInvalidaDialog = true
                                        senhaInvalidaMensagem = "Preencha todos os campos"
                                    } else if (novaSenha != confirmacaoNovaSenha) {
                                        Log.i("senha", "senhas diferentes")
                                        showSenhaInvalidaDialog = true
                                        senhaInvalidaMensagem = "As senhas não coincidem"
                                    } else {
                                        viewModel.atualizarSenha(
                                            senhaAtual,
                                            novaSenha,
                                            sessaoUsuario
                                        )
                                    }
                                }
                            }else{
                                enableInputs = !enableInputs
                            }

                        }
                    )

                    // Espaço entre os botões
                    Spacer(modifier = Modifier.width(20.dp))

                    // Botão "Cancelar" ou "Alterar Senha"
                    if (!enableInputs || isEdicaoSenha) {
                        DefaultButtonMotion(
                            text = if (isEdicaoSenha) "Cancelar" else "Alterar Senha",
                            isFilled = false,
                            modifier = Modifier
                                .height(50.dp)
                                .width(158.dp),
                            onClick = {
                                if (isEdicaoSenha) {
                                    // Cancelar edição de senha
                                    isEdicaoSenha = false
                                    enableInputs = false
                                    senhaAtual = ""
                                    novaSenha = ""
                                    confirmacaoNovaSenha = ""
                                } else {
                                    // Iniciar edição de senha
                                    isEdicaoSenha = true
                                    enableInputs = true
                                }
                            }
                        )
                    }

                    // Botão "Cancelar" (apenas para edição de informações)
                    if (enableInputs && !isEdicaoSenha) {
                        DefaultButtonMotion(
                            text = "Cancelar",
                            isFilled = false,
                            modifier = Modifier
                                .height(50.dp)
                                .width(138.dp),
                            onClick = {
                                // Cancelar edição de informações
                                enableInputs = false
                                isEdicaoSenha = false
                                nome = sessaoUsuario.nome
                                sobrenome = sessaoUsuario.sobrenome
                                email = sessaoUsuario.email
                            }
                        )
                    }

                }
            }
        }
    }

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LogoutButton(context: Context, userRepository: UserRepository, coroutineScope: CoroutineScope) {

    val showDialog = remember { mutableStateOf(false) }


    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Text(text = "Confirmar saída")
            },
            text = {
                Text(text = "Você tem certeza que deseja sair?")
            },
            confirmButton = {
                DefaultButtonMotion(text = "Confirmar", isFilled = true, modifier = Modifier,
                    onClick = {

                        coroutineScope.launch {
                            userRepository.clearUserData()
                        }
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as? Activity)?.finish()


                        showDialog.value = false
                    })
            },
            dismissButton = {
                DefaultButtonMotion(
                    text = "Cancelar",
                    isFilled = false,
                    modifier = Modifier,
                    onClick = {
                        showDialog.value = false
                    })
            }
        )
    }

    Button(
        onClick = {

            showDialog.value = true
        },
        modifier = Modifier.size(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.icon_logout),
            contentDescription = "Ícone de Sair",
            modifier = Modifier.size(56.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPerfilPreview() {
    TelaPerfil(selectedTabIndex = 3, onTabSelected = {}, sessaoUsuario = SessaoUsuario())
}