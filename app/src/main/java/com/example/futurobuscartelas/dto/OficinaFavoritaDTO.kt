package com.example.futurobuscartelas.dto

import com.example.futurobuscartelas.models.Avaliacao
import com.example.futurobuscartelas.models.MediaAvaliacao
import com.example.futurobuscartelas.models.Oficina
import com.example.futurobuscartelas.models.Usuario
import java.io.Serializable

data class OficinaFavoritaDTO(
    val id:Int,
    val oficina: OficinaDTO,
    val usuario: Usuario,
) : Serializable

