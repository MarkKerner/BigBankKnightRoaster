package com.bigbank.knightroaster.configuration

import com.bigbank.knightroaster.data.battle.BattleDao
import com.bigbank.knightroaster.data.weather.WeatherDao
import com.bigbank.knightroaster.domain.battle.BattleResultDecoder
import com.bigbank.knightroaster.domain.battle.GetBattle
import com.bigbank.knightroaster.domain.battle.RetreatFromBattle
import com.bigbank.knightroaster.domain.battle.SendDragon
import com.bigbank.knightroaster.domain.weather.GetWeatherOfBattle
import com.bigbank.knightroaster.domain.weather.WeatherDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {
    @Bean
    fun getBattle(battleDao: BattleDao) = GetBattle(battleDao)

    @Bean
    fun getWeatherOfBattle(weatherDao: WeatherDao) = GetWeatherOfBattle(weatherDao, WeatherDecoder())

    @Bean
    fun battleResultDecoder() = BattleResultDecoder()

    @Bean
    fun sendDragon(battleDao: BattleDao,
                   battleResultDecoder: BattleResultDecoder) =
            SendDragon(battleDao, battleResultDecoder)

    @Bean
    fun retreatFromBattle(battleDao: BattleDao,
                          battleResultDecoder: BattleResultDecoder) =
            RetreatFromBattle(battleDao, battleResultDecoder)
}