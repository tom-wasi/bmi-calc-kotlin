package tomwas.pjwstk.bmi_calc_with_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tomwas.pjwstk.bmi_calc_with_kotlin.CalorieCalculator
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import tomwas.pjwstk.bmi_calc_with_kotlin.SharedViewModel
import java.util.Locale

class CalorieCalculatorFragment : Fragment() {

    private lateinit var weightInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var ageInput: EditText
    private lateinit var activitySpinner: Spinner
    private lateinit var resultTextView: TextView
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_calorie_calculator, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        weightInput = view.findViewById(R.id.weightInput)
        heightInput = view.findViewById(R.id.heightInput)
        ageInput = view.findViewById(R.id.ageInput)
        activitySpinner = view.findViewById(R.id.activitySpinner)
        resultTextView = view.findViewById(R.id.resultTextView)
        val calculateButton: Button = view.findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener { calculateCalories() }

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.activity_levels,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activitySpinner.adapter = adapter

        return view
    }

    private fun calculateCalories() {
        try {
            val weight = weightInput.text.toString().toDouble()
            val height = heightInput.text.toString().toDouble()
            val age = ageInput.text.toString().toInt()
            val activityLevel = activitySpinner.selectedItem.toString()

            val calories = CalorieCalculator.calculateCalories(weight, height, age, activityLevel)
            resultTextView.text = String.format(Locale.getDefault(),"Zapotrzebowanie: %.0f kcal", calories)
            viewModel.setCalories(calories)
        } catch (e: Exception) {
            resultTextView.text = "Błąd danych"
        }
    }
}