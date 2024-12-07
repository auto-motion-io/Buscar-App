package com.example.futurobuscartelas.api
import com.example.futurobuscartelas.dto.OficinaDTO
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OrdemServico
import com.example.futurobuscartelas.models.Produto
import com.example.futurobuscartelas.models.Servico
import retrofit2.http.*
import retrofit2.Response


interface PitstopApi {
    @GET("oficinas")
    suspend fun listarTodos(): Response<List<OficinaDTO>>

    @GET("oficinas/{id}")
    suspend fun listarOficinaPorId(@Path("id") id: Int): Response<Oficina>

    @GET("ordemDeServicos/cliente/{email}")
    suspend fun listarOS(@Path("email") email: String): Response<List<OrdemServico>>

    @GET("ordemDeServicos/token/{token}")
    suspend fun listarOsPorToken(@Path("token") token: String): Response<OrdemServico>

    @GET("buscar-servicos")
    suspend fun buscarServicos() : Response<List<Servico>>

    @GET("buscar-servicos/oficina/{idOficina}")
    suspend fun buscarServicosPorOficina(@Path("idOficina") int: Int) : Response<List<Servico>>

    @GET("produtoEstoque")
    suspend fun buscarProdutos() : Response<List<Produto>>

    @GET("produtoEstoque/oficina/{idOficina}")
    suspend fun buscarProdutosPorOficina(@Path("idOficina") int: Int) : Response<List<Produto>>
}