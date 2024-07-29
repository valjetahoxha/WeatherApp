package hu.bme.aut.weatherInfo.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel





class CitiesViewModel : ViewModel(){
    private var _cityList =
        mutableStateListOf<String>()

    fun addToCityList(cityItem:String ) {
        _cityList.add(cityItem)
    }


    fun removeCity(cityItem:String ) {
        _cityList.remove(cityItem)
    }

    fun deleteAllCities() {
        _cityList.clear()
    }

    fun getAllCities(): List<String> {
        return _cityList
    }

}


