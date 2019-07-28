package me.sparker0i.salarycalculator.model

enum class Type(val value: Int) {
    NEGATIVE(-1) , POSITIVE(1)
}

interface Category {
    val name: String
    val value: Double
    val type: Type
}