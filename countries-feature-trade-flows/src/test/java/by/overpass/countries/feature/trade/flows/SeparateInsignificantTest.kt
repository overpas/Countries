package by.overpass.countries.feature.trade.flows

import org.junit.Assert.assertEquals
import org.junit.Test

class SeparateInsignificantTest {

    @Test
    fun `insignificant items are grouped as Other`() {
        val values =
            listOf(100.0, 140.0, 130.0, 130.0, 130.0, 130.0, 130.0, 130.0, 130.0, 130.0, 98.0, 4.0)
        val insignificantSeparated = values.separateInsignificant(
            0.7,
            { it },
            { it.sum() },
        )
        assertEquals(
            listOf(140.0, 130.0, 130.0, 130.0, 130.0, 130.0, 130.0, 130.0, 332.0),
            insignificantSeparated
        )
    }
}