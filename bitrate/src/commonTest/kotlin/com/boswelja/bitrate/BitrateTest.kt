package com.boswelja.bitrate

import com.boswelja.bitrate.Bitrate.Companion.bits
import com.boswelja.bitrate.Bitrate.Companion.gigabits
import com.boswelja.bitrate.Bitrate.Companion.kibibits
import com.boswelja.bitrate.Bitrate.Companion.tebibits
import com.boswelja.bitrate.Bitrate.Companion.terabits
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BitrateTest {

    @Test
    fun roundToWholeUnits_roundsDownCorrectly() {
        assertEquals(
            1,
            1.5.terabits.roundToWholeUnits(BitrateUnit.TEBIBITS)
        )
        assertEquals(
            1,
            1.49.terabits.roundToWholeUnits(BitrateUnit.TERABITS)
        )
        assertEquals(
            1,
            1.01.terabits.roundToWholeUnits(BitrateUnit.TERABITS)
        )
    }

    @Test
    fun roundToWholeUnits_roundsUpCorrectly() {
        assertEquals(
            2,
            1.51.terabits.roundToWholeUnits(BitrateUnit.TERABITS)
        )
        assertEquals(
            2,
            1.9.terabits.roundToWholeUnits(BitrateUnit.TERABITS)
        )
    }

    @Test
    fun toWholeUnits_roundsCorrectly() {
        assertEquals(
            1,
            1.5.terabits.toWholeUnits(BitrateUnit.TEBIBITS)
        )
        assertEquals(
            1,
            1.49.terabits.toWholeUnits(BitrateUnit.TERABITS)
        )
        assertEquals(
            1,
            1.01.terabits.toWholeUnits(BitrateUnit.TERABITS)
        )
        assertEquals(
            1,
            1.51.terabits.toWholeUnits(BitrateUnit.TERABITS)
        )
        assertEquals(
            1,
            1.9.terabits.toWholeUnits(BitrateUnit.TERABITS)
        )
    }

    @Test
    fun instantiation() {
        assertEquals(
            0.bits,
            Bitrate(0)
        )
        assertEquals(
            0f.gigabits,
            Bitrate(0)
        )
        assertEquals(
            0.0.kibibits,
            Bitrate(0)
        )
        assertEquals(
            Long.MAX_VALUE.bits,
            Bitrate(Long.MAX_VALUE)
        )
        assertEquals(
            Long.MIN_VALUE.bits,
            Bitrate(Long.MIN_VALUE)
        )
    }

    @Test
    fun compareTo_comparesSameSizes() {
        assertTrue(
            1.5.terabits.compareTo(1500.gigabits) == 0
        )
        assertTrue(
            1500.gigabits.compareTo(1.5.terabits) == 0
        )
    }

    @Test
    fun compareTo_comparesDifferentSizes() {
        assertTrue(
            1.5.terabits > 1.gigabits
        )
        assertTrue(
            1.gigabits < 1.5.terabits
        )
    }
}
