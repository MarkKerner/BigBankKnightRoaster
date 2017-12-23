package com.bigbank.knightroaster.data.weather

import com.bigbank.knightroaster.data.weather.dto.WeatherDto
import com.bigbank.knightroaster.data.weather.mapper.WeatherMapper
import com.bigbank.knightroaster.domain.weather.GetWeatherGateway
import com.bigbank.knightroaster.domain.weather.EncodedWeather
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.RestTemplate

class WeatherDao(private val mapper: WeatherMapper) : GetWeatherGateway {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    override fun getWeather(battleId: Int): EncodedWeather {
        val dto = restTemplate.getForObject(
                buildUrl(battleId),
                WeatherDto::class.java
        )
        return mapper.toDomain(dto)
    }

    private fun buildUrl(battleId: Int) =
            String.format(
                    "http://www.dragonsofmugloar.com/weather/api/report/%d",
                    battleId)
}