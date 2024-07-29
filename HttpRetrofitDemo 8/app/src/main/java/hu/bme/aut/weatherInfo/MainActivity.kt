package hu.bme.aut.weatherInfo

import hu.bme.aut.weatherInfo.data.navigation.MainNavigation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.weatherInfo.screen.CityScreen
import hu.bme.aut.weatherInfo.screen.MainScreen
import hu.bme.aut.weatherInfo.ui.theme.HttpRetrofitDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HttpRetrofitDemoTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    WeatherNavHost()
                }
            }
        }
    }
}

@Composable
fun WeatherNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "CityScreen"
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {
        composable("CityScreen") {
            CityScreen(
                onNavigateToMain = { cityLocation ->
                    navController.navigate(
                        MainNavigation.MainScreen.createRoute(cityLocation))
                }
            )

        }

        composable(MainNavigation.MainScreen.route) {
            backStackEntry ->
                    val cityLocation = backStackEntry.arguments?.getString("cityLocation")
            cityLocation?.let {
                MainScreen(cityLocation = it)
            }
        }
    }
}