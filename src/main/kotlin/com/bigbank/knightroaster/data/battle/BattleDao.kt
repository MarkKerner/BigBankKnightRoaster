package com.bigbank.knightroaster.data.battle

import com.bigbank.knightroaster.data.battle.dto.BattleDto
import com.bigbank.knightroaster.data.battle.dto.BattleResultDto
import com.bigbank.knightroaster.data.battle.dto.DragonDto
import com.bigbank.knightroaster.data.battle.mapper.*
import com.bigbank.knightroaster.domain.battle.entity.Battle
import com.bigbank.knightroaster.domain.battle.entity.BattleResult
import com.bigbank.knightroaster.domain.battle.usecase.GetBattleGateway
import com.bigbank.knightroaster.domain.battle.usecase.RetreatFromBattleGateway
import com.bigbank.knightroaster.domain.battle.usecase.SendDragonGateway
import com.bigbank.knightroaster.domain.dragon.Dragon
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

class BattleDao(
        private val restTemplate: RestTemplate,
        private val battleMapper: BattleMapper,
        private val resultMapper: BattleResultMapper,
        private val dragonMapper: DragonMapper) :
        GetBattleGateway,
        SendDragonGateway,
        RetreatFromBattleGateway {

    private val battleUrl = "http://www.dragonsofmugloar.com/api/game"
    private val battleSolutionUrl = "http://www.dragonsofmugloar.com/api/game/%d/solution"

    override fun getBattle(): Battle {
        val result = restTemplate.getForObject(
                battleUrl,
                BattleDto::class.java)
        return battleMapper.toDomain(result)
    }

    override fun sendDragon(dragon: Dragon, battleId: Int): BattleResult {
        val requestEntity = HttpEntity<DragonDto>(dragonMapper.toDto(dragon))

        val result = restTemplate.exchange(
                buildBattleSolutionUrl(battleId),
                HttpMethod.PUT,
                requestEntity,
                BattleResultDto::class.java
        )

        return resultMapper.toDomain(result.body)
    }

    override fun retreat(battleId: Int): BattleResult {
        val result = restTemplate.exchange(
                buildBattleSolutionUrl(battleId),
                HttpMethod.PUT,
                null,
                BattleResultDto::class.java
        )

        return resultMapper.toDomain(result.body)
    }

    private fun buildBattleSolutionUrl(battleId: Int) = String.format(
            battleSolutionUrl,
            battleId
    )
}