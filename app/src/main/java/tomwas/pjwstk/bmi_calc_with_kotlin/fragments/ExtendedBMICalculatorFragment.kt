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
import tomwas.pjwstk.bmi_calc_with_kotlin.R

class ExtendedBMICalculatorFragment : Fragment() {

    private lateinit var weightInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var ageInput: EditText
    private lateinit var activitySpinner: Spinner
    private lateinit var resultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_extended_bmi_calculator, container, false)

        weightInput = view.findViewById(R.id.weightInput)
        heightInput = view.findViewById(R.id.heightInput)
        ageInput = view.findViewById(R.id.ageInput)
        activitySpinner = view.findViewById(R.id.activitySpinner)
        resultTextView = view.findViewById(R.id.resultTextView)

        val calculateButton = view.findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener { calculateExtendedBMI() }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.activity_levels, // You’ll need to define this in strings.xml
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            activitySpinner.adapter = adapter
        }

        return view
    }

    private fun calculateExtendedBMI() {
        try {
            val weight = weightInput.text.toString().toDouble()
            val height = heightInput.text.toString().toDouble() / 100
            val bmi = weight / (height * height)

            val activityLevel = activitySpinner.selectedItem.toString()
            val age = ageInput.text.toString().toInt()

            val result = "BMI: %.2f\nWiek: %d\nAktywność: %s".format(bmi, age, activityLevel)
            resultTextView.text = result
        } catch (e: Exception) {
            resultTextView.text = "Wprowadź poprawne dane"
        }
    }
}