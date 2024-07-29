package hu.bme.aut.weatherInfo.data.navigation

sealed class MainNavigation(val route: String) {
    object CityScreen : MainNavigation("cityscreen")
    object MainScreen : MainNavigation("mainscreen?cityLocation={cityLocation}") {
        fun createRoute(cityLocation: String) : String {
            return "mainscreen?cityLocation=$cityLocation"
        }
    }
}