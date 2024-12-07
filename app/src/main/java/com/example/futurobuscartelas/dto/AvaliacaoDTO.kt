package com.example.futurobuscartelas.dto

import com.example.futurobuscartelas.models.Usuario
import java.io.Serializable

class AvaliacaoDTO (
    val idAvaliacao: Int,
    var nota: Double,
    var comentario: String,
    var usuarioAvaliacao: Usuario,
    var oficina: OficinaDTO
) : Serializable