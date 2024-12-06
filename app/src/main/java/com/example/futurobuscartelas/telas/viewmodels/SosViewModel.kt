package com.example.futurobuscartelas.telas.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.BuildConfig
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.api.ViaCepApi
import com.example.futurobuscartelas.api.google.LocationRepository
import com.example.futurobuscartelas.models.Avaliacao
import com.example.futurobuscartelas.models.CepInfo
import com.example.futurobuscartelas.models.MediaAvaliacao
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OficinaFavorita
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SosViewModel : ViewModel() {
    val repository: LocationRepository = LocationRepository()
    val apiKey = "AIzaSyAV1EbJzFHWyS6ZAdW4b0SqI-6vlGd73Ys"

    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)
    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)
    private val viaCepApi: ViaCepApi by inject(ViaCepApi::class.java)

    val oficinas = mutableStateListOf<Oficina>()
    val oficinasFavoritas = mutableStateListOf<OficinaFavorita>()

    fun getOficinas() = oficinas.toList()
    fun getOficinasFavoritas() = oficinasFavoritas.toList()

    fun getDistanceFromOficina(
        context: Context,
        targetCep: String,
        callback: (Int?) -> Unit
    ) {
        val userLocation = repository.getLocation(context)
        GlobalScope.launch {
            repository.fetchCoordinates(apiKey, targetCep) { coordinates ->
                if (coordinates != null) {
                    val currentLat = -23.457142
                    val currentLon = -46.692007

                    //val currentLat = userLocation.latitude
                    //val currentLon = userLocation.longitude

                    val origins = "$currentLat,$currentLon"
                    val destinations = "${coordinates.first},${coordinates.second}"

                    repository.fetchDistance(apiKey, origins, destinations) { distance ->
                        if (distance != null) {
                            Log.i("Location", "Distance: $distance")
                        } else {
                            Log.i("Location", "Erro ao obter distancia.")
                        }
                        callback(distance)
                    }
                } else {
                    callback(null)
                    Log.i("Location", "Erro ao obter as coordenadas.")
                }
            }
        }
    }

    fun listarOficinas(context: Context) {
        viewModelScope.launch {
            try {
                val resposta = pitstopApi.listarTodos()
                val listaOficinas = resposta.body()
                if (resposta.isSuccessful) {
                    Log.i("api", "Oficinas da API: ${resposta.body()}")
                    oficinas.clear()
                    if (!listaOficinas.isNullOrEmpty()) {
                        // Use async para garantir que todas as tarefas assíncronas sejam completadas antes de adicionar
                        val oficinaTasks = listaOficinas.map { oficina ->
                            async {
                                // Obtém a distância da oficina
                                getDistanceFromOficina(context, oficina.cep) { distance ->
                                    oficina.distance = distance
                                    Log.i("Location", "distancia para oficina viewmodel ${oficina.nome}: ${oficina.distance}")
                                }

                                // Preenche os campos de endereço da oficina com a função retornada
                                retornarInfoCep(oficina.cep)?.let { cepInfo ->
                                    oficina.logradouro = cepInfo.logradouro ?: ""
                                    oficina.bairro = cepInfo.bairro ?: ""
                                    oficina.cidade = cepInfo.localidade ?: ""
                                    Log.i("api", "CEP atualizado para oficina ${oficina.nome}: ${oficina.logradouro}, ${oficina.bairro}, ${oficina.cidade}")
                                }

                                // Busca a avaliação da oficina
                                buscarMediaAvaliacao(oficina.id)?.let { avaliacao ->
                                    oficina.mediaAvaliacao = avaliacao
                                }
                            }
                        }

                        // Espera todos os cálculos assíncronos serem completados
                        oficinaTasks.awaitAll()

                        // Agora que todas as informações estão carregadas, adicione as oficinas à lista
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

    fun listarOficinasFavoritas(id: Int) {
        viewModelScope.launch {
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

    suspend fun buscarAvaliacao(idOficina: Int): Avaliacao? {
        return try {
            val resposta = buscarApi.buscarAvaliacao(idOficina)
            if (resposta.isSuccessful) {
                val listaAvaliacoes = resposta.body()  // Aqui espera-se uma lista
                listaAvaliacoes?.firstOrNull()
            } else {
                Log.e("api", "Erro ao buscar avaliação: ${resposta.errorBody()?.string()}")
                null
            }
        } catch (exception: Exception) {
            Log.e("api", "Erro ao buscar avaliação: ", exception)
            null
        }
    }

    suspend fun buscarMediaAvaliacao(idOficina: Int): MediaAvaliacao? {
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

}