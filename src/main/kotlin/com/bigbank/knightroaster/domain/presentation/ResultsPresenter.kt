package com.bigbank.knightroaster.domain.presentation

import com.bigbank.knightroaster.domain.battle.BattleResult

interface ResultsPresenter {

    fun displayBattleStart()

    fun displayBattleResult(
            result: BattleResult)

    fun displayFinalResult(
            winPercentage: Double,
            elapseSeconds: Double)
}