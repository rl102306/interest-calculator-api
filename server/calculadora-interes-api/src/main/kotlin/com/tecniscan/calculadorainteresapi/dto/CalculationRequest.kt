package com.tecniscan.calculadorainteresapi.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class CalculationRequest(
    @field:NotNull

    val montoInicial: Double,

    @field:NotNull

    val tasaInteres: Double,

    @field:NotNull

    val anios: Int
)