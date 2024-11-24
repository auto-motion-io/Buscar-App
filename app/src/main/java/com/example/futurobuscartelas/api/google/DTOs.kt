package com.example.futurobuscartelas.api.google

data class GeocodeResponse(
    val results: List<Result>
)

data class Result(
    val geometry: Geometry
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class DistanceMatrixResponse(
    val rows: List<Row>
)

data class Row(
    val elements: List<Element>
)

data class Element(
    val distance: Distance,
    val duration: Duration
)

data class Distance(
    val text: String,  // Ex: "12.5 km"
    val value: Int     // Ex: 12500 (em metros)
)

data class Duration(
    val text: String,  // Ex: "15 mins"
    val value: Int     // Ex: 900 (em segundos)
)

