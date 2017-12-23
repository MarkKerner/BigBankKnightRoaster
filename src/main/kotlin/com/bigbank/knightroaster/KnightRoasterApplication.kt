package com.bigbank.knightroaster

import com.bigbank.knightroaster.configuration.DataConfiguration
import com.bigbank.knightroaster.configuration.UseCaseConfiguration
import com.bigbank.knightroaster.domain.KnightRoaster
import com.bigbank.knightroaster.domain.battle.usecase.GetBattle
import com.bigbank.knightroaster.domain.battle.usecase.RetreatFromBattle
import com.bigbank.knightroaster.domain.battle.usecase.SendDragon
import com.bigbank.knightroaster.domain.dragon.DragonKeeper
import com.bigbank.knightroaster.domain.weather.GetWeatherOfBattle
import com.bigbank.knightroaster.presentation.ResultsLogger
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(
        DataConfiguration::class,
        UseCaseConfiguration::class
)
@SpringBootApplication
class KnightRoasterApplication {

    private val defaultDaysOfBattle = 100

    @Bean
    fun run(
            getBattle: GetBattle,
            getWeatherOfBattle: GetWeatherOfBattle,
            sendDragon: SendDragon,
            retreatFromBattle: RetreatFromBattle) = CommandLineRunner { args ->

        val daysOfBattle: Int =
                if (args.size == 1) parseArgument(args)
                else defaultDaysOfBattle

        KnightRoaster(
                ResultsLogger(),
                getBattle,
                getWeatherOfBattle,
                sendDragon,
                retreatFromBattle,
                DragonKeeper()).beginSlaughter(daysOfBattle)
    }

    private fun parseArgument(args: Array<String>) = try {
        args[0].toInt()
    } catch (e: NumberFormatException) {
        defaultDaysOfBattle
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(KnightRoasterApplication::class.java, *args)
}
