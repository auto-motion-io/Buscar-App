package com.example.futurobuscartelas.api
import com.example.futurobuscartelas.dto.*
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.Usuario
import retrofit2.Call
import retrofit2.http.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query


interface PitstopApi {
    @GET("oficinas")
    suspend fun listarTodos(): Response<List<Oficina>>
}