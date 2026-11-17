package com.chogo.lostandfound

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.chogo.lostandfound.databinding.ActivityViewItemsBinding

class ViewItemsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewItemsBinding
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityViewItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.applySystemBarPadding()

        ItemRepository.init(applicationContext)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // 1. RecyclerView must be ready first
        adapter = ItemAdapter { item -> showDetails(item) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // 2. Filter spinners
        setupSpinner(binding.spCategory, listOf(Categories.ALL) + Categories.list)
        setupSpinner(binding.spStatus, listOf(Categories.ALL, "Lost", "Found"))

        // 3. Search box
        binding.etSearch.doOnTextChanged { _, _, _, _ -> applyFilters() }
    }

    override fun onResume() {
        super.onResume()
        applyFilters() // refresh when coming back from the report screen
    }

    private fun setupSpinner(spinner: Spinner, options: List<String>) {
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>?, v: View?, pos: Int, id: Long) {
                applyFilters()
            }

            override fun onNothingSelected(p: AdapterView<*>?) {}
        }
    }

    private fun applyFilters() {
        val query = binding.etSearch.text.toString().trim()
        val category = binding.spCategory.selectedItem?.toString() ?: Categories.ALL
        val status = binding.spStatus.selectedItem?.toString() ?: Categories.ALL

        val results = ItemRepository.search(query, category, status)
        adapter.submitList(results)
        binding.tvCount.text = "${results.size} item(s)"
        binding.tvEmpty.visibility = if (results.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun showDetails(item: Item) {
        val message = listOf(
            "Status: ${item.type.label}",
            "Category: ${item.category}",
            "Date: ${item.date}",
            "Location: ${item.location}",
            "Description: ${item.description}",
            "Contact: ${item.contact}",
            if (item.claimed) "\nThis item has been claimed / returned." else ""
        ).joinToString("\n")

        val builder = AlertDialog.Builder(this)
            .setTitle(item.name)
            .setMessage(message)
            .setNegativeButton("Close", null)
            .setNeutralButton("Copy contact") { _, _ ->
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("Contact", item.contact))
                Toast.makeText(this, "Contact copied", Toast.LENGTH_SHORT).show()
            }

        if (!item.claimed) {
            builder.setPositiveButton("Mark as Claimed/Returned") { _, _ -> confirmClaim(item) }
        }
        builder.show()
    }

    private fun confirmClaim(item: Item) {
        AlertDialog.Builder(this)
            .setTitle("Confirm")
            .setMessage("Mark \"${item.name}\" as claimed / returned?")
            .setPositiveButton("Yes") { _, _ ->
                ItemRepository.markClaimed(item.id)
                applyFilters()
                Toast.makeText(this, "Item marked as claimed / returned", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}