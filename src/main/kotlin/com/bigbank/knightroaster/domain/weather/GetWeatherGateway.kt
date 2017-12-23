package com.bigbank.knightroaster.domain.weather

interface GetWeatherGateway {
    fun getWeather(battleId: Int): EncodedWeather
}