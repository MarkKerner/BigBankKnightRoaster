package com.bigbank.knightroaster.domain.battle

class GetBattle(private val gateway: GetBattleGateway) {
    fun execute(): Battle = gateway.getBattle()
}