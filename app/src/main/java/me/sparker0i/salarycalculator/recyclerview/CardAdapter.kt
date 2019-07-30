package me.sparker0i.salarycalculator.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.sparker0i.salarycalculator.R
import me.sparker0i.salarycalculator.model.Category
import me.sparker0i.salarycalculator.model.Expenses
import me.sparker0i.salarycalculator.model.Salary
import me.sparker0i.salarycalculator.model.Type

class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private val components = mutableListOf<Category>()

    init {
        for (i in 1..10) {
            components.add(Salary("Basic" , 295368.0))
        }
        for (i in 1..10) {
            components.add(Expenses("Insurance" , 100000.0))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var componentName: TextView
        var amount: TextView
        var layout: RelativeLayout

        init {
            componentName = itemView.findViewById(R.id.category_card_name)
            amount = itemView.findViewById(R.id.category_card_amount)
            layout = itemView.findViewById(R.id.category_card_holder)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.category_card, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (components.get(position).type == Type.NEGATIVE)
            holder.layout.setBackgroundColor(Color.parseColor("#FF0000"))
        else if (components.get(position).type == Type.POSITIVE)
            holder.layout.setBackgroundColor(Color.parseColor("#239123"))

        holder.amount.text = components.get(position).value.toString()
        holder.componentName.text = components.get(position).name
    }

    override fun getItemCount(): Int {
        return components.size
    }
}