package me.sparker0i.salarycalculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.sparker0i.salarycalculator.model.Category


class SalaryViewModel : ViewModel() {

    val TAG = SalaryViewModel::class.java.simpleName

    var components: MutableLiveData<MutableList<Category>> = MutableLiveData()

    init {
        components.value = mutableListOf()
    }

    fun getComponents(): LiveData<MutableList<Category>> {
        return components
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    fun addComponent(component: Category) {
        println(component.name)
        components.value?.add(component)
        components.notifyObserver()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "on cleared called")
    }
}
