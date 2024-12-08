package com.example.futurobuscartelas.telas.viewmodels

import android.provider.Settings.Global
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.api.ViaCepApi
import com.example.futurobuscartelas.models.CepInfo
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OrdemServico
import com.example.futurobuscartelas.models.Produto
import com.example.futurobuscartelas.models.Servico
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class OrdemServicoViewModel : ViewModel(){
    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)
    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)
    private val viaCepApi: ViaCepApi by inject(ViaCepApi::class.java)

    val listaOS = mutableStateListOf<OrdemServico>()
    val oficinas = mutableStateListOf<Oficina>()
    val servicos = mutableStateListOf<Servico>()
    val pecas = mutableStateListOf<Produto>()

    var isLoading = mutableStateOf(false)
        private set

    fun getOficinas() = oficinas.toList()
    fun getListaOs() = listaOS.toList()
    fun getServicos() = servicos.toList()
    fun getPecas() = pecas.toList()

    fun listarOS(idUsuario: Int) {
        viewModelScope.launch {
            isLoading.value = true
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
                isLoading.value = false
            }
            isLoading.value = false
        }
    }

    fun listarOsPorToken(token: String){
        viewModelScope.launch {
            isLoading.value = true
            try{
                Log.i("API","Pré chamada da api")
                val resposta = pitstopApi.listarOsPorToken(token)
                if(resposta.isSuccessful){
                    resposta.body()?.let { listaOS.add(it) }
                } else{
                    Log.e("api", "Lista de OS erro: ${resposta.code()}")
                }
            } catch(exception: Exception){
                Log.e("api", "Erro ao buscar ordem de serviço", exception)
                isLoading.value = false
            }
                isLoading.value = false
        }
    }

    suspend fun listarOsPorTokenConsulta(token: String) : OrdemServico? {
        return try{
            Log.i("API","Pré chamada da api")
            val resposta = pitstopApi.listarOsPorToken(token)
            if(resposta.isSuccessful){
                resposta.body()
            } else{
                Log.e("api", "Lista de OS erro: ${resposta.code()}")
                null
            }
        } catch(exception: Exception){
            Log.e("api", "Erro ao buscar ordem de serviço", exception)
            null
        }
    }

    suspend fun retornarInfoCep(cep: String): CepInfo? {
        return try {
            val resposta = viaCepApi.getCep(cep)
            if (resposta.isSuccessful) {
                resposta.body().also {
                    Log.i("api", "InfoCEP da API: $it")
                }
            } else {
                Log.e("api", "Erro ao buscar cep: ${resposta.errorBody()?.string()}")
                null
            }
        } catch (exception: Exception) {
            Log.e("api", "Erro ao buscar cep", exception)
            null
        }
    }

    fun listarOficinaPorId(id: Int) {
        viewModelScope.launch {
            try {
                val resposta = pitstopApi.listarOficinaPorId(id)
                Log.i("api", "Oficinas: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    val oficina = resposta.body()
                    resposta.body()?.cep?.let {
                        retornarInfoCep(it)?.let { cepInfo ->
                            oficina?.logradouro = cepInfo.logradouro ?: ""
                            oficina?.bairro = cepInfo.bairro ?: ""
                            oficina?.cidade = cepInfo.localidade ?: ""
                        }
                    }
                    if (oficina != null) {
                        oficinas.add(oficina)
                        Log.i("api", "Oficinas: ${oficinas.toList()}")
                    }
                    Log.i("api", "Oficinas: $")
                } else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar oficinas", e)
            }
        }
    }

    fun listarServicosPorOficina(id: Int){
        viewModelScope.launch {
            try{
                val resposta = pitstopApi.buscarServicosPorOficina(id)
                Log.i("api", "Serviços: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    val listaServicos = resposta.body().orEmpty()
                    Log.i("api", "Lista de OS retornada: $listaServicos")

                    servicos.clear()
                    servicos.addAll(listaServicos)
                } else {
                    Log.e("api", "Erro ao buscar Serviços: ${resposta.errorBody()?.string()}")
                }
            } catch (e: Exception){
                Log.e("api", "Erro ao buscar servicos", e)
            }
        }
    }

    fun listarPecasPorOficina(id: Int){
        viewModelScope.launch {
            try{
                val resposta = pitstopApi.buscarProdutosPorOficina(id)
                Log.i("api", "Serviços: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    val listaPecas = resposta.body().orEmpty()
                    Log.i("api", "Lista de OS retornada: $listaPecas")

                    pecas.clear()
                    pecas.addAll(listaPecas)
                } else {
                    Log.e("api", "Erro ao buscar Serviços: ${resposta.errorBody()?.string()}")
                }
            } catch (e: Exception){
                Log.e("api", "Erro ao buscar servicos", e)
            }
        }
    }
}