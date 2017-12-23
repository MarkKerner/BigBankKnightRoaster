package com.bigbank.knightroaster.domain.battle.usecase

import com.bigbank.knightroaster.domain.battle.entity.BattleResult
import com.bigbank.knightroaster.domain.dragon.Dragon

interface SendDragonGateway {
    fun sendDragon(dragon: Dragon, battleId: Int): BattleResult
}