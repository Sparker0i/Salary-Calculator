package me.sparker0i.salarycalculator.model

data class Salary(override val name: String,
                  override val value: Double,
                  override val type: Type = Type.POSITIVE) : Category