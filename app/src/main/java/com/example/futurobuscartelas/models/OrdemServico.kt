package com.example.futurobuscartelas.models

import com.example.futurobuscartelas.dto.OficinaDTO
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
    val oficina: OficinaDTO,
    val placaVeiculo:String,
    val marcaVeiculo:String,
    val modeloVeiculo:String,
    val anoVeiculo:String,
    val emailCliente: String,
    val nomeCliente: String,
    val telefoneCliente: String,
    val corVeiculo:String,
    val nomeMecanico: String,
    val produtos: List<OSProduto>,
    val servicos: List<OSServico>,
) {
    data class OSProduto (
        val nome:String,
        val valor:Double,
        val quantidade:Int,
    )

    data class OSServico (
        val nome:String,
        val garantia:String,
        val valor:Double
    )
}
