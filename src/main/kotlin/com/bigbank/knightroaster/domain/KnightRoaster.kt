package com.bigbank.knightroaster.domain

import com.bigbank.knightroaster.domain.battle.*
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

        val executor = Executors.newCachedThreadPool()
        val startTime = System.currentTimeMillis()
        val results = executor.invokeAll(List(daysOfBattle, { Callable { fightBattle() } }))
        val elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0
        executor.shutdown()

        presentBattleResults(results)
        presentFinalResult(results, elapsedTime)
    }

    private fun presentBattleResults(results: MutableList<Future<BattleResult>>) {
        results.forEach { presenter.displayBattleResult(it.get()) }
    }

    private fun presentFinalResult(results: MutableList<Future<BattleResult>>, elapsedTime: Double) {
        val daysFought = results.size
        val daysWon = results.filter { it.get().type == BattleResultType.VICTORY }.size
        val winPercentage = (daysWon.toDouble() / daysFought.toDouble()) * 100.0

        presenter.displayFinalResult(winPercentage, elapsedTime)
    }

    private fun fightBattle(): BattleResult {
        val battle: Battle = getBattle.execute()
        val weather: Weather = getWeather.execute(battle.id)

        return if (weather.type == WeatherType.STORM) {
            retreatFromBattle.execute(battle.id)
        } else {
            val worthyDragon = when (weather.type) {
                WeatherType.FLOOD -> dragonKeeper.getFloodDragon()
                WeatherType.THE_LONG_DRY -> dragonKeeper.getZenDragon()
                else -> dragonKeeper.getForKnight(battle.knight)
            }
            sendDragon.execute(worthyDragon, battle.id)
        }
    }
}