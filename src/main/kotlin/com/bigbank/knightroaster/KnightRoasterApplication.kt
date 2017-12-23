package com.bigbank.knightroaster

import com.bigbank.knightroaster.domain.KnightRoaster
import com.bigbank.knightroaster.domain.battle.GetBattle
import com.bigbank.knightroaster.domain.battle.RetreatFromBattle
import com.bigbank.knightroaster.domain.battle.SendDragon
import com.bigbank.knightroaster.domain.dragon.DragonKeeper
import com.bigbank.knightroaster.domain.weather.GetWeatherOfBattle
import com.bigbank.knightroaster.presentation.ResultsLogger
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@EnableAutoConfiguration
@SpringBootApplication
class KnightRoasterApplication {
    @Bean
    fun run(
            getBattle: GetBattle,
            getWeatherOfBattle: GetWeatherOfBattle,
            sendDragon: SendDragon,
            retreatFromBattle: RetreatFromBattle) = CommandLineRunner {
        KnightRoaster(
                ResultsLogger(),
                getBattle,
                getWeatherOfBattle,
                sendDragon,
                retreatFromBattle,
                DragonKeeper()).beginSlaughter(100)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(KnightRoasterApplication::class.java)
}
