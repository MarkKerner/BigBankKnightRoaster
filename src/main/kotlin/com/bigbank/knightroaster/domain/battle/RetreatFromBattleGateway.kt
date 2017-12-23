package com.bigbank.knightroaster.domain.battle

interface RetreatFromBattleGateway {
    fun retreat(battleId: Int): EncodedBattleResult
}