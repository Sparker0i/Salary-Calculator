package me.sparker0i.salarycalculator.app

import android.app.Application
import me.sparker0i.salarycalculator.model.Category

class SalaryCalculator : Application() {
    lateinit var components: List<Category>
}