package com.chogo.lostandfound

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chogo.lostandfound.databinding.ItemRowBinding

class ItemAdapter(
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var items: List<Item> = emptyList()

    fun submitList(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val b = holder.binding
        val color = ContextCompat.getColor(
            b.root.context,
            if (item.type == ItemType.LOST) R.color.lost else R.color.found
        )

        b.tvName.text = item.name
        b.tvStatus.text = item.type.label.uppercase()
        b.tvStatus.backgroundTintList = ColorStateList.valueOf(color)
        b.tvMeta.text = "${item.category}  •  ${item.date}"
        b.tvDescription.text = item.description
        b.tvLocation.text = item.location
        b.tvContact.text = item.contact
        b.tvClaimed.visibility = if (item.claimed) View.VISIBLE else View.GONE

        b.root.setOnClickListener { onItemClick(item) }
    }
}