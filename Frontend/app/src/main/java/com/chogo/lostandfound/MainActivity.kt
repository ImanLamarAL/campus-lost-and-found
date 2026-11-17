package com.chogo.lostandfound

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.chogo.lostandfound.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.applySystemBarPadding()

        ItemRepository.init(applicationContext)

        binding.btnReportLost.setOnClickListener { openReport(ItemType.LOST) }
        binding.btnReportFound.setOnClickListener { openReport(ItemType.FOUND) }
        binding.btnViewItems.setOnClickListener {
            startActivity(Intent(this, ViewItemsActivity::class.java))
        }
    }

    private fun openReport(type: ItemType) {
        val intent = Intent(this, ReportItemActivity::class.java)
        intent.putExtra(ReportItemActivity.EXTRA_TYPE, type.name)
        startActivity(intent)
    }
}