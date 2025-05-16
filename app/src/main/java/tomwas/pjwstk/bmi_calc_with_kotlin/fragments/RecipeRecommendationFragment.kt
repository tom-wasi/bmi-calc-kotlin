package tomwas.pjwstk.bmi_calc_with_kotlin.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import tomwas.pjwstk.bmi_calc_with_kotlin.Recipe
import tomwas.pjwstk.bmi_calc_with_kotlin.SharedViewModel
import tomwas.pjwstk.bmi_calc_with_kotlin.adapters.RecipeAdapter

class RecipeRecommendationFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var allRecipes: Map<String, List<Recipe>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_recipe_recommendation, container, false)

        recyclerView = view.findViewById(R.id.recipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        loadRecipesFromJson()

        viewModel.calories.observe(viewLifecycleOwner) { userCalories ->
            val recommended = getRecommendedRecipes(userCalories)

            recyclerView.adapter = RecipeAdapter(recommended) { selectedRecipe ->
                viewModel.setIngredients(selectedRecipe.ingredients)
                val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
                viewPager.currentItem = 5  // or the correct tab index for shopping list
            }
        }

        return view
    }

    private fun loadRecipesFromJson() {
        val inputStream = requireContext().assets.open("recipes.json")
        val json = inputStream.bufferedReader().use { it.readText() }

        val type = object : TypeToken<Map<String, List<Recipe>>>() {}.type
        allRecipes = Gson().fromJson(json, type)
    }

    private fun getRecommendedRecipes(calories: Double?): List<Recipe> {
        return when {
            calories == null || calories < 0 -> emptyList()
            calories < 1800 -> allRecipes["light"] ?: emptyList()
            calories <= 2500 -> allRecipes["balanced"] ?: emptyList()
            else -> allRecipes["high"] ?: emptyList()
        }
    }
}
