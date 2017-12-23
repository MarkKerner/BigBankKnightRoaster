package com.bigbank.knightroaster.data.battle.mapper

import com.bigbank.knightroaster.data.battle.dto.BattleResultDto
import com.bigbank.knightroaster.domain.battle.entity.BattleResult
import com.bigbank.knightroaster.domain.battle.entity.BattleResultType

class BattleResultMapper {
    fun toDomain(encoded: BattleResultDto): BattleResult {
        val type = when (encoded.status) {
            "Victory" -> BattleResultType.VICTORY
            "Defeat" -> BattleResultType.DEFEAT
            else -> throw IllegalStateException()
        }
        return BattleResult(type, encoded.message)
    }
}