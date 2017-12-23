package com.bigbank.knightroaster.domain.dragon

import com.bigbank.knightroaster.domain.battle.Knight

class DragonKeeper {
    private class DragonBuilder(knight: Knight) {

        private open class Property(private var value: Int) {
            protected val MIN = 0
            protected val MAX = 10

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
                private val dragonsCounterpart: Property) : Property(value) {
            fun getDragonsCounterpart() = dragonsCounterpart
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

        private val knightProperties = listOf(
                KnightProperty(knight.attack, scaleThickness),
                KnightProperty(knight.armor, clawSharpness),
                KnightProperty(knight.agility, wingStrength),
                KnightProperty(knight.endurance, fireBreath)
        )

        private fun haveHealthyDragon() = pointsLeft() == 0

        private fun havePointsExceeded() = pointsLeft() < 0

        private fun havePointsLeft() = pointsLeft() > 0

        private fun pointsLeft() = 20 - pointsUsed()

        private fun pointsUsed() =
                scaleThickness.getValue() + clawSharpness.getValue() + wingStrength.getValue() + fireBreath.getValue()


        fun build(): Dragon {
            fillProperties()
            if (haveHealthyDragon()) {
                return buildCurrentDragon()
            } else if (havePointsLeft()) {
                fillPointsBetweenProperties()
            } else if (havePointsExceeded()) {
                takePointsFromProperties()
            }

            assertCurrentDragonIsHealthy()
            return buildCurrentDragon()
        }

        private fun fillProperties() {
            val sortedKnightProperties = knightProperties.sortedByDescending { it.getValue() }

            val first = sortedKnightProperties[0]
            first.getDragonsCounterpart().setValue(minOf(first.getValue() + 2, 10))

            val second = sortedKnightProperties[1]
            val third = sortedKnightProperties[2]
            val fourth = sortedKnightProperties[3]
            second.getDragonsCounterpart().setValue(maxOf(second.getValue() - 1, 0))
            third.getDragonsCounterpart().setValue(maxOf(third.getValue() - 1, 0))
            fourth.getDragonsCounterpart().setValue(maxOf(fourth.getValue() - 1, 0))
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

        private fun assertCurrentDragonIsHealthy() {
            check(pointsLeft() == 0)
        }

        private fun buildCurrentDragon(): Dragon {
            return Dragon(
                    scaleThickness.getValue(),
                    clawSharpness.getValue(),
                    wingStrength.getValue(),
                    fireBreath.getValue()
            )
        }

    }


    fun getForKnight(knight: Knight): Dragon {
        val builder = DragonBuilder(knight)
        return builder.build()
    }

    fun getZenDragon() = Dragon(5, 5, 5, 5)

    fun getFloodDragon() = Dragon(5, 10, 5, 0)
}