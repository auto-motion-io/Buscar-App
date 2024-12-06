package com.example.futurobuscartelas.dto

data class InformacoesOficinaDTO (
    val id:Int,
    val whatsapp:String,
    val horarioIniSem:String,
    val horarioFimSem:String,
    val horarioIniFds:String,
    val horarioFimFds:String,
    val diasSemanaAberto:String,
    val tipoVeiculosTrabalha:String,
    val tipoPropulsaoTrabalha:String,
    val marcasVeiculosTrabalha:String
)