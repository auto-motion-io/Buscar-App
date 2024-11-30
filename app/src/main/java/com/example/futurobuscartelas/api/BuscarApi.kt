package com.example.futurobuscartelas.api
import com.example.futurobuscartelas.dto.*
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.OficinaFavorita
import com.example.futurobuscartelas.models.OrdemServico
import com.example.futurobuscartelas.models.Usuario
import retrofit2.Call
import retrofit2.http.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query


interface BuscarApi {
    @GET("usuarios")
    suspend fun listarTodos(): Response<List<Usuario>>

    @GET("usuarios/{id}")
    suspend fun buscarPorId(@Path("id") id: Int): Response<Usuario>

    @POST("usuarios/cadastrar")
    suspend fun cadastrarUsuario(@Body usuario: CreateUsuarioDTO): Response<Usuario>

    @POST("usuarios/login")
    suspend fun login(@Body usuario: LoginUsuarioDTO): Response<LoginUsuarioResponseDTO>

    @PUT("usuarios/{id}")
    suspend fun atualizar(@Path("id") id: Int, @Body updateUsuarioDTO: UpdateUsuarioDTO): Response<Usuario>

    @POST("usuarios/set-token")
    suspend fun setToken(
        @Body dto: SendEmailDTO,
        @Query("op") op: String
    ):Response<Void>

    @POST("usuarios/confirmar-token")
    suspend fun confirmarToken(
        @Body dto: ConfirmTokenDTO,
        @Query("op") op: String
    ): Response<Unit>

    @PUT("usuarios/atualizar-senha/{id}")
    suspend fun atualizarSenha(
        @Path("id") id: Int,
        @Body updateSenhaUsuarioDTO: UpdateSenhaUsuarioDTO
    ): Response<Usuario>

    @DELETE("usuarios/{id}")
    suspend fun deletar(@Path("id") id: Int): Response<String>

    @PUT("usuarios/atualizar-foto/{id}")
    suspend fun atualizarFoto(
        @Path("id") id: Int,
        @Body updateFotoUsuarioDTO: UpdateFotoUsuarioDTO
    ): Response<Usuario>

    @POST("usuarios/login-google")
    suspend fun loginGoogle(@Body googleAuthDTO: GoogleAuthDTO): Response<GoogleResponseDTO>

    @POST("usuarios/cadastrar-google")
    suspend fun cadastrarGoogle(@Body googleUser: CreateUserGoogleDTO): Response<Usuario>

    @PUT("usuarios/atualizar-preferencias/{id}")
    suspend fun atualizarPreferencias(
        @Path("id") id: Int,
        @Body dto: UpdatePreferenciasDTO
    ): Response<Usuario>

    @GET("usuarios/{id}/oficinas-favoritas")
    suspend fun listarOficinas(@Path("id") id: Int): Response<List<OficinaFavorita>>

    @POST("usuarios/{idUsuario}/oficinas-favoritas/{idOficina}")
    suspend fun favoritar(
        @Path("idUsuario") idUsuario: Int,
        @Path("idOficina") idOficina: Int
    ): Response<Unit>

    @DELETE("usuarios/{idUsuario}/oficinas-favoritas/{idOficina}")
    suspend fun remover(
        @Path("idUsuario") idUsuario: Int,
        @Path("idOficina") idOficina: Int
    ): Response<Unit>

    @GET("ordemDeServicos/cliente/{id}")
    suspend fun listarOsPorCliente(@Path("id") id: Int): Response<List<OrdemServico>>
}