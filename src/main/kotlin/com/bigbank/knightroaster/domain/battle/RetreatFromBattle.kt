package com.bigbank.knightroaster.domain.battle

class RetreatFromBattle(
        private val gateway: RetreatFromBattleGateway,
        private val decoder: BattleResultDecoder
) {
    fun execute(battleId: Int): BattleResult = decoder.decode(gateway.retreat(battleId))
}