package com.bigbank.knightroaster.domain.battle.usecase

import com.bigbank.knightroaster.domain.battle.entity.BattleResult

interface RetreatFromBattleGateway {
    fun retreat(battleId: Int): BattleResult
}