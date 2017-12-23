package com.bigbank.knightroaster.data.battle

import com.bigbank.knightroaster.data.battle.dto.BattleDto
import com.bigbank.knightroaster.data.battle.dto.BattleResultDto
import com.bigbank.knightroaster.data.battle.dto.DragonDto
import com.bigbank.knightroaster.data.battle.mapper.BattleMapper
import com.bigbank.knightroaster.data.battle.mapper.BattleResultMapper
import com.bigbank.knightroaster.data.battle.mapper.DragonMapper
import com.bigbank.knightroaster.domain.battle.*
import com.bigbank.knightroaster.domain.dragon.Dragon
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class BattleDao(
        private val battleMapper: BattleMapper,
        private val resultMapper: BattleResultMapper,
        private val dragonMapper: DragonMapper) :
        GetBattleGateway,
        SendDragonGateway,
        RetreatFromBattleGateway {

    @Autowired private lateinit var restTemplate: RestTemplate

    override fun getBattle(): Battle {
        val result = restTemplate.getForObject(
                "http://www.dragonsofmugloar.com/api/game",
                BattleDto::class.java)
        return battleMapper.toDomain(result)
    }

    override fun sendDragon(dragon: Dragon, battleId: Int): EncodedBattleResult {
        val requestEntity = HttpEntity<DragonDto>(dragonMapper.toDto(dragon))

        val result = restTemplate.exchange(
                buildBattleUrl(battleId),
                HttpMethod.PUT,
                requestEntity,
                BattleResultDto::class.java
        )

        return resultMapper.toDomain(result.body)
    }

    override fun retreat(battleId: Int): EncodedBattleResult {
        val result = restTemplate.exchange(
                buildBattleUrl(battleId),
                HttpMethod.PUT,
                null,
                BattleResultDto::class.java
        )

        return resultMapper.toDomain(result.body)
    }

    private fun buildBattleUrl(battleId: Int) = String.format(
            "http://www.dragonsofmugloar.com/api/game/%d/solution",
            battleId
    )
}