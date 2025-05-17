package tomwas.pjwstk.bmi_calc_with_kotlin

data class Recipe(
    val name: String,
    val calories: Int,
    val type: String,
    val ingredients: List<String>,
    val steps: List<String>
)