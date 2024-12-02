package com.example.futurobuscartelas.login

import android.util.Log
import androidx.compose.runtime.getValue
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

    var isLoading = mutableStateOf(false)
        private set

    @OptIn(DelicateCoroutinesApi::class)
    fun recuperarSenha() {
        val sendEmailDTO = SendEmailDTO(email.value)
        GlobalScope.launch {
            try {
                isLoading.value = true
                val resposta = buscarApi.setToken(sendEmailDTO, "senha")
                isLoading.value = false
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
                Log.i("api", e.toString())
                errorState.value = ErrorState(
                    hasError = true,
                    message = "Um erro inesperado ocorreu."
                )
                isLoading.value = false
            }
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun atualizarSenha() {
        val tokenUpperCase = token.value.uppercase()
        val confirmTokenDTO = ConfirmTokenDTO(email.value, novaSenha.value, tokenUpperCase)
        GlobalScope.launch {
            try {
                isLoading.value = true
                val resposta = buscarApi.confirmarToken(confirmTokenDTO, "senha")
                isLoading.value = false
                Log.i("api", "res ${resposta}")
                if (resposta.isSuccessful) {
                    successState.value = SuccessState(true, "Sua senha foi atualizada com sucesso. Agora, você pode usar sua nova senha para acessar sua conta.")
                } else if (resposta.code() == 401) {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Token inválido, tente novamente."
                    )
                } else {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Um erro inesperado ocorreu. Entre em contato com o suporte."
                    )
                }
            } catch (e: Exception) {
                Log.i("api", e.toString())
                errorState.value = ErrorState(
                    hasError = true,
                    message = "Um erro inesperado ocorreu."
                )
                isLoading.value = false
            }
        }
    }
}