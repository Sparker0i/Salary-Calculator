package me.sparker0i.salarycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import me.sparker0i.salarycalculator.model.Category
import me.sparker0i.salarycalculator.recyclerview.CardAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var model: SalaryViewModel
    private lateinit var categoryObserver: Observer<List<Category>>

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<CardAdapter.ViewHolder>? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.component_recycler_view)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val fab: ExtendedFloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { showDialog() }

        adapter = CardAdapter()
        recyclerView.adapter = adapter

        model = ViewModelProviders.of(this).get(SalaryViewModel::class.java)

        categoryObserver = Observer {list ->

        }

        model.getComponents().observe(this , categoryObserver)
    }

    fun showDialog() {

    }
}
