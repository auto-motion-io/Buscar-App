package com.example.futurobuscartelas.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class OrdemServico(
    val id: Int,
    val dataInicio: Date,
    val dataFim: Date,
    val status: String,
    val desconto: Double,
    val valorTotal: Double,
    val token: String,
    val tipoOs: String,
    val garantia: String,
    val observacoes: String,
    val veiculo: Veiculo,
    val mecanico: Mecanico,
    val produtos: List<Produto>,
    val servicos: List<Servico>
)
