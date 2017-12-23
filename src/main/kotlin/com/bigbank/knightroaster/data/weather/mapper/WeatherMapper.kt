package com.bigbank.knightroaster.data.weather.mapper

import com.bigbank.knightroaster.data.weather.dto.WeatherDto
import com.bigbank.knightroaster.domain.weather.EncodedWeather

class WeatherMapper {
    fun toDomain(dto: WeatherDto): EncodedWeather {
        return EncodedWeather(
                dto.code,
                dto.message
        )
    }
}