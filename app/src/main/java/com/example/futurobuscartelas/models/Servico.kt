package com.example.futurobuscartelas.models

data class Servico(
    val id: Int,
    val nome: String,
    val descricao: String,
    val valorServico: Double,
    val oficina: Oficina
)
