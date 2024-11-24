package com.example.futurobuscartelas.models

data class Produto(
    val id: Int,
    val nome: String,
    val modeloVeiculo: String,
    val quantidade: Int,
    val valorComMaobra: Double,
    val valorCompra: Double,
    val valorVenda: Double,
    val localizacao: String,
    val garantia: String
)
