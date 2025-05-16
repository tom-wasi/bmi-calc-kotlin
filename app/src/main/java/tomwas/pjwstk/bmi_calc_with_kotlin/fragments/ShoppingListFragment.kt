package tomwas.pjwstk.bmi_calc_with_kotlin.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import tomwas.pjwstk.bmi_calc_with_kotlin.SharedViewModel
import tomwas.pjwstk.bmi_calc_with_kotlin.ShoppingItem
import tomwas.pjwstk.bmi_calc_with_kotlin.adapters.ShoppingListAdapter

class ShoppingListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShoppingListAdapter
    private val shoppingItems = mutableListOf<ShoppingItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shopping_list, container, false)
        recyclerView = view.findViewById(R.id.shoppingRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ShoppingListAdapter(shoppingItems) { item, isChecked ->
            item.isChecked = isChecked
        }
        recyclerView.adapter = adapter

        observeIngredientsFromViewModel()

        return view
    }

    private fun observeIngredientsFromViewModel() {
        val viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel.selectedIngredients.observe(viewLifecycleOwner) { ingredients ->
            shoppingItems.clear()
            shoppingItems.addAll(
                ingredients.map { name ->
                    ShoppingItem(name = name, quantity = 1, unit = null, isChecked = false)
                }
            )
            adapter.notifyDataSetChanged()
        }
    }
}