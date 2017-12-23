package com.bigbank.knightroaster.configuration

import com.bigbank.knightroaster.data.battle.BattleDao
import com.bigbank.knightroaster.data.battle.mapper.BattleMapper
import com.bigbank.knightroaster.data.battle.mapper.BattleResultMapper
import com.bigbank.knightroaster.data.battle.mapper.DragonMapper
import com.bigbank.knightroaster.data.battle.mapper.KnightMapper
import com.bigbank.knightroaster.data.weather.WeatherDao
import com.bigbank.knightroaster.data.weather.WeatherMapper
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class DataConfiguration {
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder
                .build()
    }

    @Bean
    fun battleDao(restTemplate: RestTemplate) = BattleDao(
            restTemplate,
            BattleMapper(KnightMapper()),
            BattleResultMapper(),
            DragonMapper())

    @Bean
    fun weatherDao(restTemplate: RestTemplate) = WeatherDao(
            restTemplate,
            WeatherMapper())
}