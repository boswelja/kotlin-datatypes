package com.boswelja.length

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

enum class LengthUnit(
    internal val toMeter: (BigDecimal) -> BigDecimal,
    internal val fromMeter: (BigDecimal) -> BigDecimal,
) {
    // SI Metric
    METER({ it }, { it }),
    KILOMETER(
        toMeter = {
            it * 1000
        },
        fromMeter = {
            it / 1000
        }
    ),
    CENTIMETER(
        toMeter = {
            it / 100
        },
        fromMeter = {
            it * 100
        }
    ),
    MILLIMETER(
        toMeter = {
            it / 1000
        },
        fromMeter = {
            it * 1000
        }
    ),
    // US Customary
    THOU(
        toMeter = {
            it / 25.4.toBigDecimal()
        },
        fromMeter = {
            it * 25.4.toBigDecimal()
        }
    ),
    INCH(
        toMeter = {
            it / 0.0254.toBigDecimal()
        },
        fromMeter = {
            it * 39.37.toBigDecimal()
        }
    ),
    FOOT(
        toMeter = {
            it / 0.3048.toBigDecimal()
        },
        fromMeter = { it * 3.28084.toBigDecimal() }
    ),
    YARD(
        toMeter = {
            it / 0.9144.toBigDecimal()
        },
        fromMeter = { it * 1.09361.toBigDecimal() }
    ),
    MILE(
        toMeter = {
            it / 1609.34.toBigDecimal()
        },
        fromMeter = { it * 0.000621371.toBigDecimal() }
    ),
    LEAGUE(
        toMeter = {
            it / 4828.03.toBigDecimal()
        },
        fromMeter = { it * 0.000207123.toBigDecimal() }
    ),
    // Marine
    NAUTICAL_MILE(
        toMeter = {
            it / 1852.toBigDecimal()
        },
        fromMeter = { it * 0.000539957.toBigDecimal() }
    ),
    FATHOM(
        toMeter = {
            it / 1.8288.toBigDecimal()
        },
        fromMeter = { it * 0.546807.toBigDecimal() }
    ),
}
