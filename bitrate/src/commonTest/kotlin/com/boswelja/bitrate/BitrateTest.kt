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
    fun toLong_roundsDownCorrectly() {
        assertEquals(
            1,
            1.5.terabits.toLong(BitrateUnit.TEBIBITS)
        )
        assertEquals(
            1,
            1.49.terabits.toLong(BitrateUnit.TERABITS)
        )
    }

    @Test
    fun toLong_roundsUpCorrectly() {
        assertEquals(
            2,
            1.5.tebibits.toLong(BitrateUnit.TERABITS)
        )
        assertEquals(
            2,
            1.5.tebibits.toLong(BitrateUnit.TERABITS)
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
