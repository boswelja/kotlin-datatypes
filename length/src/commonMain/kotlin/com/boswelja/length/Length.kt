package com.boswelja.length

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlin.jvm.JvmInline
import kotlin.math.roundToLong

@JvmInline
value class Length(val meters: BigDecimal): Comparable<Length> {
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

}
