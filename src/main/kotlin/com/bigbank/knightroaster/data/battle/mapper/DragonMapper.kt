package com.bigbank.knightroaster.data.battle.mapper

import com.bigbank.knightroaster.data.battle.dto.DragonDto
import com.bigbank.knightroaster.domain.dragon.Dragon

class DragonMapper {
    fun toDto(domain: Dragon): DragonDto {
        return DragonDto(
                DragonDto.DragonPropertiesDto(
                        domain.scaleThickness,
                        domain.clawSharpness,
                        domain.wingStrength,
                        domain.fireBreath
                )
        )
    }
}