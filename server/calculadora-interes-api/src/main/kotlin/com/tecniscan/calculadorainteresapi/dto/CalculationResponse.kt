package com.tecniscan.calculadorainteresapi.dto

import com.tecniscan.calculadorainteresapi.model.Calculation
import java.time.format.DateTimeFormatter

data class CalculationResponse(
    val id: Long?,
    val fecha: String,
    val interes_compuesto: List<YearlyResultResponse>,
    val interes_simple: List<YearlyResultResponse>
) {
    companion object {
        fun fromEntity(calculation: Calculation): CalculationResponse {
            return CalculationResponse(
                id = calculation.id,
                fecha = calculation.createdAt.format(DateTimeFormatter.ISO_DATE_TIME),
                interes_compuesto = calculation.compoundInterestResults.map {
                    YearlyResultResponse(it.year, it.amount)
                },
                interes_simple = calculation.simpleInterestResults.map {
                    YearlyResultResponse(it.year, it.amount)
                }
            )
        }
    }
}