package com.example.futurobuscartelas.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.CreateUsuarioDTO
import com.example.futurobuscartelas.api.RetrofitService
import com.example.futurobuscartelas.models.Usuario
import com.google.android.gms.common.api.Response
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    var nome = mutableStateOf("")
    var sobrenome = mutableStateOf("")
    var email = mutableStateOf("")
    var senha = mutableStateOf("")
    var checked = mutableStateOf(false)


    var carroPreference = mutableStateOf(false)
    var motoPreference = mutableStateOf(false)
    var caminhaoPreference = mutableStateOf(false)
    var onibusPreference = mutableStateOf(false)

    var combustaoPreference = mutableStateOf(false)
    var eletricoPreference = mutableStateOf(false)
    var hibridoPreference = mutableStateOf(false)


    // privado porque assim evitamos que alguém de fora mexa no conteúdo da lista
    // para acessá-la de fora, usamos o getFilmes() que retorna uma cópia de seu conteúdo
    private val users = mutableStateListOf<Usuario>()

    private val buscarApi: BuscarApi

    init {
        buscarApi = RetrofitService.getApiBuscar()
    }

    fun getUsers() = users.toList()


    fun transformPropulsaoPreferences(): String {
        val preferences = mutableListOf<String>()

        if (combustaoPreference.value) preferences.add("combustao")
        if (eletricoPreference.value) preferences.add("eletrico")
        if (hibridoPreference.value) preferences.add("hibrido")

        return preferences.joinToString(";")

    }

    fun transformVeiculoPreferences(): String {
        val preferences = mutableListOf<String>()

        if (carroPreference.value) preferences.add("carro")
        if (motoPreference.value) preferences.add("moto")
        if (caminhaoPreference.value) preferences.add("caminhao")
        if (onibusPreference.value) preferences.add("onibus")

        return preferences.joinToString(";")
    }


    fun listarUsuarios() {
        GlobalScope.launch {
            try {
                val resposta = buscarApi.listarTodos() // aqui fizemos o GET p/ a API
                val usersList = resposta.body();
                if (resposta.isSuccessful) { // status 2xx (200, 201, 204 etc)?
                    Log.i("api", "users da API: ${resposta.body()}")
                    users.clear() // evitando que a lista fique duplicada
                    if (!usersList.isNullOrEmpty()) {
                        users.addAll(usersList)
                    }

                } else {
                    // resposta.errorBody()?.string() -> assim que pegamos o corpo de uma requisição em caso de erro (status 4xx ou 5xx)
                    Log.e("api", "Erro ao buscar filmes: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                /* ocorre uma exceção caso:
                    1. Não foi possível fazer a chamada p/ a API (sem internet, API fora, App sem premissão de internet)
                    2. O corpo da resposta não pode ser convertido no tipo esperado
                 */
                Log.e("api", "Erro ao buscar filmes", exception)
            }
        }
    }

    fun criarUsuario(onResult: (Int) -> Unit) {
        val teste = transformPropulsaoPreferences();
        Log.i("api","preferencias ${teste}")
        val novoUsuario = CreateUsuarioDTO(
            nome = nome.value,
            sobrenome = sobrenome.value,
            email = email.value,
            senha = senha.value,
            preferenciasVeiculos = transformVeiculoPreferences(),
            preferenciasPropulsao = transformPropulsaoPreferences()
        )

        // Chame seu método para enviar o novo usuário para a API
        GlobalScope.launch {
            Log.i("api", "Criando usuario....")
            try {
                val resposta =
                    buscarApi.cadastrarUsuario(novoUsuario) // Método que você implementou para fazer POST
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