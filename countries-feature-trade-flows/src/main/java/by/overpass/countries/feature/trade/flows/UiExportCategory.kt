package by.overpass.countries.feature.trade.flows

import by.overpass.countries.data.products.Product

enum class UiExportCategory(val range: IntRange) {

    AnimalsAndAnimalProducts(1..5),
    VegetableProducts(6..15),
    Foodstuffs(16..24),
    MineralProducts(25..27),
    ChemicalsAndAlliedIndustries(28..38),
    PlasticsAndRubbers(39..40),
    RawHidesSkinsLeatherFurs(41..43),
    WoodAndWoodProducts(44..49),
    Textiles(50..63),
    FootwearAndHeadgear(64..67),
    StoneAndGlass(68..71),
    Metals(72..83),
    MachineryAndElectrical(84..85),
    Transportation(86..89),
    Miscellaneous(90..97),
    SpecialClassificationOrTemporaryLegislationAndModifications(98..99),
}

val Product.category: UiExportCategory?
    get() {
        val id = this.id.substring(2..3).toInt()
        return UiExportCategory.values()
            .find { category -> id in category.range }
    }