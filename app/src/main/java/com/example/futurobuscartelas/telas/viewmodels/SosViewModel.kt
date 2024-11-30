package com.example.futurobuscartelas.telas.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.api.ViaCepApi
import com.example.futurobuscartelas.api.google.LocationRepository
import com.example.futurobuscartelas.location.UserLocation
import com.example.futurobuscartelas.models.CepInfo
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OficinaFavorita
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SosViewModel : ViewModel() {
    val repository: LocationRepository = LocationRepository()
    val apiKey = ""

    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)
    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)
    private val viaCepApi: ViaCepApi by inject(ViaCepApi::class.java)

    val oficinas = mutableStateListOf<Oficina>()
    val oficinasFavoritas = mutableStateListOf<OficinaFavorita>()
    var infoCep = mutableStateOf<CepInfo?>(null)

    fun getCep() = infoCep;
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
                        // Calculando a distância para cada oficina
                        listaOficinas.forEach { oficina ->
                            getDistanceFromOficina(context, oficina.cep) { distance ->
                                oficina.distance = distance
                                Log.i("Location", "distancia para oficina viewmodel${oficina.nome}: ${oficina.distance}")
                                // Adicionando a oficina após calcular a distância
                                oficinas.add(oficina)
                            }
                        }
                    }
                } else {
                    Log.e("api", "Erro ao buscar oficinas: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar oficinas", exception)
            }
        }
    }

    fun retornarInfoCep(cep: String){
        viewModelScope.launch {
            try {
                val resposta = viaCepApi.getCep(cep)
                if (resposta.isSuccessful) {
                    val cepInfo = resposta.body()
                    infoCep.value = cepInfo
                    Log.i("api", "InfoCEP da API: ${resposta.body()}")
                } else {
                    Log.e("api", "Erro ao buscar cep: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar cep", exception)
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
}