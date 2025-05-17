package tomwas.pjwstk.bmi_calc_with_kotlin.fragments

import ShoppingListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import tomwas.pjwstk.bmi_calc_with_kotlin.Recipe
import tomwas.pjwstk.bmi_calc_with_kotlin.SharedViewModel
import tomwas.pjwstk.bmi_calc_with_kotlin.ShoppingItem

class RecipeFragment : Fragment() {

    private lateinit var recipeTitleTextView: TextView
    private lateinit var recipeStepsTextView: TextView
    private lateinit var shoppingListRecyclerView: RecyclerView

    private lateinit var allRecipes: List<Recipe>
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        recipeTitleTextView = view.findViewById(R.id.recipeTitleTextView)
        recipeStepsTextView = view.findViewById(R.id.recipeStepsTextView)
        shoppingListRecyclerView = view.findViewById(R.id.shoppingListRecyclerView)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        loadRecipesFromJson()
        sharedViewModel.calories.observe(viewLifecycleOwner) { calories ->
            updateRecipeForCalories(calories)
        }

        return view
    }

    private fun updateRecipeForCalories(calories: Double?) {
        val recommendedRecipes = if (calories != null) {
            filterRecipesByCalories(calories)
        } else {
            allRecipes
        }

        val firstRecipe = recommendedRecipes.firstOrNull()

        if (firstRecipe == null) {
            recipeTitleTextView.text = "No recipes available."
            recipeStepsTextView.text = ""
            shoppingListRecyclerView.adapter = null
        } else {
            recipeTitleTextView.text = firstRecipe.name
            recipeStepsTextView.text = firstRecipe.steps.toString()

            shoppingListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            val shoppingItems = firstRecipe.ingredients.map { ingredientName ->
                ShoppingItem(name = ingredientName, quantity = 1, unit = null, isChecked = false)
            }

            shoppingListRecyclerView.adapter = ShoppingListAdapter(shoppingItems)
        }
    }

    private fun loadRecipesFromJson() {
        val inputStream = requireContext().assets.open("recipes.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Recipe>>() {}.type
        allRecipes = Gson().fromJson(json, type)
    }

    private fun filterRecipesByCalories(calories: Double): List<Recipe> {
        val type = when {
            calories < 1800 -> "low"
            calories <= 2500 -> "medium"
            else -> "high"
        }
        return allRecipes.filter { it.type == type }
    }
}
