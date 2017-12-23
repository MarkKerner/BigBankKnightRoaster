package com.bigbank.knightroaster.domain.battle

import com.bigbank.knightroaster.domain.dragon.Dragon

interface SendDragonGateway {
    fun sendDragon(dragon: Dragon, battleId: Int): EncodedBattleResult
}