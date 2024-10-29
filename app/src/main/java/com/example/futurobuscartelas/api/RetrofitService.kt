package com.example.futurobuscartelas.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    //val BASE_URL_BUSCAR = "http://10.0.2.2:8081/api/"
    //val BASE_URL_PITSTOP = "http://10.0.2.2:8081/api/"

    val BASE_URL_BUSCAR = "http://52.206.85.157/api/"
    val BASE_URL_PITSTOP = "http://52.204.58.45/api/"
    fun getApiBuscar(): BuscarApi {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_BUSCAR)
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