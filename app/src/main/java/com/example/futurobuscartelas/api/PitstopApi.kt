package com.example.futurobuscartelas.api
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OrdemServico
import retrofit2.http.*
import retrofit2.Response


interface PitstopApi {
    @GET("oficinas")
    suspend fun listarTodos(): Response<List<Oficina>>

    @GET("ordemDeServicos/cliente/{email}")
    suspend fun listarOS(@Path("email") email: String): Response<List<OrdemServico>>
}