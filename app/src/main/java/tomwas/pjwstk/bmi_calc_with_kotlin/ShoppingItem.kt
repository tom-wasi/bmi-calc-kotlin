package tomwas.pjwstk.bmi_calc_with_kotlin

data class ShoppingItem(
    val name: String,
    val quantity: Int = 1,
    val unit: String? = null,
    var isChecked: Boolean = false
)