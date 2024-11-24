package com.example.futurobuscartelas.telas.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OrdemServico
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class TelasViewModel : ViewModel() {

    private val oficinas = mutableStateListOf<Oficina>()
    private val ordensDeServico = mutableStateListOf<OrdemServico>()

    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)

    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)

    fun getOficinas() = oficinas.toList()
    fun getOrdensDeServico() = ordensDeServico.toList()

    @OptIn(DelicateCoroutinesApi::class)
    fun listarOficinas() {
        GlobalScope.launch {
            try {
                val resposta = pitstopApi.listarTodos()
                val listaOficinas = resposta.body();
                if (resposta.isSuccessful) {
                    Log.i("api", "users da API: ${resposta.body()}")
                    oficinas.clear()
                    if (!listaOficinas.isNullOrEmpty()) {
                        oficinas.addAll(listaOficinas)
                    }

                } else {
                    Log.e("api", "Erro ao buscar oficinas: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar oficinas", exception)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun listarOficinasFavoritas(id:Int) {
        GlobalScope.launch {
            try {
                val resposta = buscarApi.listarOficinas(id)
                val listaOficinas = resposta.body();
                if (resposta.isSuccessful) {
                    Log.i("api", "users da API: ${resposta.body()}")
                    oficinas.clear()
                    if (!listaOficinas.isNullOrEmpty()) {
                        oficinas.addAll(listaOficinas)
                    }

                } else {
                    Log.e("api", "Erro ao buscar oficinas: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar oficinas", exception)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun listarOsPorCliente(id:Int) {
        GlobalScope.launch {
            try {
                val resposta = buscarApi.listarOsPorCliente(id)
                val listaOs = resposta.body();
                if (resposta.isSuccessful) {
                    Log.i("api", "users da API: ${resposta.body()}")
                    ordensDeServico.clear()
                    if (!listaOs.isNullOrEmpty()) {
                        ordensDeServico.addAll(listaOs)
                    }

                } else {
                    Log.e("api", "Erro ao buscar oficinas: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar oficinas", exception)
            }
        }
    }
}