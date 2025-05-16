package tomwas.pjwstk.bmi_calc_with_kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _calories = MutableLiveData<Double>()
    val calories: LiveData<Double> get() = _calories
    val selectedIngredients = MutableLiveData<List<String>>()

    fun setIngredients(ingredients: List<String>) {
        selectedIngredients.value = ingredients
    }

    fun setCalories(value: Double) {
        _calories.value = value
    }
}