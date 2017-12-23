package com.bigbank.knightroaster.domain.battle.usecase

import com.bigbank.knightroaster.domain.battle.entity.Battle

interface GetBattleGateway {
    fun getBattle(): Battle
}