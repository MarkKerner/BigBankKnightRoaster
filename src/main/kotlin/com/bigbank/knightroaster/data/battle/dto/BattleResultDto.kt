package com.bigbank.knightroaster.data.battle.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BattleResultDto(
        @JsonProperty("status") val status: String,
        @JsonProperty("message") val message: String
)