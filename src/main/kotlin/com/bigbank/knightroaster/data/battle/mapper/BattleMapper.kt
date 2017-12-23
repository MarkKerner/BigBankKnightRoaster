package com.bigbank.knightroaster.data.battle.mapper

import com.bigbank.knightroaster.data.battle.dto.KnightDto
import com.bigbank.knightroaster.data.battle.dto.BattleDto
import com.bigbank.knightroaster.domain.battle.Battle
import com.bigbank.knightroaster.domain.battle.Knight

class BattleMapper {
    fun toDomain(dto: BattleDto) = Battle(
            dto.gameId,
            knightToDomain(dto.knight)
    )

    private fun knightToDomain(dto: KnightDto) =
            Knight(
                    dto.name,
                    dto.attack,
                    dto.armor,
                    dto.agility,
                    dto.endurance
            )
}