package com.tecniscan.calculadorainteresapi.dto

data class ApiError(
    val status: Int,
    val message: String,
    val timestamp: String = java.time.LocalDateTime.now().toString()
)