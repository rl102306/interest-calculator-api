package com.tecniscan.calculadorainteresapi.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class CalculationRequest(
    @field:NotNull
    @field:Min(0.01.toLong(), message = "El monto debe ser mayor a 0")
    val montoInicial: Double,

    @field:NotNull
    @field:Min(0.01.toLong(), message = "La tasa debe ser mayor a 0")
    val tasaInteres: Double,

    @field:NotNull
    @field:Min(1, message = "Debe ser al menos 1 año")
    val anios: Int
)