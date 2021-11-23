/**
 * Colors
 */

@file:Suppress("MagicNumber")

package by.overpass.countries.ui.common.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFF_BB_86_FC)
val Purple500 = Color(0xFF_62_00_EE)
val Purple700 = Color(0xFF_37_00_B3)
val Teal200 = Color(0xFF_03_DA_C5)
val Transparent = Color(0x00_FF_FF_FF)
val DimmedBlack = Color(0x50_00_00_00)

val String.rgbColor: Color
    get() = Color(
        substring(1..2).toInt(16),
        substring(3..4).toInt(16),
        substring(5..6).toInt(16),
    )
