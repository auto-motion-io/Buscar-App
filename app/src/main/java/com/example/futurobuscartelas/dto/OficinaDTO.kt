package com.example.futurobuscartelas.dto

import com.example.futurobuscartelas.models.Avaliacao
import com.example.futurobuscartelas.models.MediaAvaliacao
import java.io.Serializable

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
    var distance: Int?,
    var avaliacao: AvaliacaoDTO?,
    var mediaAvaliacao: MediaAvaliacaoDTO?,
    val informacoesOficina: InformacoesOficinaDTO?
) : Serializable

