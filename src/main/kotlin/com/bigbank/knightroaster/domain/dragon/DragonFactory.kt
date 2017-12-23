package com.bigbank.knightroaster.domain.dragon

import com.bigbank.knightroaster.domain.battle.entity.Knight

class DragonFactory(knight: Knight) {

    private open class Property(private var value: Int) {
        companion object {
            val MIN = 0
            val MAX = 10
        }

        fun getValue() = value

        fun setValue(value: Int) {
            assertValueInAllowedRange(value)
            this.value = value
        }

        fun isMin() = value == MIN

        fun isMax() = value == MAX

        private fun assertValueInAllowedRange(value: Int) {
            check(value in MIN..MAX)
        }
    }

    private class KnightProperty(
            value: Int,
            private val dragonCounterpart: Property) : Property(value) {
        fun getDragonCounterpart() = dragonCounterpart
    }

    private val scaleThickness = Property(0)
    private val clawSharpness = Property(0)
    private val wingStrength = Property(0)
    private val fireBreath = Property(0)

    private val dragonProperties = listOf<Property>(
            scaleThickness,
            clawSharpness,
            wingStrength,
            fireBreath
    )

    private val knightProperties = listOf<KnightProperty>(
            KnightProperty(knight.attack, scaleThickness),
            KnightProperty(knight.armor, clawSharpness),
            KnightProperty(knight.agility, wingStrength),
            KnightProperty(knight.endurance, fireBreath)
    )

    fun get(): Dragon {
        fillProperties()
        if (!haveHealthyDragon()) {
            balanceDragonProperties()
        }
        return buildCurrentDragon()
    }

    private fun fillProperties() {
        val descendingKnightProperties = knightProperties.sortedByDescending { it.getValue() }
        descendingKnightProperties.forEachIndexed { index, knightProperty ->
            if (index == 0)
                knightProperty
                        .getDragonCounterpart()
                        .setValue(
                                minOf(knightProperty.getValue() + 2, Property.MAX))
            else
                knightProperty
                        .getDragonCounterpart()
                        .setValue(
                                maxOf(knightProperty.getValue() - 1, Property.MIN))
        }
    }

    private fun balanceDragonProperties() {
        if (havePointsLeft()) {
            fillPointsBetweenProperties()
        } else if (havePointsExceeded()) {
            takePointsFromProperties()
        }
        check(haveHealthyDragon())
    }

    private fun fillPointsBetweenProperties() {
        val mutableProperties = dragonProperties.filter { !it.isMax() }.toMutableList()

        var activeIndex = 0
        while (havePointsLeft() && mutableProperties.isNotEmpty()) {
            val property = mutableProperties[activeIndex]
            property.setValue(property.getValue() + 1)

            if (property.isMax()) mutableProperties.removeAt(activeIndex)

            if (activeIndex >= mutableProperties.lastIndex) activeIndex = 0
            else ++activeIndex
        }
    }

    private fun takePointsFromProperties() {
        val mutableProperties = dragonProperties.filter { !it.isMin() }.toMutableList()
        var activeIndex = 0
        while (havePointsExceeded() && mutableProperties.isNotEmpty()) {
            val property = mutableProperties[activeIndex]

            property.setValue(property.getValue() - 1)

            if (property.isMin()) mutableProperties.removeAt(activeIndex)

            if (activeIndex >= mutableProperties.lastIndex) activeIndex = 0
            else ++activeIndex
        }
    }

    private fun buildCurrentDragon(): Dragon {
        return Dragon(
                scaleThickness.getValue(),
                clawSharpness.getValue(),
                wingStrength.getValue(),
                fireBreath.getValue()
        )
    }

    private fun haveHealthyDragon() = pointsLeft() == 0

    private fun havePointsExceeded() = pointsLeft() < 0

    private fun havePointsLeft() = pointsLeft() > 0

    private fun pointsLeft() = 20 - pointsUsed()

    private fun pointsUsed() =
            scaleThickness.getValue() +
                    clawSharpness.getValue() +
                    wingStrength.getValue() +
                    fireBreath.getValue()
}