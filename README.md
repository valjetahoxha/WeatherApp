# WeatherApp


<img src="https://github.com/user-attachments/assets/b2e73a7a-1a8e-491b-bc03-ae1aea5558af" width="413">


<img width="413" alt="Screenshot 2024-07-29 at 10 40 54 AM" src="https://github.com/user-attachments/assets/0fc39c3e-8632-4249-a5b9-c0c03f24ad6f">

Project Overview:
The application is designed to download and display weather information from OpenWeatherMap. To begin, I needed to register on the OpenWeatherMap website to obtain an API key, which is essential for accessing the weather data.

Step-by-Step Instructions
API Registration and Testing:

First, I registered on the OpenWeatherMap website and got my API key from OpenWeatherMap Sign-Up.
With the API key, I tested the API using the following endpoints:
For metric units: https://api.openweathermap.org/data/2.5/weather?q=Budapest,hu&units=metric&appid=YOUR_API_KEY
For imperial units: https://api.openweathermap.org/data/2.5/weather?q=Budapest,hu&units=imperial&appid=YOUR_API_KEY
The complete API documentation can be found here.
Application Requirements:

The application consists of two screens:
City List Screen: Displays a list of cities.
Weather Details Screen: Shows detailed weather information for the selected city.
The city names do not need to be saved in a database or Room.
City List Screen:

This screen utilizes a LazyColumn to list cities.
It supports adding and removing cities.
A Scaffold base layout is recommended for this screen.
Cities can be added via a FloatingActionButton that triggers a Dialog for entering the city name, or alternatively, a Toolbar with an "Add city" menu can be used.
Weather Details Screen:

This screen appears when a city name is clicked on the city list screen.
The city name is passed as an argument to the weather details screen.
Network communication to fetch weather data should be implemented on this screen.
The screen displays weather information along with an icon/image that represents the weather condition.
