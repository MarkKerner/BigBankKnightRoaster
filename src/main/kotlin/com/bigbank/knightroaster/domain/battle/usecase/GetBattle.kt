package com.bigbank.knightroaster.domain.battle.usecase

import com.bigbank.knightroaster.domain.battle.entity.Battle

class GetBattle(private val gateway: GetBattleGateway) {
    fun execute(): Battle = gateway.getBattle()
}