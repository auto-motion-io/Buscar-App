package com.example.futurobuscartelas.dto

data class CreateUsuarioDTO(
    val nome: String,
    val sobrenome: String,
    val email: String,
    val senha: String,
    val preferenciasVeiculos: String,
    val preferenciasPropulsao: String
)
