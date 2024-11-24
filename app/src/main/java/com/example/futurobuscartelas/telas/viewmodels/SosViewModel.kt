package com.example.futurobuscartelas.telas.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.api.google.LocationRepository
import com.example.futurobuscartelas.location.UserLocation
import com.example.futurobuscartelas.models.Oficina
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SosViewModel : ViewModel() {
    val repository: LocationRepository = LocationRepository()
    val apiKey = "segredinho"

    private val pitstopApi: PitstopApi by inject(PitstopApi::class.java)

    val oficinas = mutableStateListOf<Oficina>()

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

    fun getOficinas() = oficinas.toList()

    @OptIn(DelicateCoroutinesApi::class)
    fun listarOficinas(context: Context) {
        GlobalScope.launch {
            try {
                val resposta = pitstopApi.listarTodos()
                var listaOficinas = resposta.body();
                if (resposta.isSuccessful) {
                    Log.i("api", "Oficinas da API: ${resposta.body()}")
                    oficinas.clear()
                    if (!listaOficinas.isNullOrEmpty()) {
                        listaOficinas.forEach { oficina ->
                            getDistanceFromOficina(context, oficina.cep) { distance ->
                                oficina.distance = distance
                                Log.i("Location", "distancia para oficina viewmodel${oficina.nome}: ${oficina.distance}")
                            }
                        }
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

    fun removeOficina(id: Int) {
        oficinas.removeAll { it.id == id }
    }
}