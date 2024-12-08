package com.example.futurobuscartelas.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OficinaFavorita(
    val idOficinaFavorita: Int,
    val usuario: Usuario,
    val oficina: Oficina
) : Serializable

