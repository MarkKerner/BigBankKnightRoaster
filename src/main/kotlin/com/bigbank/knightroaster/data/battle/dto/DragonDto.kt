package com.bigbank.knightroaster.data.battle.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DragonDto(
        @JsonProperty("dragon")val dragon: DragonPropertiesDto
) {
    data class DragonPropertiesDto(
            @JsonProperty("scaleThickness") val scaleThickness: Int,
            @JsonProperty("clawSharpness") val clawSharpness: Int,
            @JsonProperty("wingStrength") val wingStrength: Int,
            @JsonProperty("fireBreath") val fireBreath: Int
    )
}