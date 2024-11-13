package com.boswelja.capacity

import kotlin.jvm.JvmInline
import kotlin.math.roundToLong

/**
 * Represents the amount of digital data something uses.
 *
 * Capacity can represent ±8 exbibytes with byte-level accuracy.
 *
 * To construct a Capacity, use the extension functions [kibibytes], [kilobytes], [mebibytes],
 * [megabytes], and so on.
 *
 * To get the value of this Capacity expressed in a particular CapacityUnit, use the functions
 * [toLong], [toDouble], and so on.
 */
@JvmInline
value class Capacity internal constructor(private val rawValue: Long) : Comparable<Capacity> {

    /**
     * Returns the value of this Capacity in bytes.
     */
    val inWholeBytes: Long
        get() = rawValue

    override fun compareTo(other: Capacity): Int {
        return rawValue.compareTo(other.rawValue)
    }

    /**
     * Adds [other] to this Capacity. This currently does **not** handle integer overflow.
     */
    operator fun plus(other: Capacity): Capacity {
        return Capacity(rawValue + other.rawValue)
    }

    /**
     * Subtracts [other] from this Capacity. This currently does **not** handle integer overflow.
     */
    operator fun minus(other: Capacity): Capacity {
        return Capacity(rawValue - other.rawValue)
    }

    /**
     * Converts this Capacity to the given [CapacityUnit], returning a Double representing the
     * precise value.
     */
    @Deprecated("Use toFractionalUnits instead", ReplaceWith("toFractionalUnits(unit)"))
    fun toDouble(unit: CapacityUnit): Double {
        return toFractionalUnits(unit)
    }

    /**
     * Converts this Capacity to the given [CapacityUnit], rounding to the nearest whole number.
     */
    @Deprecated("Use toWholeUnits or roundToWholeUnits instead", ReplaceWith("roundToWholeUnits(unit)"))
    fun toLong(unit: CapacityUnit): Long {
        return roundToWholeUnits(unit)
    }

    /**
     * Converts this Capacity to the given [CapacityUnit], returning a Double representing the precise value.
     */
    fun toFractionalUnits(unit: CapacityUnit): Double {
        return rawValue.toDouble() / unit.byteFactor
    }

    /**
     * Converts this Capacity to the given [CapacityUnit], rounding down to the nearest whole number.
     */
    fun toWholeUnits(unit: CapacityUnit): Long {
        return rawValue / unit.byteFactor
    }

    /**
     * Converts this Capacity to the given [CapacityUnit], rounding to the nearest whole number.
     */
    fun roundToWholeUnits(unit: CapacityUnit): Long {
        return toFractionalUnits(unit).roundToLong()
    }

    @Suppress("unused")
    companion object {

        /**
         * Converts a [Number] to a [Capacity], using the given [CapacityUnit].
         */
        fun Number.toCapacity(unit: CapacityUnit): Capacity {
            return when (this) {
                is Byte -> Capacity(this * unit.byteFactor)
                is Double -> Capacity((this * unit.byteFactor).roundToLong())
                is Float -> Capacity((this * unit.byteFactor).roundToLong())
                is Int -> Capacity(this * unit.byteFactor)
                is Long -> Capacity(this * unit.byteFactor)
                is Short -> Capacity(this * unit.byteFactor)
                else -> {
                    // Best-effort conversion
                    Capacity((this.toDouble() * unit.byteFactor).roundToLong())
                }
            }
        }

        /** Converts a [Number] representation of bytes to a [Capacity]. */
        val Number.bytes: Capacity get() = toCapacity(CapacityUnit.BYTE)

        /** Converts a [Number] representation of kibibytes to a [Capacity]. */
        val Number.kibibytes: Capacity get() = toCapacity(CapacityUnit.KIBIBYTE)

        /** Converts a [Number] representation of mebibytes to a [Capacity]. */
        val Number.mebibytes: Capacity get() = toCapacity(CapacityUnit.MEBIBYTE)

        /** Converts a [Number] representation of gibibytes to a [Capacity]. */
        val Number.gibibytes: Capacity get() = toCapacity(CapacityUnit.GIBIBYTE)

        /** Converts a [Number] representation of tebibytes to a [Capacity]. */
        val Number.tebibytes: Capacity get() = toCapacity(CapacityUnit.TEBIBYTE)

        /** Converts a [Number] representation of pebibytes to a [Capacity]. */
        val Number.pebibytes: Capacity get() = toCapacity(CapacityUnit.PEBIBYTE)

        /** Converts a [Number] representation of exibytes to a [Capacity]. */
        val Number.exbibytes: Capacity get() = toCapacity(CapacityUnit.EXBIBYTE)

        /** Converts a [Number] representation of kilobytes to a [Capacity]. */
        val Number.kilobytes: Capacity get() = toCapacity(CapacityUnit.KILOBYTE)

        /** Converts a [Number] representation of megabytes to a [Capacity]. */
        val Number.megabytes: Capacity get() = toCapacity(CapacityUnit.MEGABYTE)

        /** Converts a [Number] representation of gigabytes to a [Capacity]. */
        val Number.gigabytes: Capacity get() = toCapacity(CapacityUnit.GIGABYTE)

        /** Converts a [Number] representation of terabytes to a [Capacity]. */
        val Number.terabytes: Capacity get() = toCapacity(CapacityUnit.TERABYTE)

        /** Converts a [Number] representation of petabytes to a [Capacity]. */
        val Number.petabytes: Capacity get() = toCapacity(CapacityUnit.PETABYTE)

        /** Converts a [Number] representation of exabytes to a [Capacity]. */
        val Number.exabytes: Capacity get() = toCapacity(CapacityUnit.EXABYTE)
    }
}
