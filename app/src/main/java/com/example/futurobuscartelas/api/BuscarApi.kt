package com.example.futurobuscartelas.api
import com.example.futurobuscartelas.models.Usuario
import retrofit2.Call
import retrofit2.http.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query


//DTOS UTILIZADAS

data class CreateUsuarioDTO(val nome: String,val sobrenome:String, val email: String, val senha: String,val preferenciasVeiculos:String, val preferenciasPropulsao:String)
data class LoginUsuarioRequest(val email: String, val password: String)
data class LoginUsuarioResponse(val token: String, val user: Usuario)
data class UpdateUsuarioDTO(val name: String, val email: String)
data class SendEmailDTO(val email: String)
data class ConfirmTokenDTO(val token: String)
data class UpdateSenhaUsuarioDTO(val currentPassword: String, val newPassword: String)
data class UpdateFotoUsuarioDTO(val photoUrl: String)
data class GoogleAuthDTO(val tokenId: String)
data class GoogleResponseDTO(val chave: String)
data class CreateUserGoogleDTO(val name: String, val email: String)
data class UpdatePreferenciasDTO(val preferences: List<String>)


interface BuscarApi {
    @GET("usuarios")
    suspend fun listarTodos(): Response<List<Usuario>>

    @GET("usuarios/{id}")
    suspend fun buscarPorId(@Path("id") id: Int): Response<Usuario>

    @POST("usuarios/cadastrar")
    suspend fun cadastrarUsuario(@Body usuario: CreateUsuarioDTO): Response<Usuario>

    @POST("usuarios/login")
    suspend fun login(@Body usuario: LoginUsuarioRequest): Response<LoginUsuarioResponse>

    @PUT("usuarios/{id}")
    suspend fun atualizar(@Path("id") id: Int, @Body updateUsuarioDTO: UpdateUsuarioDTO): Response<Usuario>

    @POST("usuarios/set-token")
    suspend fun setToken(
        @Body dto: SendEmailDTO,
        @Query("op") op: String
    ): Response<Unit>

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
}