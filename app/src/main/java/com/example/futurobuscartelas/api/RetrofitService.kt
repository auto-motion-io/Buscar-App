package com.example.futurobuscartelas.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL_FILMES = "http://10.0.2.2:8080/"

    // função que retorna o cliente para a API de filmes
    fun getApiBuscar(): BuscarApi {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_FILMES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BuscarApi::class.java)
        return cliente
    }
}