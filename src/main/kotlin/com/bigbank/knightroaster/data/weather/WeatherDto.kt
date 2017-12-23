package com.bigbank.knightroaster.data.weather

import javax.xml.bind.annotation.*

@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.NONE)
class WeatherDto(
        @XmlElement(name = "coords") val coordinates: CoordinatesDto = CoordinatesDto(0.0, 0.0, 0.0),
        @XmlElement(name = "code") val code: String = "",
        @XmlElement(name = "message") val message: String = "",
        @XmlElement(name = "varX-Rating") val rating: Int = 0
) {
    class CoordinatesDto(
            @XmlElement(name = "x") val x: Double = 0.0,
            @XmlElement(name = "y") val y: Double = 0.0,
            @XmlElement(name = "z") val z: Double = 0.0
    )
}

