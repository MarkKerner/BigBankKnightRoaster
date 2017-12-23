package com.bigbank.knightroaster.data.weather

import com.bigbank.knightroaster.domain.weather.GetWeatherOfBattleGateway
import com.bigbank.knightroaster.domain.weather.Weather
import org.springframework.web.client.RestTemplate

class WeatherDao(
        private val restTemplate: RestTemplate,
        private val mapper: WeatherMapper) : GetWeatherOfBattleGateway {

    override fun getWeather(battleId: Int): Weather {
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