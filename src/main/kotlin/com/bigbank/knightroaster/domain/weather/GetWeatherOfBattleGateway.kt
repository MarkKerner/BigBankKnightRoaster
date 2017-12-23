package com.bigbank.knightroaster.domain.weather

import com.bigbank.knightroaster.domain.weather.Weather

interface GetWeatherOfBattleGateway {
    fun getWeather(battleId: Int): Weather
}