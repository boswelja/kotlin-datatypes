package com.boswelja.bitrate

import kotlin.jvm.JvmInline
import kotlin.math.roundToLong

/**
 * Represents an amount of bits that are processed per second.
 *
 * Bitrate can represent ±8 exabits/s.
 *
 * To construct a Bitrate, use the extension functions [kilobits], [megabits], [gigabits], and so on.
 *
 * To get the value of this Capacity expressed in a particular CapacityUnit, use the functions
 * [toLong], [toDouble], and so on.
 */
@JvmInline
value class Bitrate internal constructor(private val rawValue: Long) : Comparable<Bitrate> {

    override fun compareTo(other: Bitrate): Int {
        return rawValue.compareTo(other.rawValue)
    }

    /**
     * Adds [other] to this Bitrate. This currently does **not** handle integer overflow.
     */
    operator fun plus(other: Bitrate): Bitrate {
        return Bitrate(rawValue + other.rawValue)
    }

    /**
     * Subtracts [other] from this Bitrate. This currently does **not** handle integer overflow.
     */
    operator fun minus(other: Bitrate): Bitrate {
        return Bitrate(rawValue - other.rawValue)
    }

    /**
     * Converts this Bitrate to the given [BitrateUnit], returning a Double representing the
     * precise value.
     */
    @Deprecated("Use toFractionalUnits instead", ReplaceWith("toFractionalUnits(unit)"))
    fun toDouble(unit: BitrateUnit): Double {
        return rawValue.toDouble() / unit.bitFactor
    }

    /**
     * Converts this Bitrate to the given [BitrateUnit], rounding to the nearest whole number.
     */
    @Deprecated("Use toWholeUnits or roundToWholeUnits instead", ReplaceWith("roundToWholeUnits(unit)"))
    fun toLong(unit: BitrateUnit): Long {
        return roundToWholeUnits(unit)
    }

    /**
     * Converts this Capacity to the given [CapacityUnit], returning a Double representing the precise value.
     */
    fun toFractionalUnits(unit: BitrateUnit): Double {
        return rawValue.toDouble() / unit.bitFactor
    }

    /**
     * Converts this Capacity to the given [CapacityUnit], rounding down to the nearest whole number.
     */
    fun toWholeUnits(unit: BitrateUnit): Long {
        return rawValue / unit.bitFactor
    }

    /**
     * Converts this Capacity to the given [CapacityUnit], rounding to the nearest whole number.
     */
    fun roundToWholeUnits(unit: BitrateUnit): Long {
        return toFractionalUnits(unit).roundToLong()
    }

    @Suppress("unused")
    companion object {

        /**
         * Converts a [Number] to a [Bitrate], using the given [BitrateUnit].
         */
        fun Number.toBitrate(unit: BitrateUnit): Bitrate {
            return when (this) {
                is Byte -> Bitrate(this * unit.bitFactor)
                is Double -> Bitrate((this * unit.bitFactor).roundToLong())
                is Float -> Bitrate((this * unit.bitFactor).roundToLong())
                is Int -> Bitrate(this * unit.bitFactor)
                is Long -> Bitrate(this * unit.bitFactor)
                is Short -> Bitrate(this * unit.bitFactor)
                else -> {
                    // Best-effort conversion
                    Bitrate((this.toDouble() * unit.bitFactor).roundToLong())
                }
            }
        }

        /** Converts a [Number] representation of bits to a [Bitrate]. */
        val Number.bits: Bitrate get() = toBitrate(BitrateUnit.BITS)

        /** Converts a [Number] representation of kibibits to a [Bitrate]. */
        val Number.kibibits: Bitrate get() = toBitrate(BitrateUnit.KIBIBITS)

        /** Converts a [Number] representation of mebibits to a [Bitrate]. */
        val Number.mebibits: Bitrate get() =  toBitrate(BitrateUnit.MEBIBITS)

        /** Converts a [Number] representation of gibibits to a [Bitrate]. */
        val Number.gibibits: Bitrate get() =  toBitrate(BitrateUnit.GIBIBITS)
        
        /** Converts a [Number] representation of tebibits to a [Bitrate]. */
        val Number.tebibits: Bitrate get() =  toBitrate(BitrateUnit.TEBIBITS)
        
        /** Converts a [Number] representation of pebibits to a [Bitrate]. */
        val Number.pebibits: Bitrate get() = toBitrate(BitrateUnit.PEBIBITS)

        /** Converts a [Number] representation of exibits to a [Bitrate]. */
        val Number.exbibits: Bitrate get() = toBitrate(BitrateUnit.EXBIBITS)

        /** Converts a [Number] representation of kilobits to a [Bitrate]. */
        val Number.kilobits: Bitrate get() = toBitrate(BitrateUnit.KILOBITS)

        /** Converts a [Number] representation of megabits to a [Bitrate]. */
        val Number.megabits: Bitrate get() = toBitrate(BitrateUnit.MEGABITS)

        /** Converts a [Number] representation of gigabits to a [Bitrate]. */
        val Number.gigabits: Bitrate get() = toBitrate(BitrateUnit.GIGABITS)

        /** Converts a [Number] representation of terabits to a [Bitrate]. */
        val Number.terabits: Bitrate get() = toBitrate(BitrateUnit.TERABITS)

        /** Converts a [Number] representation of petabits to a [Bitrate]. */
        val Number.petabits: Bitrate get() = toBitrate(BitrateUnit.PETABITS)

        /** Converts a [Number] representation of exabits to a [Bitrate]. */
        val Number.exabits: Bitrate get() = toBitrate(BitrateUnit.EXABITS)
    }
}
