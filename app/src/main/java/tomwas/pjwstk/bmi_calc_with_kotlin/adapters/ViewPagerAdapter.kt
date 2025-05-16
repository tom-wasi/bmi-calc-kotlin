package tomwas.pjwstk.bmi_calc_with_kotlin.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tomwas.pjwstk.bmi_calc_with_kotlin.fragments.BMICalculatorFragment
import tomwas.pjwstk.bmi_calc_with_kotlin.fragments.BMIChartFragment
import tomwas.pjwstk.bmi_calc_with_kotlin.fragments.CalorieCalculatorFragment
import tomwas.pjwstk.bmi_calc_with_kotlin.fragments.RecipeFragment
import tomwas.pjwstk.bmi_calc_with_kotlin.fragments.ShoppingListFragment
import tomwas.pjwstk.bmi_calc_with_kotlin.fragments.WHRCalculatorFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BMICalculatorFragment()
            1 -> WHRCalculatorFragment()
            2 -> CalorieCalculatorFragment()
            3 -> RecipeFragment()
            4 -> ShoppingListFragment()
            5 -> BMIChartFragment()
            else -> BMICalculatorFragment()
        }
    }
}