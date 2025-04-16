package com.tecniscan.calculadorainteresapi.service
import com.tecniscan.calculadorainteresapi.model.Calculation
import com.tecniscan.calculadorainteresapi.repository.CalculationRepository
import com.tecniscan.calculadorainteresapi.dto.CalculationRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.pow

@Service
class InterestCalculatorService(
    private val repository: CalculationRepository
) {

    @Transactional
    fun calculateInterest(request: CalculationRequest): Calculation {
        validateInput(request)

        val compoundResults = (1..request.anios).map { year ->
            Calculation.YearlyResult(
                year = year,
                amount = calculateCompoundInterest(request.montoInicial, request.tasaInteres, year)
            )
        }

        val simpleResults = (1..request.anios).map { year ->
            Calculation.YearlyResult(
                year = year,
                amount = calculateSimpleInterest(request.montoInicial, request.tasaInteres, year)
            )
        }

        return repository.save(
            Calculation(
                initialAmount = request.montoInicial,
                interestRate = request.tasaInteres,
                years = request.anios,
                compoundInterestResults = compoundResults,
                simpleInterestResults = simpleResults
            )
        )
    }

    fun getHistory(): List<Calculation> = repository.findAllByOrderByCreatedAtDesc()

    private fun calculateCompoundInterest(principal: Double, rate: Double, years: Int): Double {
        return principal * (1 + rate / 100).pow(years.toDouble())
    }

    private fun calculateSimpleInterest(principal: Double, rate: Double, years: Int): Double {
        return principal * (1 + (rate / 100) * years)
    }

    private fun validateInput(request: CalculationRequest) {
        require(request.montoInicial > 0) { "Monto inicial inválido" }
        require(request.tasaInteres > 0) { "Tasa de interés inválida" }
        require(request.anios >= 1) { "Período inválido" }
    }
}