package com.bigbank.knightroaster.domain.battle.usecase

import com.bigbank.knightroaster.domain.battle.entity.BattleResult
import com.bigbank.knightroaster.domain.dragon.Dragon

class SendDragon(
        private val gateway: SendDragonGateway
) {
    fun execute(dragon: Dragon, battleId: Int): BattleResult {
        return gateway.sendDragon(dragon, battleId)
    }
}