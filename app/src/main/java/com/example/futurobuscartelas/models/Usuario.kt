package com.example.futurobuscartelas.models

import com.google.gson.annotations.SerializedName

data class Usuario(
    val idUsuario: Int,
    val nome: String,
    val sobrenome: String,
    val email: String,
    val senha: String,
    @SerializedName("confirm_token") val confirmToken: String?,
    @SerializedName("foto_url") val fotoUrl: String?,
    @SerializedName("google_sub") val googleSub: String?,
    @SerializedName("preferencias_veiculo") val preferenciasVeiculo: String?,
    @SerializedName("preferencias_propulsao") val preferenciasPropulsao: String?
)
