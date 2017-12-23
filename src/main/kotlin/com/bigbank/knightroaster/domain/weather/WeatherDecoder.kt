package com.bigbank.knightroaster.domain.weather

class WeatherDecoder {
    fun decode(encodedWeather: EncodedWeather): Weather {
        val type = when (encodedWeather.code) {
            "NMR" -> WeatherType.NORMAL
            "FUNDEFINEDG" -> WeatherType.FOG
            "HVA" -> WeatherType.FLOOD
            "SRO" -> WeatherType.STORM
            "T E" -> WeatherType.THE_LONG_DRY
            else -> WeatherType.UNDEFINED
        }
        return Weather(type, encodedWeather.description)
    }
}