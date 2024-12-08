package com.example.futurobuscartelas.telas.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.api.ViaCepApi
import com.example.futurobuscartelas.dto.AvaliacaoDTO
import com.example.futurobuscartelas.dto.MediaAvaliacaoDTO
import com.example.futurobuscartelas.dto.OficinaDTO
import com.example.futurobuscartelas.models.CepInfo
import com.example.futurobuscartelas.models.MediaAvaliacao
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OficinaFavorita
import com.example.futurobuscartelas.models.Produto
import com.example.futurobuscartelas.models.Servico
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class TelaInicialViewModel : ViewModel() {

    private val oficinasFavoritas = mutableStateListOf<OficinaFavorita>()
    private val oficinas = mutableStateListOf<OficinaDTO>()
    private val servicos = mutableStateListOf<Servico>()
    private val pecas = mutableStateListOf<Produto>()
    private val avaliacoes = mutableStateListOf<AvaliacaoDTO>()

    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)
    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)
    private val viaCepApi: ViaCepApi by inject(ViaCepApi::class.java)

    var isLoading = mutableStateOf(false)
        private set

    fun getOficinasFavoritas() = oficinasFavoritas.toList()
    fun getOficinas() = oficinas.toList()
    fun getServicos() = servicos.toList()
    fun getPecas() = pecas.toList()
    fun getAvaliacoes() = avaliacoes.toList()

    fun listarOficinasFavoritas(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val resposta = buscarApi.listarOficinas(id)
                if (resposta.isSuccessful) {
                    val listaOficinasFavoritas = resposta.body().orEmpty()
                    // Extraia apenas as oficinas
                    oficinasFavoritas.clear()
                    oficinasFavoritas.addAll(listaOficinasFavoritas)
                    Log.i("api", "Oficinas: $listaOficinasFavoritas")
                } else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar oficinas", e)
                isLoading.value = false
            }
            isLoading.value = false

        }
    }

    fun listarServicos() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val resposta = pitstopApi.buscarServicos()
                if (resposta.isSuccessful) {
                    var listaServicos = resposta.body()?.toList()

                    if (listaServicos != null) {
                        servicos.addAll(listaServicos)
                    }

                    Log.i("api", "Serviços: ${servicos}")
                } else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar servicos", e)
                isLoading.value = false
            }
            isLoading.value = false
        }
    }

    fun listarServicosPorOficina(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            try {
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
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar servicos", e)
                isLoading.value = false
            }
            isLoading.value = false
        }
    }

    fun listarPecas() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                var resposta = pitstopApi.buscarProdutos()
                if (resposta.isSuccessful) {
                    var listaPecas = resposta.body()?.toList()

                    if (listaPecas != null) {
                        pecas.addAll(listaPecas)
                    }

                    Log.i("api", "Pecas: ${pecas.toList()}")
                } else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar servicos", e)
                isLoading.value = false
            }
            isLoading.value = false
        }
    }

    fun listarPecasPorOficina(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            try {
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
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar servicos", e)
                isLoading.value = false
            }
            isLoading.value = false
        }
    }

    fun listarOficinas() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val resposta = pitstopApi.listarTodos()
                val listaOficinas = resposta.body()
                Log.i("api", "Oficinas: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    oficinas.clear()
                    if (!listaOficinas.isNullOrEmpty()) {
                        val oficinaTasks = listaOficinas.map { oficina ->
                            async {

                                // Preenche os campos de endereço da oficina com a função retornada
                                retornarInfoCep(oficina.cep)?.let { cepInfo ->
                                    oficina.logradouro = cepInfo.logradouro ?: ""
                                    oficina.bairro = cepInfo.bairro ?: ""
                                    oficina.cidade = cepInfo.localidade ?: ""
                                    Log.i(
                                        "api",
                                        "CEP atualizado para oficina ${oficina.informacoesOficina}: ${oficina.logradouro}, ${oficina.bairro}, ${oficina.cidade}"
                                    )
                                }

                                // Busca a avaliação da oficina
                                buscarMediaAvaliacao(oficina.id)?.let { avaliacao ->
                                    oficina.mediaAvaliacao = avaliacao
                                }
                            }
                        }
                        oficinaTasks.awaitAll()

                        oficinas.addAll(listaOficinas)
                    }
                    Log.i("api", "Serviços: ${servicos.toList()}")
                } else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar servicos", e)
                isLoading.value = false
            }
            isLoading.value = false
        }
    }

    fun listarOficinas2() {
        viewModelScope.launch {
            try {
                val resposta = pitstopApi.listarTodos()
                val listaOficinas = resposta.body()
                Log.i("api", "Oficinas: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    oficinas.clear()
                    if (!listaOficinas.isNullOrEmpty()) {
                        val oficinaTasks = listaOficinas.map { oficina ->
                            async {

                                // Preenche os campos de endereço da oficina com a função retornada
                                retornarInfoCep(oficina.cep)?.let { cepInfo ->
                                    oficina.logradouro = cepInfo.logradouro ?: ""
                                    oficina.bairro = cepInfo.bairro ?: ""
                                    oficina.cidade = cepInfo.localidade ?: ""
                                    Log.i(
                                        "api",
                                        "CEP atualizado para oficina ${oficina.informacoesOficina}: ${oficina.logradouro}, ${oficina.bairro}, ${oficina.cidade}"
                                    )
                                }

                                // Busca a avaliação da oficina
                                buscarMediaAvaliacao(oficina.id)?.let { avaliacao ->
                                    oficina.mediaAvaliacao = avaliacao
                                }
                            }
                        }
                        oficinaTasks.awaitAll()

                        oficinas.addAll(listaOficinas)
                    }
                    Log.i("api", "Serviços: ${servicos.toList()}")
                } else {
                    Log.e("api", "Erro na API: Código ${resposta.code()} - ${resposta.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar servicos", e)
            }
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

    suspend fun buscarMediaAvaliacao(idOficina: Int): MediaAvaliacaoDTO? {
        return try {
            val resposta = buscarApi.mediaAvaliacaoOficina(idOficina)
            if (resposta.isSuccessful) {
                resposta.body().also {
                    Log.i("api", "Medias de avaliacao da oficina: $it")
                }
            } else {
                Log.e("api", "Erro ao buscar avaliação: ${resposta.errorBody()?.string()}")
                null
            }
        } catch (exception: Exception) {
            Log.e("api", "Erro ao buscar avaliação: ", exception)
            null
        }
    }

    fun buscarAvaliacoes(idOficina: Int) {
        viewModelScope.launch {
            try {
                val resposta = buscarApi.buscarAvaliacao(idOficina)
                Log.i("api", "Serviços: ${resposta.body()}")
                if (resposta.isSuccessful) {
                    val listaAvaliacoes = resposta.body().orEmpty()
                    Log.i("api", "Lista de OS retornada: $listaAvaliacoes")

                    avaliacoes.clear()
                    avaliacoes.addAll(listaAvaliacoes)
                } else {
                    Log.e("api", "Erro ao buscar Serviços: ${resposta.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao buscar servicos", e)
            }
        }
    }

    fun favoritarOficina(idUsuario: Int, idOficina: Int) {
        GlobalScope.launch {
            try {
                // Fazendo o POST para favoritar uma oficina
                val resposta = buscarApi.favoritar(idUsuario, idOficina)

                if (resposta.isSuccessful) {
                    Log.i("api", "Oficina ${idOficina} favoritada com sucesso.")
                } else {
                    Log.e("api", "Erro ao favoritar oficina: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao favoritar oficina", exception)
            }
        }
    }

    fun removeOficina(idUsuario: Int, idOficina: Int) {
        GlobalScope.launch {
            try {
                // Fazendo o POST para favoritar uma oficina
                val resposta = buscarApi.remover(idUsuario, idOficina)

                if (resposta.isSuccessful) {
                    Log.i("api", "Oficina ${idOficina} favoritada com sucesso.")
                } else {
                    Log.e("api", "Erro ao favoritar oficina: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao favoritar oficina", exception)
            }
        }
    }
}
