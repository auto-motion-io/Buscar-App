package com.example.futurobuscartelas.dto

data class OficinaDTO(
    val id:Int,
    val nome:String,
    val cnpj:String,
    val cep:String,
    var logradouro: String,
    var bairro: String,
    var cidade: String,
    val numero:String,
    val complemento:String,
    val hasBuscar:Boolean,
    val logoUrl:String,
    val informacoesOficinaDTO: InformacoesOficinaDTO
)
