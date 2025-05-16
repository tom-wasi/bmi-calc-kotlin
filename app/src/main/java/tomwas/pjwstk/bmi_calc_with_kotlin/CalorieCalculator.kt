package tomwas.pjwstk.bmi_calc_with_kotlin

object CalorieCalculator {

    fun calculateBMR(weight: Double, height: Double, age: Int): Double {
        return 10 * weight + 6.25 * height - 5 * age + 5
    }

    fun getActivityMultiplier(level: String): Double {
        return when (level) {
            "Lekka aktywność" -> 1.375
            "Średnia aktywność" -> 1.55
            "Duża aktywność" -> 1.725
            "Bardzo duża aktywność" -> 1.9
            else -> 1.2
        }
    }

    fun calculateCalories(weight: Double, height: Double, age: Int, activityLevel: String): Double {
        val bmr = calculateBMR(weight, height, age)
        val multiplier = getActivityMultiplier(activityLevel)
        return bmr * multiplier
    }
}