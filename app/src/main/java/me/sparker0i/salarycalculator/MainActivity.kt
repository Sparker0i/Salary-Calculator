package me.sparker0i.salarycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.sparker0i.salarycalculator.model.Category

class MainActivity : AppCompatActivity() {

    private lateinit var model: SalaryViewModel
    private lateinit var categoryObserver: Observer<List<Category>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this).get(SalaryViewModel::class.java)

        categoryObserver = Observer {list ->

        }

        model.getComponents().observe(this , categoryObserver)
    }
}
