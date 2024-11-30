package com.example.futurobuscartelas.telas.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.models.Oficina
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class TelaInicialViewModel : ViewModel() {

    private val oficinas = mutableStateListOf<Oficina>()

    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)
    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)

    fun getOficinas() = oficinas.toList()

    fun listarOficinasFavoritas(id: Int) {
        viewModelScope.launch {
            try {
                val resposta = buscarApi.listarOficinas(id)
                if (resposta.isSuccessful) {
                    val listaOficinasFavoritas = resposta.body().orEmpty()
                    // Extraia apenas as oficinas
                    val listaOficinas = listaOficinasFavoritas.mapNotNull { it.oficina }
                    oficinas.clear()
                    oficinas.addAll(listaOficinas)
                    Log.i("api", "Oficinas: $listaOficinas")
                } else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar oficinas", e)
            }
        }
    }
}
