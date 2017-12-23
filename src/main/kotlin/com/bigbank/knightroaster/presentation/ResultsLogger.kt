package com.bigbank.knightroaster.presentation

import com.bigbank.knightroaster.domain.presentation.ResultsPresenter
import com.bigbank.knightroaster.domain.battle.BattleResult

class ResultsLogger : ResultsPresenter {
    override fun displayBattleStart() {
        println("David Attenborough: \"The long days of battle have begun.\"")
    }

    override fun displayBattleResult(result: BattleResult) {
        println("///")
        println(result.type)
        println(result.description)
        println("///")
    }

    override fun displayFinalResult(winPercentage: Double, elapseSeconds: Double) {
        println("David Attenborough: \"The days of battle have finally come to an end.\n    As every time before this, " +
                "not one knight survived.\"")
        println(String.format("Victory percentage was: %.1f%%", winPercentage))
        println("Elapsed seconds: " + elapseSeconds)
    }
}