package com.example.futurobuscartelas.api
import com.example.futurobuscartelas.models.Oficina
import retrofit2.http.*
import retrofit2.Response


interface PitstopApi {
    @GET("oficinas")
    suspend fun listarTodos(): Response<List<Oficina>>
}