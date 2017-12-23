package com.bigbank.knightroaster.domain.dragon

import com.bigbank.knightroaster.domain.battle.entity.Knight

class DragonKeeper {
    fun getForKnight(knight: Knight): Dragon {
        return DragonFactory(knight).get()
    }

    fun getZenDragon() = Dragon(5, 5, 5, 5)

    fun getFloodDragon() = Dragon(5, 10, 5, 0)
}