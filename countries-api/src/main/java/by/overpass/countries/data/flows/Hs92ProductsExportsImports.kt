/**
 * Trade Flows DTOs
 */

package by.overpass.countries.data.flows

import by.overpass.countries.data.ALL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property data exports imports data per hs92 product
 */
@Serializable
data class Hs92ProductsExportsImports(
    @SerialName("data")
    val data: List<Hs92ProductExportsImports>
)

/**
 * @property exportRca export RCA
 * @property exportVal total export value in usd
 * @property exportValGrowthPct export growth percent (last year)?
 * @property exportValGrowthPct5 export growth percent (last 5 years)?
 * @property exportValGrowthVal export growth (last year)?
 * @property exportValGrowthVal5 export growth (last 5 years)?
 * @property hs92Id product category id in hs92 classification
 * @property hs92IdLen product category id length
 * @property importRca import RCA
 * @property importVal total import value in usd
 * @property importValGrowthPct import growth percent (last year)?
 * @property importValGrowthPct5 import growth percent (last 5 years)?
 * @property importValGrowthVal import growth (last year)?
 * @property importValGrowthVal5 import growth (last 5 years)?
 * @property originId country id
 * @property destinationId export destination id ("all" means export to all destinations in total)
 * @property year to which the data corresponds
 */
@Serializable
data class Hs92ProductExportsImports(
    @SerialName("export_rca")
    val exportRca: Double? = null,
    @SerialName("export_val")
    val exportVal: Double? = null,
    @SerialName("export_val_growth_pct")
    val exportValGrowthPct: Double? = null,
    @SerialName("export_val_growth_pct_5")
    val exportValGrowthPct5: Double? = null,
    @SerialName("export_val_growth_val")
    val exportValGrowthVal: Double? = null,
    @SerialName("export_val_growth_val_5")
    val exportValGrowthVal5: Double? = null,
    @SerialName("hs92_id")
    val hs92Id: String,
    @SerialName("hs92_id_len")
    val hs92IdLen: Double,
    @SerialName("import_rca")
    val importRca: Double? = null,
    @SerialName("import_val")
    val importVal: Double? = null,
    @SerialName("import_val_growth_pct")
    val importValGrowthPct: Double? = null,
    @SerialName("import_val_growth_pct_5")
    val importValGrowthPct5: Double? = null,
    @SerialName("import_val_growth_val")
    val importValGrowthVal: Double? = null,
    @SerialName("import_val_growth_val_5")
    val importValGrowthVal5: Double? = null,
    @SerialName("origin_id")
    val originId: String,
    @SerialName("dest_id")
    val destinationId: String = ALL,
    @SerialName("year")
    val year: Double,
)