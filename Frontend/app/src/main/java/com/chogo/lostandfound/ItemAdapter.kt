package com.chogo.lostandfound

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var items: List<Item> = emptyList()

    fun submitList(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvMeta: TextView = view.findViewById(R.id.tvMeta)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvLocation: TextView = view.findViewById(R.id.tvLocation)
        val tvContact: TextView = view.findViewById(R.id.tvContact)
        val tvClaimed: TextView = view.findViewById(R.id.tvClaimed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val color = ContextCompat.getColor(
            holder.itemView.context,
            if (item.type == ItemType.LOST) R.color.lost else R.color.found
        )
        holder.tvName.text = item.name
        holder.tvStatus.text = item.type.label.uppercase()
        holder.tvStatus.backgroundTintList = ColorStateList.valueOf(color)
        holder.tvMeta.text = "${item.category} • ${item.date}"
        holder.tvDescription.text = item.description
        holder.tvLocation.text = item.location
        holder.tvContact.text = item.contact
        holder.tvClaimed.visibility = if (item.claimed) View.VISIBLE else View.GONE
        holder.itemView.setOnClickListener { onItemClick(item) }
    }
}