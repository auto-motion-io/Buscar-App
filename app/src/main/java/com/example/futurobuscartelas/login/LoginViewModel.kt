package com.example.futurobuscartelas.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.RetrofitService
import com.example.futurobuscartelas.dto.LoginUsuarioDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
class LoginViewModel : ViewModel() {
    var email = remember { mutableStateOf("") }
    var senha = remember { mutableStateOf("") }

    private val buscarApi: BuscarApi

    init {
        buscarApi = RetrofitService.getApiBuscar()
    }

    fun login(onResult: (Int) -> Unit) {
        Log.i("api", "Logando....")
        val userLogin = LoginUsuarioDTO(email.value,senha.value)
        GlobalScope.launch {
            Log.i("api", "Criando usuario....")
            try {
                val resposta = buscarApi.login(userLogin)
                if (resposta.isSuccessful) {
                    Log.i("api", "Usuario criado com sucesso: ${resposta.body()}")
                    // Faça algo com a resposta, como atualizar a UI
                    onResult(resposta.code())
                } else {
                    Log.e("api", "Erro ao criar usuário: ${resposta.errorBody()?.string()}")
                    onResult(resposta.code())
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao criar usuário", exception)
            }
        }
    }
}