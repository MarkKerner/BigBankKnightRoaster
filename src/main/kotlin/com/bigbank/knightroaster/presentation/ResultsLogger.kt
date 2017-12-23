package com.bigbank.knightroaster.presentation

import com.bigbank.knightroaster.domain.presentation.ResultsPresenter
import com.bigbank.knightroaster.domain.battle.entity.BattleResult
import com.bigbank.knightroaster.domain.battle.entity.FinalResult

class ResultsLogger : ResultsPresenter {
    override fun displayBattleStart() {
        println("---------")
        println("David Attenborough: \"The long days of battle have begun.\"")
        println("---------")
    }

    override fun displayBattleResult(result: BattleResult) {
        println("---------")
        println(result.type)
        println(result.description)
        println("---------")
    }

    override fun displayFinalResult(result: FinalResult) {
        println("---------")
        println("David Attenborough: \"The days of battle have finally come to an end.\n    Like every time before this, " +
                "not one knight survived.\"")
        println()
        println("Days fought: " + result.daysFought)
        println("Days victorious: " + result.daysVictorious)
        println(String.format("Victory percentage was: %.1f%%", calculateVictoryPercentage(result)))
        println("Battle length in human seconds: " + result.elapsedSeconds)
        println("---------")
    }

    private fun calculateVictoryPercentage(result: FinalResult) =
            (result.daysVictorious.toDouble() / result.daysFought.toDouble()) * 100.0
}