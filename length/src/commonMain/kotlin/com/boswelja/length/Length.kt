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

    operator fun plus(other: Length): Length {
        return Length(meters + other.meters)
    }

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

    companion object {
        private fun Int.toLength(unit: LengthUnit): Length =
            Length(unit.toMeter(this.toBigDecimal()))
        private fun Long.toLength(unit: LengthUnit): Length =
            Length(unit.toMeter(this.toBigDecimal()))
        private fun Float.toLength(unit: LengthUnit): Length =
            Length(unit.toMeter(this.toBigDecimal()))
        private fun Double.toLength(unit: LengthUnit): Length =
            Length(unit.toMeter(this.toBigDecimal()))

        // region meters
        val Int.meters get() = toLength(LengthUnit.METER)
        val Long.meters get() = toLength(LengthUnit.METER)
        val Float.meters get() = toLength(LengthUnit.METER)
        val Double.meters get() = toLength(LengthUnit.METER)
        // endregion

        // region centimeters
        val Int.centimeters get() = toLength(LengthUnit.CENTIMETER)
        val Long.centimeters get() = toLength(LengthUnit.CENTIMETER)
        val Float.centimeters get() = toLength(LengthUnit.CENTIMETER)
        val Double.centimeters get() = toLength(LengthUnit.CENTIMETER)
        // endregion

        // region kilometers
        val Int.kilometers get() = toLength(LengthUnit.KILOMETER)
        val Long.kilometers get() = toLength(LengthUnit.KILOMETER)
        val Float.kilometers get() = toLength(LengthUnit.KILOMETER)
        val Double.kilometers get() = toLength(LengthUnit.KILOMETER)
        // endregion

        // region millimeter
        val Int.millimeters get() = toLength(LengthUnit.MILLIMETER)
        val Long.millimeters get() = toLength(LengthUnit.MILLIMETER)
        val Float.millimeters get() = toLength(LengthUnit.MILLIMETER)
        val Double.millimeters get() = toLength(LengthUnit.MILLIMETER)
        // endregion

        // region thou
        val Int.thous get() = toLength(LengthUnit.THOU)
        val Long.thous get() = toLength(LengthUnit.THOU)
        val Float.thous get() = toLength(LengthUnit.THOU)
        val Double.thous get() = toLength(LengthUnit.THOU)
        // endregion

        // region inch
        val Int.inches get() = toLength(LengthUnit.INCH)
        val Long.inches get() = toLength(LengthUnit.INCH)
        val Float.inches get() = toLength(LengthUnit.INCH)
        val Double.inches get() = toLength(LengthUnit.INCH)
        // endregion

        // region foot
        val Int.feet get() = toLength(LengthUnit.FOOT)
        val Long.feet get() = toLength(LengthUnit.FOOT)
        val Float.feet get() = toLength(LengthUnit.FOOT)
        val Double.feet get() = toLength(LengthUnit.FOOT)
        // endregion

        // region yard
        val Int.yards get() = toLength(LengthUnit.YARD)
        val Long.yards get() = toLength(LengthUnit.YARD)
        val Float.yards get() = toLength(LengthUnit.YARD)
        val Double.yards get() = toLength(LengthUnit.YARD)
        // endregion

        // region mile
        val Int.miles get() = toLength(LengthUnit.MILE)
        val Long.miles get() = toLength(LengthUnit.MILE)
        val Float.miles get() = toLength(LengthUnit.MILE)
        val Double.miles get() = toLength(LengthUnit.MILE)
        // endregion

        // region league
        val Int.leagues get() = toLength(LengthUnit.LEAGUE)
        val Long.leagues get() = toLength(LengthUnit.LEAGUE)
        val Float.leagues get() = toLength(LengthUnit.LEAGUE)
        val Double.leagues get() = toLength(LengthUnit.LEAGUE)
        // endregion

        // region nauticalMile
        val Int.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)
        val Long.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)
        val Float.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)
        val Double.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)
        // endregion

        // region fathom
        val Int.fathoms get() = toLength(LengthUnit.FATHOM)
        val Long.fathoms get() = toLength(LengthUnit.FATHOM)
        val Float.fathoms get() = toLength(LengthUnit.FATHOM)
        val Double.fathoms get() = toLength(LengthUnit.FATHOM)
        // endregion
    }
}
