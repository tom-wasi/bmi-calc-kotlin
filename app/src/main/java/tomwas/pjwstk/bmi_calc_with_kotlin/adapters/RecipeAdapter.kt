package tomwas.pjwstk.bmi_calc_with_kotlin.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import tomwas.pjwstk.bmi_calc_with_kotlin.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.recipeTitle)
        val calories = itemView.findViewById<TextView>(R.id.recipeCalories)
        val ingredients = itemView.findViewById<TextView>(R.id.recipeIngredients)
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
        holder.ingredients.text = recipe.ingredients.joinToString(", ")

        holder.itemView.setOnClickListener {
            Log.d("RecipeAdapter", "Clicked: ${recipes[position].name}")
            onRecipeClick(recipes[position])
        }
    }

    override fun getItemCount(): Int = recipes.size
}