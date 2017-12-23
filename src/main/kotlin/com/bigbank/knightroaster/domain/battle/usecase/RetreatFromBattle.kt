package com.bigbank.knightroaster.domain.battle.usecase

import com.bigbank.knightroaster.domain.battle.entity.BattleResult

class RetreatFromBattle(
        private val gateway: RetreatFromBattleGateway
) {
    fun execute(battleId: Int): BattleResult = gateway.retreat(battleId)
}