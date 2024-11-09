package com.example.futurobuscartelas.koin

import java.time.LocalDateTime

/*
Classe que usaremos para ter a "sessão" do usuário
Outros atributos que ela poderia ter: email, URL da foto, perfil, id da empresa etc
 */
data class SessaoUsuario(
    var id: Int = 0,
    var nome: String = "",
    var token: String = "",
    var fotoUrl: String? = ""
)
