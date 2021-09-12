package by.overpass.countries.economies.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.overpass.countries.economies.data.OecApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountriesViewModel(private val oecApi: OecApi) : ViewModel() {

    @Suppress("TYPE_ALIAS")
    private val theCountries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val countries: StateFlow<List<Country>> = theCountries

    init {
        viewModelScope.launch {
            theCountries.value = oecApi.getCountries()
                .countries
                .filter { it.displayId != null }
        }
    }
}