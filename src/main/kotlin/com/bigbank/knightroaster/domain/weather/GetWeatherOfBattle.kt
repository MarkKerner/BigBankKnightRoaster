package com.bigbank.knightroaster.domain.weather

class GetWeatherOfBattle(
        private val gateway: GetWeatherGateway,
        private val decoder: WeatherDecoder
) {
    fun execute(battleId: Int): Weather = decoder.decode(gateway.getWeather(battleId))
}