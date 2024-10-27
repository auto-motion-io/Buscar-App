package com.example.futurobuscartelas.telas.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.api.RetrofitService
import com.example.futurobuscartelas.models.Oficina
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TelasViewModel : ViewModel() {

    // privado porque assim evitamos que alguém de fora mexa no conteúdo da lista
    // para acessá-la de fora, usamos o getFilmes() que retorna uma cópia de seu conteúdo
    private val oficinas = mutableStateListOf<Oficina>()

    private val pitstopApi: PitstopApi

    init {
        pitstopApi = RetrofitService.getApiPitstop()
    }

    fun getOficinas() = oficinas.toList()

    fun listarOficinas() {
        GlobalScope.launch {
            try {
                val resposta = pitstopApi.listarTodos() // aqui fizemos o GET p/ a API
                val listaOficinas = resposta.body();
                if (resposta.isSuccessful) { // status 2xx (200, 201, 204 etc)?
                    Log.i("api", "users da API: ${resposta.body()}")
                    oficinas.clear() // evitando que a lista fique duplicada
                    if (!listaOficinas.isNullOrEmpty()) {
                        oficinas.addAll(listaOficinas)
                    }

                } else {
                    // resposta.errorBody()?.string() -> assim que pegamos o corpo de uma requisição em caso de erro (status 4xx ou 5xx)
                    Log.e("api", "Erro ao buscar oficinas: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                /* ocorre uma exceção caso:
                    1. Não foi possível fazer a chamada p/ a API (sem internet, API fora, App sem premissão de internet)
                    2. O corpo da resposta não pode ser convertido no tipo esperado
                 */
                Log.e("api", "Erro ao buscar oficinas", exception)
            }
        }
    }
}