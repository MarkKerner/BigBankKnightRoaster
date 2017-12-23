package com.bigbank.knightroaster.configuration

import com.bigbank.knightroaster.data.battle.BattleDao
import com.bigbank.knightroaster.data.weather.WeatherDao
import com.bigbank.knightroaster.data.battle.mapper.BattleResultMapper
import com.bigbank.knightroaster.domain.battle.usecase.GetBattle
import com.bigbank.knightroaster.domain.battle.usecase.RetreatFromBattle
import com.bigbank.knightroaster.domain.battle.usecase.SendDragon
import com.bigbank.knightroaster.domain.weather.GetWeatherOfBattle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {
    @Bean
    fun getBattle(battleDao: BattleDao) = GetBattle(battleDao)

    @Bean
    fun getWeatherOfBattle(weatherDao: WeatherDao) = GetWeatherOfBattle(weatherDao)

    @Bean
    fun battleResultDecoder() = BattleResultMapper()

    @Bean
    fun sendDragon(battleDao: BattleDao) =
            SendDragon(battleDao)

    @Bean
    fun retreatFromBattle(battleDao: BattleDao) =
            RetreatFromBattle(battleDao)
}