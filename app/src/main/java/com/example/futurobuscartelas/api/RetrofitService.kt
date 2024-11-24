package com.example.futurobuscartelas.api

import android.util.Log
import com.example.futurobuscartelas.api.PitstopApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    //val BASE_URL_BUSCAR = "http://10.0.2.2:8081/api/"
    //val BASE_URL_PITSTOP = "http://10.0.2.2:8081/api/"

    val BASE_URL_BUSCAR = "http://52.206.85.157/api/"
    val BASE_URL_PITSTOP = "http://52.204.58.45/api/"
    fun getApiBuscar(token: String): BuscarApi {

        /*
        cliente OkHttp que tem um interceptor, o InterceptorTokenJWT
         */
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(InterceptorTokenJWT(token))
            .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .build()

        val cliente = Retrofit.Builder()
            .client(okHttpClient) // aqui estamos dizendo que o cliente Retrofit usará o cliente OkHttp que criamos
            .baseUrl(BASE_URL_BUSCAR).addConverterFactory(GsonConverterFactory.create()).build()
            .create(BuscarApi::class.java)
        return cliente
    }

    fun getApiPitstop(): PitstopApi {
        val cliente = Retrofit.Builder().baseUrl(BASE_URL_PITSTOP)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PitstopApi::class.java)
        return cliente
    }


}

/*
Classe que implementa um Interceptor para adicionar um token JWT a TODAS as requisições
*/
class InterceptorTokenJWT(val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()

        val requestPath = chain.request().url.encodedPath

        val shouldAddAuthHeader =
            !requestPath.contains("/login") &&
                !requestPath.contains("/cadastrar") &&
                !requestPath.contains("/set-token") &&
                !requestPath.contains("/confirmar-token")

        Log.i("api", "Path = ${requestPath}, should add auth header? $shouldAddAuthHeader")

        if (shouldAddAuthHeader && token.isNotEmpty()) {
            Log.i("api", "adding token to the request")
            currentRequest.addHeader("Authorization", "Bearer $token")
        }
        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}
