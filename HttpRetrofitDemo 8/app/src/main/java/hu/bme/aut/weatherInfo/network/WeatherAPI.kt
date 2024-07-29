package hu.bme.aut.weatherInfo.network

import hu.bme.aut.weatherInfo.data.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

//
// HOST: https://api.openweathermap.org/data/2.5/
// PATH: mars-photos/api/v1/rovers/curiosity/photos
// QUERY PARAMS:
// ?
// earth_date=2024-1-4
// &
// api_key=DEMO_KEY

interface WeatherAPI {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appid: String = "c54cc49ff3b152fbc1b0f7a6a49d971c"
    ): WeatherInfo

}
