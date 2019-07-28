package me.sparker0i.salarycalculator.model

data class Expenses(override val name: String,
                    override val value: Double,
                    override val type: Type = Type.NEGATIVE) : Category