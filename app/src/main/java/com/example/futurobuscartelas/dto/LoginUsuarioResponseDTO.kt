package com.example.futurobuscartelas.dto

data class LoginUsuarioResponseDTO(
    val idUsuario:Int,
    val nome:String,
    val token:String,
    val fotoUrl:String?
)
