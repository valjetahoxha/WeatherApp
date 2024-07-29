package hu.bme.aut.weatherInfo.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

import hu.bme.aut.weatherInfo.R
import hu.bme.aut.weatherInfo.data.WeatherInfo


@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    cityLocation: String
) {
    LaunchedEffect(Unit) {
        mainViewModel.getWeather(cityLocation, "metric", "c54cc49ff3b152fbc1b0f7a6a49d971c")
    }
    Column {

        when (mainViewModel.weatherUiState) {
            is WeatherUiState.Init -> {}
            is WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Success ->
                WeatherPhotoCard(
                    (mainViewModel.weatherUiState as WeatherUiState.Success).weatherResult,
                    cityLocation
                )
            is WeatherUiState.Error -> Text(
                text = "Error: " +
                        "${(mainViewModel.weatherUiState as WeatherUiState.Error).errorMsg}"
            )
        }
    }
}
@Composable
fun WeatherPhotoCard (
    weatherResult: WeatherInfo,
    cityLocation: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.temperature, cityLocation)
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        "https://openweathermap.org/img/w/${
                            weatherResult.weather?.get(0)?.icon
                        }.png"
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = stringResource(R.string.temperature, weatherResult.main?.temp.toString())
            )

            Text(
                text = "Feels Like: ${weatherResult.main?.feelsLike}"
            )

            Text(
                text = "Max Temperature: ${weatherResult.main?.humidity}"
            )

            Text(
                text = "Min Temperature: ${weatherResult.main?.temp}"
            )

            Text(
                text = "Pressure: ${weatherResult.main?.pressure}"
            )

        }
    }
}