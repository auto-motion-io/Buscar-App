package com.example.futurobuscartelas.telas.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.models.OrdemServico
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class OrdemServicoViewModel : ViewModel(){
    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)
    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)

    val listaOS = mutableStateListOf<OrdemServico>()

    fun getListaOs() = listaOS.toList()

    fun listarOS(idUsuario: Int) {
        GlobalScope.launch {
            try {
                val usuario = buscarApi.buscarPorId(idUsuario)
                Log.i("api", "Usuário retornado: ${usuario.body()}")

                val email = usuario.body()?.email
                Log.i("api", "Email para buscar OS: $email")

                val resposta = email?.let { pitstopApi.listarOS(it) }
                Log.i("api", "Email para buscar OS: $resposta")

                if (resposta != null) {
                    Log.i("api", "Código de status da resposta: ${resposta.code()}")
                    Log.i("api", "Corpo da resposta: ${resposta.body().toString()}")

                    if (resposta.isSuccessful) {
                        val listaOrdemServicos = resposta.body().orEmpty()
                        Log.i("api", "Lista de OS retornada: $listaOrdemServicos")

                        listaOS.clear()
                        listaOS.addAll(listaOrdemServicos)
                    } else {
                        Log.e("api", "Erro na API: ${resposta.errorBody()?.string()}")
                    }
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar ordens de serviço", exception)
            }
        }

    }
}