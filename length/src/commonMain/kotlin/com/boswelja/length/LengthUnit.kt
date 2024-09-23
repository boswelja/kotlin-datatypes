package com.boswelja.length

interface LengthUnit {

    fun fromMeters(meters: Double): Double

    fun toMeters(unit: Double): Double
}

enum class MetricLengthUnit : LengthUnit {
    METER;

    override fun fromMeters(meters: Double): Double {
        TODO("Not yet implemented")
    }

    override fun toMeters(unit: Double): Double {
        TODO("Not yet implemented")
    }
}
