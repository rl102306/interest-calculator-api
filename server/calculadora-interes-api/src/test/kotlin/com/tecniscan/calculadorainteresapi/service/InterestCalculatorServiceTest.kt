package com.tecniscan.calculadorainteresapi.service

import com.tecniscan.calculadorainteresapi.model.Calculation
import com.tecniscan.calculadorainteresapi.repository.CalculationRepository

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class InterestCalculatorServiceTest {

    // 1. Mock del repositorio
    private val mockRepository: CalculationRepository = mockk()

    // 2. Sistema bajo prueba (con el mock inyectado)
    private val service = InterestCalculatorService(mockRepository)

    @Test
    fun `calculateInterest should return correct calculation`() {
        // Configuración del mock
        every { mockRepository.save(any()) } returnsArgument 0

        // Ejecución
        val result = service.calculateInterest(1000.0, 5.0, 3)

        // Verificaciones
        assertEquals(1000.0, result.initialAmount)
        assertEquals(5.0, result.interestRate)
        assertEquals(3, result.years)
    }

    @Test
    fun `calculateInterest should calculate correct compound interest`() {
        every { mockRepository.save(any()) } answers {
            val calc = firstArg<Calculation>()
            calc.copy(id = 1L)
        }

        val result = service.calculateInterest(1000.0, 10.0, 2)

        // Verifica interés compuesto: 1000*(1.10)^2 = 1210
        assertEquals(1210.0, result.compoundInterestResults[1].amount, 0.01)
    }

    @Test
    fun `getHistory should return calculations ordered by date`() {
        // Datos de prueba
        val testData = listOf(
            Calculation(/* datos de prueba */),
            Calculation(/* datos de prueba */)
        )

        every { mockRepository.findAllByOrderByCreatedAtDesc() } returns testData

        val result = service.getHistory()

        assertEquals(2, result.size)
    }
}