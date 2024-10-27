package com.example.futurobuscartelas.models

import com.google.gson.annotations.SerializedName

data class Oficina(
    val id: Int,
    val nome: String,
    val cnpj: String,
    val cep: String,
    val numero: String,
    val complemento: String,
    val hasBuscar: Boolean,
    val logoUrl: String
)
