package hu.bme.aut.weatherInfo.screen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import hu.bme.aut.weatherInfo.data.WeatherInfo
import hu.bme.aut.weatherInfo.network.WeatherAPI
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class MainViewModel @Inject constructor(
    var weatherAPI: WeatherAPI
) : ViewModel() {

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Init)

    fun getWeather(city: String, units: String, apiKey: String) {
        weatherUiState = WeatherUiState.Loading

        viewModelScope.launch {
            try {
//            starts the network communication and returns a weather object
                val result = weatherAPI.getWeather(city, units, apiKey)

                weatherUiState = WeatherUiState.Success(result)

            } catch (e: java.lang.Exception) {
                weatherUiState = WeatherUiState.Error(e.message!!)
            }
        }
    }

}

sealed interface WeatherUiState {
    object Init : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val weatherResult: WeatherInfo) : WeatherUiState
    data class Error(val errorMsg: String) : WeatherUiState
}
/*
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val weatherData by viewModel.weatherData.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        weatherData?.let { data ->
            // Display your weather data
        }
        error?.let { errorMsg ->
            Text("Error: $errorMsg")
        }
    }
}*/



//    private var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Init)
//
//    fun getPhotos(date: String, apiKey: String){
//        fun loadWeatherInfo(cityName: String) {
//
//        }
//            viewModelScope.launch {
//            try {
//                val result = WeatherAPI.(date, apiKey)
//
//            }
//            catch (e: Exception){
//
//                weatherUiState = WeatherUiState.Error(e.message !!)
//            }
//        }
//
//    }
//}




