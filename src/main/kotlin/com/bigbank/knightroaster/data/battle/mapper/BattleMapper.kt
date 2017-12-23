package com.bigbank.knightroaster.data.battle.mapper

import com.bigbank.knightroaster.data.battle.dto.BattleDto
import com.bigbank.knightroaster.domain.battle.entity.Battle

class BattleMapper(
        private val knightMapper: KnightMapper
) {
    fun toDomain(dto: BattleDto) = Battle(
            dto.gameId,
            knightMapper.toDomain(dto.knight)
    )
}