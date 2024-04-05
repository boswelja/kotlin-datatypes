package com.boswelja.bitrate

/**
 * Defines various units supported by [Bitrate]. We can convert to/from any of these.
 */
@Suppress("MagicNumber")
enum class BitrateUnit(internal val bitFactor: Long) {
    BITS(1),

    // Binary units
    KIBIBITS(1024),
    MEBIBITS(1048576),
    GIBIBITS(1073741824),
    TEBIBITS(1_099_511_627_776),
    PEBIBITS(1_125_899_906_842_624),
    EXBIBITS(1_152_921_504_606_846_976),

    // Metric units
    KILOBITS(1_000),
    MEGABITS(1_000_000),
    GIGABITS(1_000_000_000),
    TERABITS(1_000_000_000_000),
    PETABITS(1_000_000_000_000_000),
    EXABITS(1_000_000_000_000_000_000)
}
