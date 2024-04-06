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
    fun toDouble(unit: BitrateUnit): Double {
        return rawValue.toDouble() / unit.bitFactor
    }

    /**
     * Converts this Bitrate to the given [BitrateUnit], rounding to the nearest whole number.
     */
    fun toLong(unit: BitrateUnit): Long {
        return toDouble(unit).roundToLong()
    }

    @Suppress("unused")
    companion object {

        private fun Int.toBitrate(unit: BitrateUnit): Bitrate = Bitrate(this * unit.bitFactor)
        private fun Long.toBitrate(unit: BitrateUnit): Bitrate = Bitrate(this * unit.bitFactor)
        private fun Float.toBitrate(unit: BitrateUnit): Bitrate = Bitrate((this * unit.bitFactor).roundToLong())
        private fun Double.toBitrate(unit: BitrateUnit): Bitrate = Bitrate((this * unit.bitFactor).roundToLong())

        // region bits
        /** Converts an [Int] representation of bits to a [Bitrate]. */
        val Int.bits: Bitrate get() = Bitrate(toLong())

        /** Converts a [Long] representation of bits to a [Bitrate]. */
        val Long.bits: Bitrate get() = Bitrate(this)
        // endregion

        // region binary units
        /** Converts an [Int] representation of kibibits to a [Bitrate]. */
        val Int.kibibits: Bitrate get() = toBitrate(BitrateUnit.KIBIBITS)

        /** Converts an [Int] representation of mebibits to a [Bitrate]. */
        val Int.mebibits: Bitrate get() = toBitrate(BitrateUnit.MEBIBITS)

        /** Converts an [Int] representation of gibibits to a [Bitrate]. */
        val Int.gibibits: Bitrate get() = toBitrate(BitrateUnit.GIBIBITS)

        /** Converts an [Int] representation of tebibits to a [Bitrate]. */
        val Int.tebibits: Bitrate get() = toBitrate(BitrateUnit.TEBIBITS)

        /** Converts an [Int] representation of pebibits to a [Bitrate]. */
        val Int.pebibits: Bitrate get() = toBitrate(BitrateUnit.PEBIBITS)

        /** Converts an [Int] representation of exibits to a [Bitrate]. */
        val Int.exbibits: Bitrate get() = toBitrate(BitrateUnit.EXBIBITS)

        /** Converts a [Long] representation of kibibits to a [Bitrate]. */
        val Long.kibibits: Bitrate get() = toBitrate(BitrateUnit.KIBIBITS)

        /** Converts a [Long] representation of mebibits to a [Bitrate]. */
        val Long.mebibits: Bitrate get() = toBitrate(BitrateUnit.MEBIBITS)

        /** Converts a [Long] representation of gibibits to a [Bitrate]. */
        val Long.gibibits: Bitrate get() = toBitrate(BitrateUnit.GIBIBITS)

        /** Converts a [Long] representation of tebibits to a [Bitrate]. */
        val Long.tebibits: Bitrate get() = toBitrate(BitrateUnit.TEBIBITS)

        /** Converts a [Long] representation of pebibits to a [Bitrate]. */
        val Long.pebibits: Bitrate get() = toBitrate(BitrateUnit.PEBIBITS)

        /** Converts a [Long] representation of exibits to a [Bitrate]. */
        val Long.exbibits: Bitrate get() = toBitrate(BitrateUnit.EXBIBITS)

        /** Converts a [Float] representation of kibibits to a [Bitrate]. */
        val Float.kibibits: Bitrate get() = toBitrate(BitrateUnit.KIBIBITS)

        /** Converts a [Float] representation of mebibits to a [Bitrate]. */
        val Float.mebibits: Bitrate get() = toBitrate(BitrateUnit.MEBIBITS)

        /** Converts a [Float] representation of gibibits to a [Bitrate]. */
        val Float.gibibits: Bitrate get() = toBitrate(BitrateUnit.GIBIBITS)

        /** Converts a [Float] representation of tebibits to a [Bitrate]. */
        val Float.tebibits: Bitrate get() = toBitrate(BitrateUnit.TEBIBITS)

        /** Converts a [Float] representation of pebibits to a [Bitrate]. */
        val Float.pebibits: Bitrate get() = toBitrate(BitrateUnit.PEBIBITS)

        /** Converts a [Float] representation of exibits to a [Bitrate]. */
        val Float.exbibits: Bitrate get() = toBitrate(BitrateUnit.EXBIBITS)

        /** Converts a [Double] representation of kibibits to a [Bitrate]. */
        val Double.kibibits: Bitrate get() = toBitrate(BitrateUnit.KIBIBITS)

        /** Converts a [Double] representation of mebibits to a [Bitrate]. */
        val Double.mebibits: Bitrate get() = toBitrate(BitrateUnit.MEBIBITS)

        /** Converts a [Double] representation of gibibits to a [Bitrate]. */
        val Double.gibibits: Bitrate get() = toBitrate(BitrateUnit.GIBIBITS)

        /** Converts a [Double] representation of tebibits to a [Bitrate]. */
        val Double.tebibits: Bitrate get() = toBitrate(BitrateUnit.TEBIBITS)

        /** Converts a [Double] representation of pebibits to a [Bitrate]. */
        val Double.pebibits: Bitrate get() = toBitrate(BitrateUnit.PEBIBITS)

        /** Converts a [Double] representation of exbibits to a [Bitrate]. */
        val Double.exbibits: Bitrate get() = toBitrate(BitrateUnit.EXBIBITS)
        //endregion

        // region decimal units
        /** Converts an [Int] representation of kilobits to a [Bitrate]. */
        val Int.kilobits: Bitrate get() = toBitrate(BitrateUnit.KILOBITS)

        /** Converts an [Int] representation of megabits to a [Bitrate]. */
        val Int.megabits: Bitrate get() = toBitrate(BitrateUnit.MEGABITS)

        /** Converts an [Int] representation of gigabits to a [Bitrate]. */
        val Int.gigabits: Bitrate get() = toBitrate(BitrateUnit.GIGABITS)

        /** Converts an [Int] representation of terabits to a [Bitrate]. */
        val Int.terabits: Bitrate get() = toBitrate(BitrateUnit.TERABITS)

        /** Converts an [Int] representation of petabits to a [Bitrate]. */
        val Int.petabits: Bitrate get() = toBitrate(BitrateUnit.PETABITS)

        /** Converts an [Int] representation of exabits to a [Bitrate]. */
        val Int.exabits: Bitrate get() = toBitrate(BitrateUnit.EXABITS)

        /** Converts a [Long] representation of kilobits to a [Bitrate]. */
        val Long.kilobits: Bitrate get() = toBitrate(BitrateUnit.KILOBITS)

        /** Converts a [Long] representation of megabits to a [Bitrate]. */
        val Long.megabits: Bitrate get() = toBitrate(BitrateUnit.MEGABITS)

        /** Converts a [Long] representation of gigabits to a [Bitrate]. */
        val Long.gigabits: Bitrate get() = toBitrate(BitrateUnit.GIGABITS)

        /** Converts a [Long] representation of terabits to a [Bitrate]. */
        val Long.terabits: Bitrate get() = toBitrate(BitrateUnit.TERABITS)

        /** Converts a [Long] representation of petabits to a [Bitrate]. */
        val Long.petabits: Bitrate get() = toBitrate(BitrateUnit.PETABITS)

        /** Converts a [Long] representation of exabits to a [Bitrate]. */
        val Long.exabits: Bitrate get() = toBitrate(BitrateUnit.EXABITS)

        /** Converts a [Float] representation of kilobits to a [Bitrate]. */
        val Float.kilobits: Bitrate get() = toBitrate(BitrateUnit.KILOBITS)

        /** Converts a [Float] representation of megabits to a [Bitrate]. */
        val Float.megabits: Bitrate get() = toBitrate(BitrateUnit.MEGABITS)

        /** Converts a [Float] representation of gigabits to a [Bitrate]. */
        val Float.gigabits: Bitrate get() = toBitrate(BitrateUnit.GIGABITS)

        /** Converts a [Float] representation of terabits to a [Bitrate]. */
        val Float.terabits: Bitrate get() = toBitrate(BitrateUnit.TERABITS)

        /** Converts a [Float] representation of petabits to a [Bitrate]. */
        val Float.petabits: Bitrate get() = toBitrate(BitrateUnit.PETABITS)

        /** Converts a [Float] representation of exabits to a [Bitrate]. */
        val Float.exabits: Bitrate get() = toBitrate(BitrateUnit.EXABITS)

        /** Converts a [Double] representation of kilobits to a [Bitrate]. */
        val Double.kilobits: Bitrate get() = toBitrate(BitrateUnit.KILOBITS)

        /** Converts a [Double] representation of megabits to a [Bitrate]. */
        val Double.megabits: Bitrate get() = toBitrate(BitrateUnit.MEGABITS)

        /** Converts a [Double] representation of gigabits to a [Bitrate]. */
        val Double.gigabits: Bitrate get() = toBitrate(BitrateUnit.GIGABITS)

        /** Converts a [Double] representation of terabits to a [Bitrate]. */
        val Double.terabits: Bitrate get() = toBitrate(BitrateUnit.TERABITS)

        /** Converts a [Double] representation of petabits to a [Bitrate]. */
        val Double.petabits: Bitrate get() = toBitrate(BitrateUnit.PETABITS)

        /** Converts a [Double] representation of exabits to a [Bitrate]. */
        val Double.exabits: Bitrate get() = toBitrate(BitrateUnit.EXABITS)
        //endregion
    }
}
