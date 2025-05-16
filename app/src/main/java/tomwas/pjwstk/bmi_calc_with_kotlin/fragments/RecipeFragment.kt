package tomwas.pjwstk.bmi_calc_with_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import tomwas.pjwstk.bmi_calc_with_kotlin.Recipe
import tomwas.pjwstk.bmi_calc_with_kotlin.SharedViewModel

class RecipeFragment : Fragment() {

    private lateinit var recipeTextView: TextView
    private lateinit var viewModel: SharedViewModel
    private lateinit var allRecipes: List<Recipe>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        recipeTextView = view.findViewById(R.id.recipeTextView)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        loadRecipesFromJson()

        viewModel.calories.observe(viewLifecycleOwner) { calories ->
            val filteredRecipes = filterRecipesByCalories(calories)
            displayRecipes(filteredRecipes)
        }

        return view
    }

    private fun loadRecipesFromJson() {
        val inputStream = requireContext().assets.open("recipes.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Recipe>>() {}.type
        allRecipes = Gson().fromJson(json, type)
    }

    private fun filterRecipesByCalories(calories: Double?): List<Recipe> {
        if (calories == null || calories < 0) return emptyList()

        val type = when {
            calories < 1800 -> "low"
            calories <= 2500 -> "medium"
            else -> "high"
        }

        return allRecipes.filter { it.type == type }
    }

    private fun displayRecipes(recipes: List<Recipe>) {
        if (recipes.isEmpty()) {
            recipeTextView.text = "Najpierw oblicz swoje dzienne zapotrzebowanie kaloryczne"
            return
        }

        val builder = StringBuilder()
        recipes.forEachIndexed { index, recipe ->
            builder.append("${index + 1}. ${recipe.name} – ${recipe.calories} kcal\n")
            builder.append("Składniki: ${recipe.ingredients.joinToString(", ")}\n\n")
        }

        recipeTextView.text = builder.toString()
    }
}