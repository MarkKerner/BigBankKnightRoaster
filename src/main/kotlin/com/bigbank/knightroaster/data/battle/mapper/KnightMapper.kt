package com.bigbank.knightroaster.data.battle.mapper

import com.bigbank.knightroaster.data.battle.dto.KnightDto
import com.bigbank.knightroaster.domain.battle.entity.Knight

class KnightMapper {
    fun toDomain(dto: KnightDto) =
            Knight(
                    dto.name,
                    dto.attack,
                    dto.armor,
                    dto.agility,
                    dto.endurance
            )
}