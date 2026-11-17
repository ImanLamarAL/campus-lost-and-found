package com.chogo.lostandfound

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar
import java.util.Locale

class ReportItemActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var type: ItemType

    private val toolbar by lazy { findViewById<MaterialToolbar>(R.id.toolbar) }
    private val tvTypeBanner by lazy { findViewById<TextView>(R.id.tvTypeBanner) }
    private val tilName by lazy { findViewById<TextInputLayout>(R.id.tilName) }
    private val tilDescription by lazy { findViewById<TextInputLayout>(R.id.tilDescription) }
    private val tilLocation by lazy { findViewById<TextInputLayout>(R.id.tilLocation) }
    private val tilDate by lazy { findViewById<TextInputLayout>(R.id.tilDate) }
    private val tilContact by lazy { findViewById<TextInputLayout>(R.id.tilContact) }
    private val etName by lazy { findViewById<TextInputEditText>(R.id.etName) }
    private val etDescription by lazy { findViewById<TextInputEditText>(R.id.etDescription) }
    private val etLocation by lazy { findViewById<TextInputEditText>(R.id.etLocation) }
    private val etDate by lazy { findViewById<TextInputEditText>(R.id.etDate) }
    private val etContact by lazy { findViewById<TextInputEditText>(R.id.etContact) }
    private val spCategory by lazy { findViewById<Spinner>(R.id.spCategory) }
    private val btnSubmit by lazy { findViewById<Button>(R.id.btnSubmit) }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_item)
        applySystemBarPadding()
        ItemRepository.init(applicationContext)

        type = ItemType.fromName(intent.getStringExtra(EXTRA_TYPE))

        setupHeader()
        setupCategorySpinner()

        toolbar.setNavigationOnClickListener { finish() }
        etDate.setOnClickListener { showDatePicker() }
        btnSubmit.setOnClickListener {
            if (validate()) confirmSubmit()
        }
    }

    private fun setupHeader() {
        val isLost = type == ItemType.LOST
        toolbar.title = if (isLost) "Report Lost Item" else "Report Found Item"
        tvTypeBanner.text =
            if (isLost) "You are reporting a LOST item" else "You are reporting a FOUND item"
        tvTypeBanner.setBackgroundColor(
            ContextCompat.getColor(this, if (isLost) R.color.lost else R.color.found)
        )
        tilLocation.hint = if (isLost) "Where did you lose it?" else "Where did you find it?"
        tilDate.hint = if (isLost) "Date lost" else "Date found"
    }

    private fun setupCategorySpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Categories.list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCategory.adapter = adapter
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        val dialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                etDate.setText(
                    String.format(Locale.getDefault(), "%02d/%02d/%04d", day, month + 1, year)
                )
                tilDate.error = null
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
        val nameOk = checkField(
            tilName, etName.text.toString(), "Item name is required"
        )
        val descOk = checkField(
            tilDescription, etDescription.text.toString(), "Description is required"
        )
        val locOk = checkField(
            tilLocation, etLocation.text.toString(), "Location is required"
        )
        val dateOk = checkField(
            tilDate, etDate.text.toString(), "Please pick a date"
        )
        val contactOk = checkField(
            tilContact, etContact.text.toString(), "Contact information is required"
        )
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
            name = etName.text.toString().trim(),
            description = etDescription.text.toString().trim(),
            location = etLocation.text.toString().trim(),
            date = etDate.text.toString().trim(),
            contact = etContact.text.toString().trim(),
            category = spCategory.selectedItem.toString(),
            type = type
        )
        ItemRepository.add(item)
        val message = "${type.label} item reported successfully!"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }
}