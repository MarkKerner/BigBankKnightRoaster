package com.bigbank.knightroaster.domain.battle

import com.bigbank.knightroaster.domain.dragon.Dragon

class SendDragon(
        private val gateway: SendDragonGateway,
        private val decoder: BattleResultDecoder
) {
    fun execute(dragon: Dragon, battleId: Int): BattleResult {
        val encodedBattleResult = gateway.sendDragon(dragon, battleId)
        return decoder.decode(encodedBattleResult)
    }
}