package com.example.futurobuscartelas.models

import com.google.gson.annotations.SerializedName

data class Veiculo(
    val id: Int,
    val placa: String,
    val marca: String,
    val modelo: String,
    val anoFabricante: Int,
    val cor: String
)
