package com.chogo.lostandfound

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applySystemBarPadding()
        ItemRepository.init(applicationContext)

        findViewById<Button>(R.id.btnReportLost).setOnClickListener { openReport(ItemType.LOST) }
        findViewById<Button>(R.id.btnReportFound).setOnClickListener { openReport(ItemType.FOUND) }
        findViewById<Button>(R.id.btnViewItems).setOnClickListener {
            startActivity(Intent(this, ViewItemsActivity::class.java))
        }
    }

    private fun openReport(type: ItemType) {
        val intent = Intent(this, ReportItemActivity::class.java)
        intent.putExtra(ReportItemActivity.EXTRA_TYPE, type.name)
        startActivity(intent)
    }
}