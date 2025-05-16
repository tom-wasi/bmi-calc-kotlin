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

class WHRCalculatorFragment : Fragment() {
    private lateinit var waistInput: EditText
    private lateinit var hipInput: EditText
    private lateinit var resultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val view = inflater.inflate(R.layout.fragment_whr_calculator, container, false)
        waistInput = view.findViewById(R.id.waistInput)
        hipInput = view.findViewById(R.id.hipInput)
        resultTextView = view.findViewById(R.id.resultTextView)

        val calculateButton = view.findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener { calculateWHR() }
        return view
    }

    private fun calculateWHR()
    {
        try {
            val waist = waistInput.text.toString().toDouble()
            val hip = hipInput.text.toString().toDouble()
            val whr = waist / hip

            val (status, riskDetails) = when {
                whr < 0.9 -> "Niskie ryzyko" to "Niskie ryzyko chorób sercowo-naczyniowych"
                whr < 1.0 -> "Średnie ryzyko" to "Umiarkowane ryzyko chorób serca"
                else -> "Wysokie ryzyko" to "Podwyższone ryzyko chorób serca i zespołu metabolicznego"
            }
            resultTextView.text = String.format(Locale.getDefault(), "Waist 2 Hip: %.2f\nStatus: %s\n%s", whr, status, riskDetails)
        } catch (exception: NumberFormatException)
        {
            resultTextView.text = "Wprowadź poprawne dane"

        }
    }
}