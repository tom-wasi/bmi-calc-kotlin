package tomwas.pjwstk.bmi_calc_with_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import java.util.Locale

class BMICalculatorFragment : Fragment() {

    private lateinit var weightInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var resultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_calculator, container, false)

        weightInput = view.findViewById(R.id.weightInput)
        heightInput = view.findViewById(R.id.heightInput)
        resultTextView = view.findViewById(R.id.resultTextView)
        val calculateButton = view.findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener { calculateBMI() }

        return view
    }

    private fun calculateBMI() {
        try {
            val weight = weightInput.text.toString().toDouble()
            val height = heightInput.text.toString().toDouble() / 100
            val bmi = weight / (height * height)

            val (status, riskDetails) = when {
                bmi < 18.5 -> "Niedowaga" to "Ryzyko niedożywienia, osłabienia odporności i osteoporozy"
                bmi < 24.9 -> "Waga optymalna" to "Prawidłowa masa ciała – niskie ryzyko problemów zdrowotnych"
                bmi < 29.9 -> "Nadwaga" to "Zwiększone ryzyko chorób serca, cukrzycy typu 2 i nadciśnienia"
                else -> "Otyłość" to "Wysokie ryzyko chorób sercowo-naczyniowych, cukrzycy, problemów ze stawami"
            }

            resultTextView.text = String.format(
                Locale.getDefault(),
                "BMI: %.2f\nStatus: %s\n%s",
                bmi, status, riskDetails
            )
        } catch (e: NumberFormatException) {
            resultTextView.text = "Wprowadź poprawne dane."
        }
    }
}