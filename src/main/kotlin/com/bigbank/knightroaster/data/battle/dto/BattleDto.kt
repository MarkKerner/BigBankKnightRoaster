package com.bigbank.knightroaster.data.battle.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BattleDto(
        @JsonProperty("gameId") val gameId: Int,
        @JsonProperty("knight")val knight: KnightDto
)