package com.example.futurobuscartelas.dto

import java.io.Serializable

class MediaAvaliacaoDTO (
    val idOficina: Int,
    val nota: Double,
    val quantidadeAvaliacoes: Int
) : Serializable