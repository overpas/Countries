/**
 * Countries data
 */

package by.overpass.countries.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property name Simplified human-readable name used throughout
 * @property bordersLand Array of IDs for countries that it borders by land
 * @property bordersMaritime Array of IDs for countries that it borders by sea (within a reasonable distance).
 * @property color Color used in visualizations, which is unique for each continent
 * @property comtradeName Full name of the country, as reported by COMTRADE
 * @property displayId 3-digit standard ISO ID, used in all of our URLs
 * @property icon For countries, this is the country's flag. For continents, this is the shape of the continent
 * @property id ID used in our databases, which encodes the continent's ID as the first 2 digits
 * (helps with nested visualizations)
 * @property id2Char 2-digit standard ISO ID
 * @property idNum Numeric values which match to the raw data we injest from CEPII
 * @property imageAuthor Name of the Flickr user who took the photo used in the profile's header
 * @property imageLink Direct link to the profile header image's Flickr page
 * @property palette Array of colors extracted from the profile header image
 * @property image Link to the country image
 * @property weight Who knows?
 */
@Serializable
data class Country(
    @SerialName("name")
    val name: String,
    @SerialName("borders_land")
    val bordersLand: String? = null,
    @SerialName("borders_maritime")
    val bordersMaritime: String? = null,
    @SerialName("color")
    val color: String,
    @SerialName("comtrade_name")
    val comtradeName: String? = null,
    @SerialName("display_id")
    val displayId: String?,
    @SerialName("icon")
    val icon: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("id_2char")
    val id2Char: String? = null,
    @SerialName("id_num")
    val idNum: String? = null,
    @SerialName("image")
    val image: String,
    @SerialName("image_author")
    val imageAuthor: String? = null,
    @SerialName("image_link")
    val imageLink: String? = null,
    @SerialName("palette")
    val palette: String? = null,
    @SerialName("weight")
    val weight: Double? = null,
) {
    val imageUrl: String = OEC_LEGACY_API_BASE_URL + image
}

/**
 * @property countries the countries
 */
@Serializable
data class CountriesResponse(
    @SerialName("data")
    val countries: List<Country>,
)