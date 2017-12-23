package com.bigbank.knightroaster.domain.battle

class BattleResultDecoder {
    fun decode(encoded: EncodedBattleResult): BattleResult {
        val type = when (encoded.status) {
            "Victory" -> BattleResultType.VICTORY
            "Defeat" -> BattleResultType.DEFEAT
            else -> throw IllegalStateException()
        }
        return BattleResult(type, encoded.message)
    }
}