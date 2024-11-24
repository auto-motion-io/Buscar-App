package com.example.futurobuscartelas.models

import com.google.gson.annotations.SerializedName

data class Servico(
    val id: Int,
    val nome: String,
    val descricao: String,
    val valorServico: Double,
    val garantia: String
)
