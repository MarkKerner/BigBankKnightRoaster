package com.bigbank.knightroaster.domain

import com.bigbank.knightroaster.domain.battle.entity.*
import com.bigbank.knightroaster.domain.battle.usecase.GetBattle
import com.bigbank.knightroaster.domain.battle.usecase.RetreatFromBattle
import com.bigbank.knightroaster.domain.battle.usecase.SendDragon
import com.bigbank.knightroaster.domain.dragon.Dragon
import com.bigbank.knightroaster.domain.dragon.DragonKeeper
import com.bigbank.knightroaster.domain.presentation.ResultsPresenter
import com.bigbank.knightroaster.domain.weather.GetWeatherOfBattle
import com.bigbank.knightroaster.domain.weather.Weather
import com.bigbank.knightroaster.domain.weather.WeatherType
import java.util.concurrent.*

class KnightRoaster(
        private val presenter: ResultsPresenter,
        private val getBattle: GetBattle,
        private val getWeather: GetWeatherOfBattle,
        private val sendDragon: SendDragon,
        private val retreatFromBattle: RetreatFromBattle,
        private val dragonKeeper: DragonKeeper
) {
    fun beginSlaughter(daysOfBattle: Int) {
        presenter.displayBattleStart()

        val startTime = System.currentTimeMillis()
        val results = War(daysOfBattle).fight()
        val elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000.0

        displayBattleResults(results)
        displayFinalResult(results, elapsedSeconds)
    }

    private inner class War(
            private val daysOfBattle: Int) {
        private val executor = Executors.newCachedThreadPool()

        fun fight(): List<BattleResult> {
            val futures = executor.invokeAll(createBattles())
            executor.shutdown()

            return extractResults(futures)
        }

        private fun createBattles() = List(daysOfBattle, { Callable { fightBattle() } })

        private fun fightBattle(): BattleResult {
            val battle: Battle = getBattle.execute()
            val weather: Weather = getWeather.execute(battle.id)

            return if (weather.type == WeatherType.STORM) {
                retreatFromBattle.execute(battle.id)
            } else {
                sendDragon.execute(
                        getWorthyDragon(weather, battle.knight),
                        battle.id)
            }
        }

        private fun getWorthyDragon(weather: Weather, knight: Knight): Dragon {
            return when (weather.type) {
                WeatherType.FLOOD -> dragonKeeper.getFloodDragon()
                WeatherType.THE_LONG_DRY -> dragonKeeper.getZenDragon()
                else -> dragonKeeper.getForKnight(knight)
            }
        }

        private fun extractResults(futures: List<Future<BattleResult>>) =
                futures.map { it.get() }
    }

    private fun displayBattleResults(results: List<BattleResult>) {
        results.forEach { presenter.displayBattleResult(it) }
    }

    private fun displayFinalResult(results: List<BattleResult>, elapsedSeconds: Double) {
        presenter.displayFinalResult(
                FinalResult(
                        results.size,
                        results.count { it.type == BattleResultType.VICTORY },
                        elapsedSeconds
                ))
    }
}