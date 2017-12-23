package com.bigbank.knightroaster.domain.presentation

import com.bigbank.knightroaster.domain.battle.entity.BattleResult
import com.bigbank.knightroaster.domain.battle.entity.FinalResult

interface ResultsPresenter {
    fun displayBattleStart()

    fun displayBattleResult(result: BattleResult)

    fun displayFinalResult(result: FinalResult)
}