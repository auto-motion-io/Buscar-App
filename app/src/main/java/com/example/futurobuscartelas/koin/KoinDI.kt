package com.example.futurobuscartelas.koin


import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.PitstopApi
import com.example.futurobuscartelas.api.RetrofitService
import org.koin.dsl.module


val appModule = module {

    single<SessaoUsuario> {
        // estamos dizendo para o Koin como criar um objeto do tipo SessaoUsuario para o primeiro que pedir
        // da 2a vez em diante, usará a instância criada anteriormente
        SessaoUsuario()
    }

    single<BuscarApi> {
        // estamos dizendo para o Koin como criar um objeto do tipo ApiFilmes para o primeiro que pedir
        // o get<SessaoUsuario>() é uma forma de pedir ao Koin para entregar um objeto do tipo SessaoUsuario
        RetrofitService.getApiBuscar(get<SessaoUsuario>().token)
    }

    single<PitstopApi> {
        RetrofitService.getApiPitstop()
    }
}
