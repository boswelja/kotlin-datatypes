package com.boswelja.temperature

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlin.jvm.JvmInline
import kotlin.math.roundToLong

/**
 * Represents a temperature value.
 *
 * To construct a Temperature, use the extension functions [celsius], [fahrenheit], [kelvin] and so
 * on.
 *
 * To get the value of this Temperature expressed in a particular TemperatureUnit, use the functions
 * [toLong], [toDouble], and so on.
 */
@JvmInline
value class Temperature internal constructor(private val kelvin: BigDecimal) : Comparable<Temperature> {

    override fun compareTo(other: Temperature): Int {
        return kelvin.compareTo(other.kelvin)
    }

    /**
     * Adds [other] to this Temperature. This currently does **not** handle integer overflow.
     */
    operator fun plus(other: Temperature): Temperature {
        return Temperature(kelvin + other.kelvin)
    }

    /**
     * Subtracts [other] from this Temperature. This currently does **not** handle integer overflow.
     */
    operator fun minus(other: Temperature): Temperature {
        return Temperature(kelvin - other.kelvin)
    }

    /**
     * Converts this Temperature to the given [TemperatureUnit], returning a Double representing the
     * precise value.
     */
    @Deprecated("Use toFractionalUnits instead", ReplaceWith("toFractionalUnits(unit)"))
    fun toDouble(unit: TemperatureUnit): Double {
        return unit.fromKelvin(kelvin).doubleValue(exactRequired = false)
    }

    /**
     * Converts this Temperature to the given [TemperatureUnit], rounding to the nearest whole number.
     */
    @Deprecated("Use toWholeUnits or roundToWholeUnits instead", ReplaceWith("roundToWholeUnits(unit)"))
    fun toLong(unit: TemperatureUnit): Long {
        return toFractionalUnits(unit).roundToLong()
    }

    /**
     * Converts this Capacity to the given [TemperatureUnit], returning a Double representing the precise value.
     */
    fun toFractionalUnits(unit: TemperatureUnit): Double {
        return unit.fromKelvin(kelvin).doubleValue(exactRequired = false)
    }

    /**
     * Converts this Capacity to the given [TemperatureUnit], rounding down to the nearest whole number.
     */
    fun toWholeUnits(unit: TemperatureUnit): Long {
        return unit.fromKelvin(kelvin).longValue(exactRequired = false)
    }

    /**
     * Converts this Capacity to the given [TemperatureUnit], rounding to the nearest whole number.
     */
    fun roundToWholeUnits(unit: TemperatureUnit): Long {
        return toFractionalUnits(unit).roundToLong()
    }

    @Suppress("unused")
    companion object {

        /**
         * Converts a [Number] to a [Temperature], using the given [TemperatureUnit].
         */
        fun Number.toTemperature(unit: TemperatureUnit): Temperature {
            return when (this) {
                is Byte -> Temperature(unit.toKelvin(this.toBigDecimal()))
                is Double -> Temperature(unit.toKelvin(this.toBigDecimal()))
                is Float -> Temperature(unit.toKelvin(this.toBigDecimal()))
                is Int -> Temperature(unit.toKelvin(this.toBigDecimal()))
                is Long -> Temperature(unit.toKelvin(this.toBigDecimal()))
                is Short -> Temperature(unit.toKelvin(this.toBigDecimal()))
                else -> {
                    // Best-effort conversion
                    Temperature(unit.toKelvin(this.toDouble().toBigDecimal()))
                }
            }
        }

        /** Converts a [Number] representation of kelvin to a [Temperature]. */
        val Number.kelvin: Temperature get() = toTemperature(TemperatureUnit.KELVIN)

        /** Converts a [Number] representation of celsius to a [Temperature]. */
        val Number.celsius: Temperature get() = toTemperature(TemperatureUnit.CELSIUS)

        /** Converts a [Number] representation of fahrenheit to a [Temperature]. */
        val Number.fahrenheit: Temperature get() = toTemperature(TemperatureUnit.FAHRENHEIT)

        /** Converts a [Number] representation of rankine to a [Temperature]. */
        val Number.rankine: Temperature get() = toTemperature(TemperatureUnit.RANKINE)

        /** Converts a [Number] representation of réaumur to a [Temperature]. */
        val Number.reaumur: Temperature get() = toTemperature(TemperatureUnit.REAUMUR)
    }
}
