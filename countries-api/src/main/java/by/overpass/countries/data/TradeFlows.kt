package by.overpass.countries.data

/**
 * @property value string value of the trade flow
 */
enum class TradeFlows(
    val value: String,
) {
    EXPORT("export"),
    IMPORT("import"),
    ;
}