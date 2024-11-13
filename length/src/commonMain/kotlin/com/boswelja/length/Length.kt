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
         * Creates a [Length] instance from the given Int in the specified [LengthUnit].
         */
        fun Int.toLength(unit: LengthUnit): Length = Length(unit.toMeter(this.toBigDecimal()))

        /**
         * Creates a [Length] instance from the given Long in the specified [LengthUnit].
         */
        fun Long.toLength(unit: LengthUnit): Length = Length(unit.toMeter(this.toBigDecimal()))

        /**
         * Creates a [Length] instance from the given Float in the specified [LengthUnit].
         */
        fun Float.toLength(unit: LengthUnit): Length = Length(unit.toMeter(this.toBigDecimal()))

        /**
         * Creates a [Length] instance from the given Double in the specified [LengthUnit].
         */
        fun Double.toLength(unit: LengthUnit): Length = Length(unit.toMeter(this.toBigDecimal()))

        // region meters
        /**
         * Converts the given Int to a [Length] instance in meters.
         */
        val Int.meters get() = toLength(LengthUnit.METER)

        /**
         * Converts the given Long to a [Length] instance in meters.
         */
        val Long.meters get() = toLength(LengthUnit.METER)

        /**
         * Converts the given Float to a [Length] instance in meters.
         */
        val Float.meters get() = toLength(LengthUnit.METER)

        /**
         * Converts the given Double to a [Length] instance in meters.
         */
        val Double.meters get() = toLength(LengthUnit.METER)
        // endregion

        // region centimeters
        /**
         * Converts the given Int to a [Length] instance in centimeters.
         */
        val Int.centimeters get() = toLength(LengthUnit.CENTIMETER)

        /**
         * Converts the given Long to a [Length] instance in centimeters.
         */
        val Long.centimeters get() = toLength(LengthUnit.CENTIMETER)

        /**
         * Converts the given Float to a [Length] instance in centimeters.
         */
        val Float.centimeters get() = toLength(LengthUnit.CENTIMETER)

        /**
         * Converts the given Double to a [Length] instance in centimeters.
         */
        val Double.centimeters get() = toLength(LengthUnit.CENTIMETER)
        // endregion

        // region kilometers
        /**
         * Converts the given Int to a [Length] instance in kilometers.
         */
        val Int.kilometers get() = toLength(LengthUnit.KILOMETER)

        /**
         * Converts the given Long to a [Length] instance in kilometers.
         */
        val Long.kilometers get() = toLength(LengthUnit.KILOMETER)

        /**
         * Converts the given Float to a [Length] instance in kilometers.
         */
        val Float.kilometers get() = toLength(LengthUnit.KILOMETER)

        /**
         * Converts the given Double to a [Length] instance in kilometers.
         */
        val Double.kilometers get() = toLength(LengthUnit.KILOMETER)
        // endregion

        // region millimeter
        /**
         * Converts the given Int to a [Length] instance in millimeters.
         */
        val Int.millimeters get() = toLength(LengthUnit.MILLIMETER)

        /**
         * Converts the given Long to a [Length] instance in millimeters.
         */
        val Long.millimeters get() = toLength(LengthUnit.MILLIMETER)

        /**
         * Converts the given Float to a [Length] instance in millimeters.
         */
        val Float.millimeters get() = toLength(LengthUnit.MILLIMETER)

        /**
         * Converts the given Double to a [Length] instance in millimeters.
         */
        val Double.millimeters get() = toLength(LengthUnit.MILLIMETER)
        // endregion

        // region thou
        /**
         * Converts the given Int to a [Length] instance in thous.
         */
        val Int.thous get() = toLength(LengthUnit.THOU)

        /**
         * Converts the given Long to a [Length] instance in thous.
         */
        val Long.thous get() = toLength(LengthUnit.THOU)

        /**
         * Converts the given Float to a [Length] instance in thous.
         */
        val Float.thous get() = toLength(LengthUnit.THOU)

        /**
         * Converts the given Double to a [Length] instance in thous.
         */
        val Double.thous get() = toLength(LengthUnit.THOU)
        // endregion

        // region inch
        /**
         * Converts the given Int to a [Length] instance in inches.
         */
        val Int.inches get() = toLength(LengthUnit.INCH)

        /**
         * Converts the given Long to a [Length] instance in inches.
         */
        val Long.inches get() = toLength(LengthUnit.INCH)

        /**
         * Converts the given Float to a [Length] instance in inches.
         */
        val Float.inches get() = toLength(LengthUnit.INCH)

        /**
         * Converts the given Double to a [Length] instance in inches.
         */
        val Double.inches get() = toLength(LengthUnit.INCH)
        // endregion

        // region foot
        /**
         * Converts the given Int to a [Length] instance in feet.
         */
        val Int.feet get() = toLength(LengthUnit.FOOT)

        /**
         * Converts the given Long to a [Length] instance in feet.
         */
        val Long.feet get() = toLength(LengthUnit.FOOT)

        /**
         * Converts the given Float to a [Length] instance in feet.
         */
        val Float.feet get() = toLength(LengthUnit.FOOT)

        /**
         * Converts the given Double to a [Length] instance in feet.
         */
        val Double.feet get() = toLength(LengthUnit.FOOT)
        // endregion

        // region yard
        /**
         * Converts the given Int to a [Length] instance in yards.
         */
        val Int.yards get() = toLength(LengthUnit.YARD)

        /**
         * Converts the given Long to a [Length] instance in yards.
         */
        val Long.yards get() = toLength(LengthUnit.YARD)

        /**
         * Converts the given Float to a [Length] instance in yards.
         */
        val Float.yards get() = toLength(LengthUnit.YARD)

        /**
         * Converts the given Double to a [Length] instance in yards.
         */
        val Double.yards get() = toLength(LengthUnit.YARD)
        // endregion

        // region mile
        /**
         * Converts the given Int to a [Length] instance in miles.
         */
        val Int.miles get() = toLength(LengthUnit.MILE)

        /**
         * Converts the given Long to a [Length] instance in miles.
         */
        val Long.miles get() = toLength(LengthUnit.MILE)

        /**
         * Converts the given Float to a [Length] instance in miles.
         */
        val Float.miles get() = toLength(LengthUnit.MILE)

        /**
         * Converts the given Double to a [Length] instance in miles.
         */
        val Double.miles get() = toLength(LengthUnit.MILE)
        // endregion

        // region league
        /**
         * Converts the given Int to a [Length] instance in leagues.
         */
        val Int.leagues get() = toLength(LengthUnit.LEAGUE)

        /**
         * Converts the given Long to a [Length] instance in leagues.
         */
        val Long.leagues get() = toLength(LengthUnit.LEAGUE)

        /**
         * Converts the given Float to a [Length] instance in leagues.
         */
        val Float.leagues get() = toLength(LengthUnit.LEAGUE)

        /**
         * Converts the given Double to a [Length] instance in leagues.
         */
        val Double.leagues get() = toLength(LengthUnit.LEAGUE)
        // endregion

        // region nauticalMile
        /**
         * Converts the given Int to a [Length] instance in nautical miles.
         */
        val Int.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)

        /**
         * Converts the given Long to a [Length] instance in nautical miles.
         */
        val Long.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)

        /**
         * Converts the given Float to a [Length] instance in nautical miles.
         */
        val Float.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)

        /**
         * Converts the given Double to a [Length] instance in nautical miles.
         */
        val Double.nauticalMiles get() = toLength(LengthUnit.NAUTICAL_MILE)
        // endregion

        // region fathom
        /**
         * Converts the given Int to a [Length] instance in fathoms.
         */
        val Int.fathoms get() = toLength(LengthUnit.FATHOM)

        /**
         * Converts the given Long to a [Length] instance in fathoms.
         */
        val Long.fathoms get() = toLength(LengthUnit.FATHOM)

        /**
         * Converts the given Float to a [Length] instance in fathoms.
         */
        val Float.fathoms get() = toLength(LengthUnit.FATHOM)

        /**
         * Converts the given Double to a [Length] instance in fathoms.
         */
        val Double.fathoms get() = toLength(LengthUnit.FATHOM)
        // endregion
    }
}
