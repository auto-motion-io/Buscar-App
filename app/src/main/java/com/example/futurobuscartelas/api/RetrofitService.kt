package com.example.futurobuscartelas.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL = "http://100.24.143.236:80/api/"

    fun getApiBuscar(): BuscarApi {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BuscarApi::class.java)
        return cliente
    }
}