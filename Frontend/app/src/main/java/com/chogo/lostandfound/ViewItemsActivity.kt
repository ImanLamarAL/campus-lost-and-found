package com.chogo.lostandfound

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

class ViewItemsActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter

    private val toolbar by lazy { findViewById<MaterialToolbar>(R.id.toolbar) }
    private val etSearch by lazy { findViewById<TextInputEditText>(R.id.etSearch) }
    private val spCategory by lazy { findViewById<Spinner>(R.id.spCategory) }
    private val spStatus by lazy { findViewById<Spinner>(R.id.spStatus) }
    private val tvCount by lazy { findViewById<TextView>(R.id.tvCount) }
    private val tvEmpty by lazy { findViewById<TextView>(R.id.tvEmpty) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_items)
        applySystemBarPadding()
        ItemRepository.init(applicationContext)

        toolbar.setNavigationOnClickListener { finish() }

        // 1. RecyclerView must be ready first
        adapter = ItemAdapter { item -> showDetails(item) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // 2. Filter spinners
        setupSpinner(spCategory, listOf(Categories.ALL) + Categories.list)
        setupSpinner(spStatus, listOf(Categories.ALL, "Lost", "Found"))

        // 3. Search box
        etSearch.doOnTextChanged { _, _, _, _ -> applyFilters() }
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
        val query = etSearch.text.toString().trim()
        val category = spCategory.selectedItem?.toString() ?: Categories.ALL
        val status = spStatus.selectedItem?.toString() ?: Categories.ALL
        val results = ItemRepository.search(query, category, status)
        adapter.submitList(results)
        tvCount.text = "${results.size} item(s)"
        tvEmpty.visibility = if (results.isEmpty()) View.VISIBLE else View.GONE
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
                val message = "Item marked as claimed / returned"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}