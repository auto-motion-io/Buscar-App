package com.example.futurobuscartelas.telas.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.Produto
import com.example.futurobuscartelas.models.Servico
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class TelaInicialViewModel : ViewModel() {

    private val oficinasFavoritas = mutableStateListOf<Oficina>()
    private val oficinas = mutableStateListOf<Oficina>()
    private val servicos = mutableStateListOf<Servico>()
    private val pecas = mutableStateListOf<Produto>()

    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)
    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)

    fun getOficinasFavoritas() = oficinasFavoritas.toList()
    fun getOficinas() = oficinas.toList()
    fun getServicos() = servicos.toList()
    fun getPecas() = pecas.toList()

    fun listarOficinasFavoritas(id: Int) {
        viewModelScope.launch {
            try {
                val resposta = buscarApi.listarOficinas(id)
                if (resposta.isSuccessful) {
                    val listaOficinasFavoritas = resposta.body().orEmpty()
                    // Extraia apenas as oficinas
                    val listaOficinas = listaOficinasFavoritas.mapNotNull { it.oficina }
                    oficinasFavoritas.clear()
                    oficinasFavoritas.addAll(listaOficinas)
                    Log.i("api", "Oficinas: $listaOficinas")
                } else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar oficinas", e)
            }
        }
    }

    fun listarServicos(){
        viewModelScope.launch {
            try{
                val resposta = pitstopApi.buscarServicos()
                if(resposta.isSuccessful){
                    var listaServicos = resposta.body()?.toList()

                    if (listaServicos != null) {
                        servicos.addAll(listaServicos)
                    }

                    Log.i("api", "Serviços: ${servicos}")
                }else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch(e: Exception){
                Log.e("api", "Erro ao buscar servicos", e)
            }
        }
    }

    fun listarPecas(){
        viewModelScope.launch {
            try{
                var resposta = pitstopApi.buscarProdutos()
                if(resposta.isSuccessful){
                    var listaPecas = resposta.body()?.toList()

                    if (listaPecas != null) {
                        pecas.addAll(listaPecas)
                    }

                    Log.i("api", "Pecas: ${pecas.toList()}")
                }else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch(e: Exception){
                Log.e("api", "Erro ao buscar servicos", e)
            }
        }
    }

    fun listarOficinas(){
        viewModelScope.launch {
            try{
                val resposta = pitstopApi.listarTodos()
                Log.i("api", "Oficinas: ${resposta.body()}")
                if(resposta.isSuccessful){
                    var listaOficinas = resposta.body()?.toList()
                    oficinas.clear()
                    if (listaOficinas != null) {
                        oficinas.addAll(listaOficinas)
                    }
                    Log.i("api", "Serviços: ${servicos.toList()}")
                }else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch(e: Exception){
                Log.e("api", "Erro ao buscar servicos", e)
            }
        }
    }
}
