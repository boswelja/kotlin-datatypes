package com.boswelja.temperature

import com.boswelja.temperature.Temperature.Companion.celsius
import com.boswelja.temperature.Temperature.Companion.fahrenheit
import com.boswelja.temperature.Temperature.Companion.kelvin
import com.boswelja.temperature.Temperature.Companion.rankine
import com.boswelja.temperature.Temperature.Companion.reaumur
import kotlin.test.Test
import kotlin.test.assertEquals

class TemperatureTest {
    data class TestPoint(
        val kelvin: Double,
        val celsius: Double,
        val fahrenheit: Double,
        val rankine: Double,
        val reaumur: Double
    )

    val WATER_FREEZING = TestPoint(
        kelvin = 273.15,
        celsius = 0.0,
        fahrenheit = 32.0,
        rankine = 491.67,
        reaumur = 0.0
    )

    val WATER_BOILING = TestPoint(
        kelvin = 373.15,
        celsius = 100.0,
        fahrenheit = 212.0,
        rankine = 671.67,
        reaumur = 80.0
    )

    private fun testAgainstTestPoint(temperature: Temperature, testPoint: TestPoint) {
        val convertedKelvin = temperature.toFractionalUnits(TemperatureUnit.KELVIN)
        assertEquals(
            testPoint.kelvin,
            convertedKelvin,
            "Expected ${testPoint.kelvin} K, but got $convertedKelvin K"
        )
        val convertedCelsius = temperature.toFractionalUnits(TemperatureUnit.CELSIUS)
        assertEquals(
            testPoint.celsius,
            convertedCelsius,
            "Expected ${testPoint.celsius} °C, but got $convertedCelsius °C"
        )
        val convertedFahrenheit = temperature.toFractionalUnits(TemperatureUnit.FAHRENHEIT)
        assertEquals(
            testPoint.fahrenheit,
            convertedFahrenheit,
            "Expected ${testPoint.fahrenheit} °F, but got $convertedFahrenheit °F"
        )
        val convertedRankine = temperature.toFractionalUnits(TemperatureUnit.RANKINE)
        assertEquals(
            testPoint.rankine,
            convertedRankine,
            "Expected ${testPoint.rankine} °R, but got $convertedRankine °R"
        )
        val convertedReaumur = temperature.toFractionalUnits(TemperatureUnit.REAUMUR)
        assertEquals(
            testPoint.reaumur,
            convertedReaumur,
            "Expected ${testPoint.reaumur} °Ré, but got $convertedReaumur °Ré"
        )
    }

    @Test
    fun convertingKelvinBetweenWaterFreezingPoints() {
        testAgainstTestPoint(
            WATER_FREEZING.kelvin.kelvin,
            WATER_FREEZING
        )
    }

    @Test
    fun convertingCelsiusBetweenWaterFreezingPoints() {
        testAgainstTestPoint(
            WATER_FREEZING.celsius.celsius,
            WATER_FREEZING
        )
    }

    @Test
    fun convertingFahrenheitBetweenWaterFreezingPoints() {
        testAgainstTestPoint(
            WATER_FREEZING.fahrenheit.fahrenheit,
            WATER_FREEZING
        )
    }

    @Test
    fun convertingRankineBetweenWaterFreezingPoints() {
        testAgainstTestPoint(
            WATER_FREEZING.rankine.rankine,
            WATER_FREEZING
        )
    }

    @Test
    fun convertingReaumurBetweenWaterFreezingPoints() {
        testAgainstTestPoint(
            WATER_FREEZING.reaumur.reaumur,
            WATER_FREEZING
        )
    }

    @Test
    fun convertingKelvinBetweenWaterBoilingPoints() {
        testAgainstTestPoint(
            WATER_BOILING.kelvin.kelvin,
            WATER_BOILING
        )
    }

    @Test
    fun convertingCelsiusBetweenWaterBoilingPoints() {
        testAgainstTestPoint(
            WATER_BOILING.celsius.celsius,
            WATER_BOILING
        )
    }

    @Test
    fun convertingFahrenheitBetweenWaterBoilingPoints() {
        testAgainstTestPoint(
            WATER_BOILING.fahrenheit.fahrenheit,
            WATER_BOILING
        )
    }

    @Test
    fun convertingRankineBetweenWaterBoilingPoints() {
        testAgainstTestPoint(
            WATER_BOILING.rankine.rankine,
            WATER_BOILING
        )
    }

    @Test
    fun convertingReaumurBetweenWaterBoilingPoints() {
        testAgainstTestPoint(
            WATER_BOILING.reaumur.reaumur,
            WATER_BOILING
        )
    }
}
