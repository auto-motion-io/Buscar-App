package com.example.futurobuscartelas.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL = "http://54.159.46.101/api/"
    val BASE_URL_PITSTOP = "http://34.226.28.195/api/"

    fun getApiBuscar(): BuscarApi {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BuscarApi::class.java)
        return cliente
    }

    fun getApiPitstop(): PitstopApi {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_PITSTOP)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PitstopApi::class.java)
        return cliente
    }
}