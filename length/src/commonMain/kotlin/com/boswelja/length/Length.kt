package com.boswelja.length

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlin.jvm.JvmInline
import kotlin.math.roundToLong

/**
 * Represents a measurement of length.
 *
 * To construct a Length, use the extension functions [meters], [feet], [nauticalMiles] and so
 * on.
 *
 * To get the value of this Length expressed in a particular LengthUnit, use the functions
 * [toFractionalUnits], [roundToWholeUnits], and so on.
 */
@JvmInline
value class Length(private val meters: BigDecimal): Comparable<Length> {
    override fun compareTo(other: Length): Int {
        return meters.compareTo(other.meters)
    }

    /**
     * Adds [other] to this Length. This does **not** handle integer overflow.
     */
    operator fun plus(other: Length): Length {
        return Length(meters + other.meters)
    }

    /**
     * Adds [other] to this Length. This does **not** handle integer overflow.
     */
    operator fun minus(other: Length): Length {
        return Length(meters - other.meters)
    }

    /**
     * Converts this Length to the given [LengthUnit], returning a Double representing the precise value.
     */
    fun toFractionalUnits(unit: LengthUnit): Double {
        return unit.fromMeter(meters).doubleValue(exactRequired = false)
    }

    /**
     * Converts this Length to the given [LengthUnit], rounding down to the nearest whole number.
     */
    fun toWholeUnits(unit: LengthUnit): Long {
        return unit.fromMeter(meters).longValue(exactRequired = false)
    }

    /**
     * Converts this Length to the given [LengthUnit], rounding to the nearest whole number.
     */
    fun roundToWholeUnits(unit: LengthUnit): Long {
        return toFractionalUnits(unit).roundToLong()
    }

    /**
     * Holds static methods for creating [Length] instances from various units.
     */
    companion object {

        /**
         * Converts a [Number] to a [Length], using the given [LengthUnit].
         */
        fun Number.toLength(unit: LengthUnit): Length {
            return when (this) {
                is Byte -> Length(unit.toMeter(this.toBigDecimal()))
                is Double -> Length(unit.toMeter(this.toBigDecimal()))
                is Float -> Length(unit.toMeter(this.toBigDecimal()))
                is Int -> Length(unit.toMeter(this.toBigDecimal()))
                is Long -> Length(unit.toMeter(this.toBigDecimal()))
                is Short -> Length(unit.toMeter(this.toBigDecimal()))
                else -> {
                    // Best-effort conversion
                    Length(unit.toMeter(this.toDouble().toBigDecimal()))
                }
            }
        }

        /** Converts the given Number to a [Length] instance in meters. */
        val Number.meters get() = toLength(LengthUnit.METER)

        /** Converts the given Number to a [Length] instance in centimeters. */
        val Number.centimeters get() = toLength(LengthUnit.CENTIMETER)

        /** Converts the given Number to a [Length] instance in kilometers. */
        val Number.kilometers get() = toLength(LengthUnit.KILOMETER)

        /** Converts the given Number to a [Length] instance in millimeters. */
        val Number.millimeters get() = toLength(LengthUnit.MILLIMETER)

        /** Converts the given Number to a [Length] instance in thous. */
        val Number.thous get() = toLength(LengthUnit.THOU)

        /** Converts the given Number to a [Length] instance in inches. */
        val Number.inches get() = toLength(LengthUnit.INCH)

        /** Converts the given Number to a [Length] instance in feet. */
        val Number.feet get() = toLength(LengthUnit.FOOT)

        /** Converts the given Number to a [Length] instance in yards. */
        val Number.yards get() = toLength(LengthUnit.YARD)

        /** Converts the given Number to a [Length] instance in miles. */
        val Number.miles get() = toLength(LengthUnit.MILE)

        /** Converts the given Number to a [Length] instance in leagues. */
        val Number.leagues get() = toLength(LengthUnit.LEAGUE)

        /** Converts the given Number to a [Length] instance in nautical miles. */
        val Number.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)

        /** Converts the given Number to a [Length] instance in fathoms. */
        val Number.fathoms get() = toLength(LengthUnit.FATHOM)
    }
}
