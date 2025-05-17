package tomwas.pjwstk.bmi_calc_with_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import tomwas.pjwstk.bmi_calc_with_kotlin.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.recipeTitle)
        val calories: TextView = itemView.findViewById(R.id.recipeCalories)
        val ingredients: TextView = itemView.findViewById(R.id.recipeIngredients)
        val steps: TextView = itemView.findViewById(R.id.recipeSteps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.name
        holder.calories.text = "${recipe.calories} kcal"
        holder.ingredients.text = "Składniki: ${recipe.ingredients.joinToString(", ")}"
        holder.steps.text = "Kroki: ${recipe.steps.joinToString("\n")}"
    }

    override fun getItemCount(): Int = recipes.size
}
