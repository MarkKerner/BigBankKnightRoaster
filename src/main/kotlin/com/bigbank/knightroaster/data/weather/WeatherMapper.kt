package com.bigbank.knightroaster.data.weather

import com.bigbank.knightroaster.domain.weather.Weather
import com.bigbank.knightroaster.domain.weather.WeatherType

class WeatherMapper {
    fun toDomain(encodedWeather: WeatherDto): Weather {
        val type = when (encodedWeather.code) {
            "NMR" -> WeatherType.NORMAL
            "FUNDEFINEDG" -> WeatherType.FOG
            "HVA" -> WeatherType.FLOOD
            "SRO" -> WeatherType.STORM
            "T E" -> WeatherType.THE_LONG_DRY
            else -> WeatherType.UNDEFINED
        }
        return Weather(type, encodedWeather.message)
    }
}