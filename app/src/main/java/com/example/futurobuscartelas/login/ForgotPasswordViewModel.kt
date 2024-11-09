package com.example.futurobuscartelas.login

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.ErrorState
import com.example.futurobuscartelas.api.SuccessState
import com.example.futurobuscartelas.dto.ConfirmTokenDTO
import com.example.futurobuscartelas.dto.SendEmailDTO
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ForgotPasswordViewModel : ViewModel() {
    var email = mutableStateOf("")
    var token = mutableStateOf("")
    var novaSenha = mutableStateOf("")
    var confirmacaoNovaSenha = mutableStateOf("")
    var steps = mutableIntStateOf(1)

    var errorState = mutableStateOf(ErrorState())
    var successState = mutableStateOf(SuccessState())

    val buscarApi: BuscarApi by inject(BuscarApi::class.java)


    @OptIn(DelicateCoroutinesApi::class)
    fun recuperarSenha() {
        val sendEmailDTO = SendEmailDTO(email.value)
        GlobalScope.launch {
            try {
                val resposta = buscarApi.setToken(sendEmailDTO, "senha")
                Log.i("api", "res ${resposta}")
                if (resposta.isSuccessful) {
                    steps.intValue = 2
                } else if (resposta.code() == 404) {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Não encontramos registro do seu email em nosso sistema"
                    )
                } else {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Um erro inesperado ocorreu."
                    )
                }
            } catch (e: Exception) {
                Log.i("api",e.toString())
                errorState.value = ErrorState(
                    hasError = true,
                    message = "Um erro inesperado ocorreu."
                )
            }
        }
    }



    @OptIn(DelicateCoroutinesApi::class)
    fun atualizarSenha() {
        val confirmTokenDTO = ConfirmTokenDTO(email.value,novaSenha.value,token.value)
        GlobalScope.launch {
            try {
                val resposta = buscarApi.confirmarToken(confirmTokenDTO, "senha")
                Log.i("api", "res ${resposta}")
                if (resposta.isSuccessful) {
                    successState.value = SuccessState(true,"Sua senha foi alterada com sucesso")
                } else if (resposta.code() == 404) {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Não encontramos registro do seu email em nosso sistema"
                    )
                } else {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Um erro inesperado ocorreu."
                    )
                }
            } catch (e: Exception) {
                Log.i("api",e.toString())
                errorState.value = ErrorState(
                    hasError = true,
                    message = "Um erro inesperado ocorreu."
                )
            }
        }
    }
}