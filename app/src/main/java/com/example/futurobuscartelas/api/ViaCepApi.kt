package com.example.futurobuscartelas.api

import com.example.futurobuscartelas.models.CepInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {
    @GET("{cep}/json")
    suspend fun getCep(@Path("cep") cep: String): Response<CepInfo>
}