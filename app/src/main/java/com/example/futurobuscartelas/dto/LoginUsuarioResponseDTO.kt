package com.example.futurobuscartelas.dto

data class LoginUsuarioResponseDTO(
    val idUsuario:Int,
    val email:String,
    val nome:String,
    val sobrenome:String,
    val token:String,
    val fotoUrl:String?
)
