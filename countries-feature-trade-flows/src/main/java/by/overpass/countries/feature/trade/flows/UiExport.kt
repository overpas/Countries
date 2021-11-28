package by.overpass.countries.feature.trade.flows

import androidx.compose.ui.graphics.Color

/**
 * @property value the total export value of the product
 * @property color the product color
 * @property name the product name
 * @property category the product's category
 */
data class UiExport(
    val value: Double,
    val color: Color,
    val name: String,
    val category: UiExportCategory? = null,
)