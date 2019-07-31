package me.sparker0i.salarycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import me.sparker0i.salarycalculator.model.Category
import me.sparker0i.salarycalculator.recyclerview.CardAdapter
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.Spinner
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import me.sparker0i.salarycalculator.model.Expense
import me.sparker0i.salarycalculator.model.Income


class MainActivity : AppCompatActivity() {

    private lateinit var model: SalaryViewModel
    private lateinit var categoryObserver: Observer<MutableList<Category>>

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: CardAdapter? = null
    private lateinit var recyclerView: RecyclerView

    var s = arrayOf("Income", "Expense")

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
            println(list.size)
            adapter!!.setData(list)
        }

        model.getComponents().observe(this , categoryObserver)
    }

    fun showDialog() {
        val inflater = layoutInflater
        val alertLayout = inflater.inflate(R.layout.layout_component_add_popup , null)

        val adp = ArrayAdapter(this, android.R.layout.simple_spinner_item, s)

        val tvName = alertLayout.findViewById<TextView>(R.id.component_name)
        val tvValue = alertLayout.findViewById<TextView>(R.id.component_value)
        val sp = alertLayout.findViewById<Spinner>(R.id.spinner_category)
        sp.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        sp.adapter = adp

        val builder = AlertDialog.Builder(this)
        builder.setView(alertLayout)

        builder.setPositiveButton("OK") { dialogInterface, i ->
            val item = sp.selectedItem.toString()
            val name = tvName.text.toString()
            val value = tvValue.text.toString().toDouble()
            println(item)
            Log.i("Before Change" , model.components.value!!.size.toString())
            if (item == "Expense")
                model.addComponent(Expense(name , value))
            else if (item == "Income")
                model.addComponent(Income(name , value))
            Log.i("After Change" , model.components.value!!.size.toString())
        }
        builder.setNegativeButton("CANCEL") { dialogInterface, i -> dialogInterface.cancel() }
        builder.create().show()
    }
}
