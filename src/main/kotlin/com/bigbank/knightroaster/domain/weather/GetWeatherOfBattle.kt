package com.bigbank.knightroaster.domain.weather

class GetWeatherOfBattle(
        private val gateway: GetWeatherOfBattleGateway
) {
    fun execute(battleId: Int): Weather = gateway.getWeather(battleId)
}