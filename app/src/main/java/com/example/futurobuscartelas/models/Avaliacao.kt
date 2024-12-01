package com.example.futurobuscartelas.models

import java.util.Date

data class Avaliacao (
    val idAvaliacao: Int,
    var nota: Double,
    var comentario: String,
    var usuarioAvaliacao: Usuario,
    var oficina: Oficina
)