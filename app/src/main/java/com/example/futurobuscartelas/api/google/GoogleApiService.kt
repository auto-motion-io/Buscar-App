package com.example.futurobuscartelas.api.google

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApiService {

    // Geocoding API: Obter coordenadas a partir do endereço
    @GET("geocode/json")
    fun getCoordinates(
        @Query("address") address: String,
        @Query("key") apiKey: String
    ): Call<GeocodeResponse>

    // Distance Matrix API: Obter distância entre dois locais
    @GET("distancematrix/json")
    fun getDistance(
        @Query("origins") origins: String,
        @Query("destinations") destinations: String,
        @Query("key") apiKey: String
    ): Call<DistanceMatrixResponse>
}
