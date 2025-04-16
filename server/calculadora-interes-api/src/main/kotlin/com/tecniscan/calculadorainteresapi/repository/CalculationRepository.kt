package com.tecniscan.calculadorainteresapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.tecniscan.calculadorainteresapi.model.Calculation

interface CalculationRepository : JpaRepository<Calculation, Long> {
    fun findAllByOrderByCreatedAtDesc(): List<Calculation>
}