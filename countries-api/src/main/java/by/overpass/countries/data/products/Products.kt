/**
 * Products data
 */

package by.overpass.countries.data.products

import by.overpass.countries.data.OEC_LEGACY_API_BASE_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property data products in hs92
 */
@Serializable
data class Products(
    @SerialName("data")
    val data: List<Product>
)

/**
 * @property color Color used in visualizations, which is unique for each parent grouping.
 * @property displayId The main ID, with the parent grouping's 2-digit prefix stripped out,
 * which maps directly to the source data.
 * @property icon An icon representing the 2-digit parent grouping.
 * @property id ID used in our databases, which encodes the parent grouping's ID as the first
 * 2 digits (helps with nested visualizations).
 * @property image image of the product
 * @property imageAuthor Name of the Flickr user who took the photo used in the profile's header.
 * @property imageLink Direct link to the profile header image's Flickr page.
 * @property keywords Comma-separated list of common keywords used to aid search.
 * @property name Simplified human-readable name used throughout this site.
 * @property palette Array of colors extracted from the profile header image.
 * @property weight weight of the product
 */
@Serializable
data class Product(
    @SerialName("color")
    val color: String,
    @SerialName("display_id")
    val displayId: String?,
    @SerialName("icon")
    val icon: String,
    @SerialName("id")
    val id: String,
    @SerialName("image")
    val image: String?,
    @SerialName("image_author")
    val imageAuthor: String? = null,
    @SerialName("image_link")
    val imageLink: String? = null,
    @SerialName("keywords")
    val keywords: String?,
    @SerialName("name")
    val name: String,
    @SerialName("palette")
    val palette: String? = null,
    @SerialName("weight")
    val weight: Double? = null,
) {
    val iconUrl = OEC_LEGACY_API_BASE_URL + icon
    val imageUrl = OEC_LEGACY_API_BASE_URL + image
}
