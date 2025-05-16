package tomwas.pjwstk.bmi_calc_with_kotlin.adapters

import android.view.*
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tomwas.pjwstk.bmi_calc_with_kotlin.R
import tomwas.pjwstk.bmi_calc_with_kotlin.ShoppingItem

class ShoppingListAdapter(
    private var items: List<ShoppingItem>,
    private val onItemCheckedChange: (ShoppingItem, Boolean) -> Unit
) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val quantity: TextView = itemView.findViewById(R.id.quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name
        holder.quantity.text = if (item.unit != null) "${item.quantity} ${item.unit}" else "${item.quantity}"
        holder.checkbox.isChecked = item.isChecked

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            onItemCheckedChange(item, isChecked)
        }
    }

    fun updateList(newItems: List<ShoppingItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}