package com.chogo.lostandfound

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.chogo.lostandfound.databinding.ActivityReportItemBinding
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar
import java.util.Locale

class ReportItemActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityReportItemBinding
    private lateinit var type: ItemType

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityReportItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.applySystemBarPadding()

        ItemRepository.init(applicationContext)
        type = ItemType.fromName(intent.getStringExtra(EXTRA_TYPE))

        setupHeader()
        setupCategorySpinner()

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.etDate.setOnClickListener { showDatePicker() }
        binding.btnSubmit.setOnClickListener {
            if (validate()) confirmSubmit()
        }
    }

    private fun setupHeader() {
        val isLost = type == ItemType.LOST
        binding.toolbar.title = if (isLost) "Report Lost Item" else "Report Found Item"
        binding.tvTypeBanner.text =
            if (isLost) "You are reporting a LOST item" else "You are reporting a FOUND item"
        binding.tvTypeBanner.setBackgroundColor(
            ContextCompat.getColor(this, if (isLost) R.color.lost else R.color.found)
        )
        binding.tilLocation.hint =
            if (isLost) "Where did you lose it?" else "Where did you find it?"
        binding.tilDate.hint = if (isLost) "Date lost" else "Date found"
    }

    private fun setupCategorySpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Categories.list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCategory.adapter = adapter
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        val dialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                binding.etDate.setText(
                    String.format(Locale.getDefault(), "%02d/%02d/%04d", day, month + 1, year)
                )
                binding.tilDate.error = null
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.maxDate = System.currentTimeMillis() // no future dates
        dialog.show()
    }

    private fun checkField(layout: TextInputLayout, value: String, message: String): Boolean {
        layout.error = if (value.isBlank()) message else null
        return value.isNotBlank()
    }

    private fun validate(): Boolean {
        val nameOk = checkField(binding.tilName, binding.etName.text.toString(), "Item name is required")
        val descOk = checkField(binding.tilDescription, binding.etDescription.text.toString(), "Description is required")
        val locOk = checkField(binding.tilLocation, binding.etLocation.text.toString(), "Location is required")
        val dateOk = checkField(binding.tilDate, binding.etDate.text.toString(), "Please pick a date")
        val contactOk = checkField(binding.tilContact, binding.etContact.text.toString(), "Contact information is required")
        return nameOk && descOk && locOk && dateOk && contactOk
    }

    private fun confirmSubmit() {
        AlertDialog.Builder(this)
            .setTitle("Submit report?")
            .setMessage("Do you want to submit this ${type.label.lowercase()} item report?")
            .setPositiveButton("Submit") { _, _ -> saveItem() }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun saveItem() {
        val item = Item(
            id = System.currentTimeMillis(),
            name = binding.etName.text.toString().trim(),
            description = binding.etDescription.text.toString().trim(),
            location = binding.etLocation.text.toString().trim(),
            date = binding.etDate.text.toString().trim(),
            contact = binding.etContact.text.toString().trim(),
            category = binding.spCategory.selectedItem.toString(),
            type = type
        )
        ItemRepository.add(item)
        Toast.makeText(this, "${type.label} item reported successfully!", Toast.LENGTH_LONG).show()
        finish()
    }
}