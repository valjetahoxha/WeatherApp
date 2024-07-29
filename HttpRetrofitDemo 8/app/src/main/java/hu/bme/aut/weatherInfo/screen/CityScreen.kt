package hu.bme.aut.weatherInfo.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.weatherInfo.R
import androidx.compose.foundation.lazy.items

@Composable
fun CityScreen(
    citiesViewModel: CitiesViewModel = viewModel(),
    onNavigateToMain: (String) -> Unit
) {

    var city by rememberSaveable {
        mutableStateOf("")
    }
    var cityEmptyState by rememberSaveable { mutableStateOf(false) }

    fun validate() {
        cityEmptyState = city.isEmpty()
    }

    Column {
        OutlinedTextField(
            value = city,
            onValueChange = {
                city = it
                validate()
            },
            label = { Text(text = stringResource(R.string.add_city_name)) },
            isError = cityEmptyState
        )

        val inputErrorState = cityEmptyState

        if (inputErrorState) {
            Text(
                text = when {
                    cityEmptyState -> {
                        stringResource(R.string.city_empty)
                    }
                    else -> ""
                },
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
        Row {

            Button(
                enabled = !inputErrorState,
                onClick = {
                    if (city.isNotEmpty()) {
                        citiesViewModel.addToCityList(city)
                    }
                }
            ) {
                Text(text = stringResource(R.string.add_city_name))
            }

            Button(onClick = {
                citiesViewModel.deleteAllCities()
            }) {
                Text(text = stringResource(R.string.delete_all))
            }
        }

        if (citiesViewModel.getAllCities().isEmpty()) {
            Text(text = stringResource(R.string.no_city))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(citiesViewModel.getAllCities()) {
                    CityCard( it,
                        onDeleteItem = { citiesViewModel.deleteAllCities() },
                        onNavigateToMain,
                    )
                }
            }
        }

    }
}
@Composable
fun CityCard(
    city: String,
        onDeleteItem: () -> Unit = {},
    onNavigateToMain: (String) -> Unit,
    cityViewModel: CitiesViewModel = viewModel(),
        ) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(onClick = {
                onNavigateToMain(city)
            }) {
                Text(text = city)
            }
            Spacer(modifier = Modifier.fillMaxSize(0.55f))
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier.clickable {
                    onDeleteItem()
                },
                tint = Color.Blue
            )
        }
    }
}