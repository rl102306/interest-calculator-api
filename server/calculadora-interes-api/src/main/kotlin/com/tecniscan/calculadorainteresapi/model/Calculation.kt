package com.tecniscan.calculadorainteresapi.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "calculations")
data class Calculation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "initial_amount", nullable = false)
    val initialAmount: Double,

    @Column(name = "interest_rate", nullable = false)
    val interestRate: Double,

    @Column(nullable = false)
    val years: Int,

    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ElementCollection
    @CollectionTable(name = "compound_interest_results", joinColumns = [JoinColumn(name = "calculation_id")])
    val compoundInterestResults: List<YearlyResult>,

    @ElementCollection
    @CollectionTable(name = "simple_interest_results", joinColumns = [JoinColumn(name = "calculation_id")])
    val simpleInterestResults: List<YearlyResult>
) {
    @Embeddable
    data class YearlyResult(
        @Column(name = "year")
        val year: Int,

        @Column(name = "amount")
        val amount: Double
    )
}