package com.example.futurobuscartelas.login


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.api.ErrorState
import com.example.futurobuscartelas.api.SuccessState
import com.example.futurobuscartelas.signup.SignUpViewModel

import com.example.futurobuscartelas.ui.theme.ArrowBackButton
import com.example.futurobuscartelas.ui.theme.BaseScreenLayout
import com.example.futurobuscartelas.ui.theme.CustomInputMotion
import com.example.futurobuscartelas.ui.theme.DefaultButtonMotion
import com.example.futurobuscartelas.ui.theme.ErrorColor
import com.example.futurobuscartelas.ui.theme.MotionLoading
import com.example.futurobuscartelas.ui.theme.PRODUCT_SANS_FAMILY
import com.example.futurobuscartelas.ui.theme.ResultDialog
import com.example.futurobuscartelas.ui.theme.UpperLabelText
import com.example.futurobuscartelas.ui.theme.VerdeBuscar


@Composable
fun ForgotPasswordScreen(navController: NavController) {
    val viewmodel = viewModel<ForgotPasswordViewModel>()


    val errorState by viewmodel.errorState
    val successState by viewmodel.successState
    var steps by viewmodel.steps
    val isLoading by viewmodel.isLoading

    BaseScreenLayout {
        if (isLoading) {
            MotionLoading()
        }

        if (errorState.hasError) {
            ResultDialog(
                title = stringResource(id = R.string.dialog_erro),
                text = errorState.message,
                icon = Icons.Default.Check
            ) {
                viewmodel.errorState.value = ErrorState()
            }
        }
        if (successState.isSuccessfull) {
            ResultDialog(
                title = stringResource(id = R.string.dialog_sucesso),
                text = successState.message,
                icon = Icons.Default.Check
            ) {
                navController.navigate("login")
                viewmodel.successState.value = SuccessState()
            }
        }


        if (steps == 1) {
            ForgotPasswordStepOne(viewmodel = viewmodel, navController = navController)
        } else if (steps == 2) {
            ForgotPasswordStepTwo(viewmodel = viewmodel, navController = navController)
        }

    }
}

@Composable
fun ForgotPasswordStepOne(viewmodel: ForgotPasswordViewModel, navController: NavController) {
    var email by viewmodel.email
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ArrowBackButton(navController = navController)
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Esqueceu sua senha?",
            style = TextStyle(
                fontFamily = PRODUCT_SANS_FAMILY,
                fontSize = 30.sp,
                color = VerdeBuscar
            )
        )
        Spacer(modifier = Modifier.height(36.dp))

        UpperLabelText(value = "Email")
        CustomInputMotion(
            value = email,
            onValueChange = {
                email = it
            },
        )
        Spacer(modifier = Modifier.height(24.dp))

        DefaultButtonMotion(
            text = "Enviar",
            isFilled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            onClick = {
                viewmodel.recuperarSenha()
            })

    }
}


@Composable
fun ForgotPasswordStepTwo(viewmodel: ForgotPasswordViewModel, navController: NavController) {
    var token by viewmodel.token
    var novaSenha by viewmodel.novaSenha

    var confirmacaoNovaSenha by viewmodel.confirmacaoNovaSenha

    var isSenhaDiferente by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ArrowBackButton(navController = navController)
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Esqueceu sua senha?",
            style = TextStyle(
                fontFamily = PRODUCT_SANS_FAMILY,
                fontSize = 30.sp,
                color = VerdeBuscar
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Enviamos um token para a redefinição da sua senha, cheque seu email!",
            style = TextStyle(
                fontFamily = PRODUCT_SANS_FAMILY,
                fontSize = 18.sp,
                color = VerdeBuscar
            )
        )
        Spacer(modifier = Modifier.height(36.dp))

        UpperLabelText(value = "Token")
        CustomInputMotion(
            value = token,
            onValueChange = {
                token = it
            },
        )
        Spacer(modifier = Modifier.height(12.dp))
        UpperLabelText(value = "Nova senha")
        CustomInputMotion(
            value = novaSenha,
            onValueChange = {
                novaSenha = it
            },
            isPasswordField = true
        )

        Spacer(modifier = Modifier.height(12.dp))
        UpperLabelText(value = "Confirme sua nova senha")
        CustomInputMotion(
            value = confirmacaoNovaSenha,
            onValueChange = {
                confirmacaoNovaSenha = it
            },
            isPasswordField = true
        )
        if (isSenhaDiferente) {
            Text(
                text = "As senhas não coincidem",
                style = TextStyle(
                    fontSize = 12.sp
                ),
                color = ErrorColor
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        DefaultButtonMotion(
            text = "Enviar",
            isFilled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            onClick = {
                if (novaSenha != confirmacaoNovaSenha) {
                    isSenhaDiferente = true
                } else {
                    isSenhaDiferente = false
                    viewmodel.atualizarSenha()
                }
            })

    }
}

