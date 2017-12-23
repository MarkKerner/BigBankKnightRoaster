package com.bigbank.knightroaster.data.battle.dto

import com.fasterxml.jackson.annotation.JsonProperty

class KnightDto(
        @JsonProperty("name") val name: String,
        @JsonProperty("attack") val attack: Int,
        @JsonProperty("armor") val armor: Int,
        @JsonProperty("agility") val agility: Int,
        @JsonProperty("endurance") val endurance: Int)