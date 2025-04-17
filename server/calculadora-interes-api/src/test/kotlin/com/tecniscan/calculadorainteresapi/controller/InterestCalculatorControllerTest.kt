package com.tecniscan.calculadorainteresapi.controller

import com.tecniscan.calculadorainteresapi.dto.CalculationRequest
import com.tecniscan.calculadorainteresapi.service.InterestCalculatorService
import io.mockk.every
import io.mockk.mockk


import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

class InterestCalculatorControllerTest {

    private val mockService: InterestCalculatorService = mockk()
    private val controller = InterestCalculatorController(mockService)

    @Test
    fun `POST calcular-interes should return 200 OK`() {
        // Configuración
        val request = CalculationRequest(1000.0, 5.0, 3)
        every { mockService.calculateInterest(any()) } returns mockCalculation()

        // Ejecución
        val response = controller.calculateInterest(request)

        // Verificación
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `GET historial-calculos should return 200`() {
        every { mockService.getHistory() } returns emptyList()

        val response = controller.getHistory()

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    private fun mockCalculation() = Calculation(
        initialAmount = 1000.0,
        interestRate = 5.0,
        years = 3,
        compoundInterestResults = emptyList(),
        simpleInterestResults = emptyList()
    )
}