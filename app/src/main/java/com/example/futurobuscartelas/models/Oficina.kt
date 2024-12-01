package com.example.futurobuscartelas.models

import com.google.gson.annotations.SerializedName

data class Oficina(
    val id: Int,
    val nome: String,
    val cnpj: String,
    val cep: String,
    var logradouro: String,
    var bairro: String,
    var cidade: String,
    val numero: String,
    val complemento: String,
    val hasBuscar: Boolean,
    val logoUrl: String,
    var distance: Int?,
    var avaliacao: Avaliacao?,
    var mediaAvaliacao: MediaAvaliacao?
)
