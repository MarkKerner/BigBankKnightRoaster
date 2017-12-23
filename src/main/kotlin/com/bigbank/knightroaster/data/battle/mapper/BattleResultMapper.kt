package com.bigbank.knightroaster.data.battle.mapper

import com.bigbank.knightroaster.data.battle.dto.BattleResultDto
import com.bigbank.knightroaster.domain.battle.EncodedBattleResult

class BattleResultMapper {
    fun toDomain(dto: BattleResultDto): EncodedBattleResult =
            EncodedBattleResult(
                    dto.status,
                    dto.message)
}