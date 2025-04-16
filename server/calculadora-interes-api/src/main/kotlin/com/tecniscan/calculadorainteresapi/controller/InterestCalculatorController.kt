package com.tecniscan.calculadorainteresapi.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.tecniscan.calculadorainteresapi.service.InterestCalculatorService
import com.tecniscan.calculadorainteresapi.dto.CalculationResponse
import com.tecniscan.calculadorainteresapi.dto.CalculationRequest
import com.tecniscan.calculadorainteresapi.dto.ApiError



@RestController
@RequestMapping("/api")
class InterestCalculatorController(
    private val service: InterestCalculatorService
) {
    // POST /api/calcular-interes
    @PostMapping("/calcular-interes")
    fun calculateInterest(
        @RequestBody request: CalculationRequest
    ): ResponseEntity<Any> {
        return try {
            val calculation = service.calculateInterest(request)
            ResponseEntity.ok(CalculationResponse.fromEntity(calculation))
        } catch (ex: IllegalArgumentException) {
            ResponseEntity.badRequest().body(
                ApiError(HttpStatus.BAD_REQUEST.value(), ex.message ?: "Error")
            )
        }
    }

    // GET /api/historial-calculos
    @GetMapping("/historial-calculos")
    fun getHistory(): ResponseEntity<List<CalculationResponse>> {
        val calculations = service.getHistory()
        return ResponseEntity.ok(
            calculations.map { CalculationResponse.fromEntity(it) }
        )
    }
}