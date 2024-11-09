package com.example.futurobuscartelas.dto


data class UpdateUsuarioDTO(val name: String, val email: String)
data class SendEmailDTO(val email: String)
data class ConfirmTokenDTO(val email: String, val senha: String, val token: String)
data class UpdateSenhaUsuarioDTO(val currentPassword: String, val newPassword: String)
data class UpdateFotoUsuarioDTO(val photoUrl: String)
data class GoogleAuthDTO(val tokenId: String)
data class GoogleResponseDTO(val chave: String)
data class CreateUserGoogleDTO(val name: String, val email: String)
data class UpdatePreferenciasDTO(val preferences: List<String>)