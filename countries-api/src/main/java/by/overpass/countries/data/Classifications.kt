package by.overpass.countries.data

/**
 * @property value string value of the classification
 */
enum class Classifications(
    val value: String,
) {
    HS92("hs92"),
    HS96("hs96"),
    HS02("hs02"),
    HS07("hs07"),
    SITC("sitc"),
    ;
}