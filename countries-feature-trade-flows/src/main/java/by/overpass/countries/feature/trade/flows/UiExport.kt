package by.overpass.countries.feature.trade.flows

import androidx.compose.ui.graphics.Color

data class UiExport(
    val value: Double,
    val color: Color,
    val name: String,
    val category: UiExportCategory? = null,
)