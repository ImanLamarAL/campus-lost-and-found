package com.chogo.lostandfound

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Single place where all reported items live.
 * Items are kept in memory and saved to SharedPreferences (simple local storage)
 * so they are still there after the app is closed.
 */
object ItemRepository {

    private const val PREFS_NAME = "lost_found_prefs"
    private const val KEY_ITEMS = "items"

    private val items = mutableListOf<Item>()
    private var prefs: SharedPreferences? = null

    /** Safe to call many times; only loads once. */
    fun init(context: Context) {
        if (prefs != null) return
        prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        load()
    }

    fun add(item: Item) {
        items.add(0, item) // newest first
        save()
    }

    fun markClaimed(id: Long) {
        items.find { it.id == id }?.claimed = true
        save()
    }

    /** Search by name, then filter by category and status. Pass Categories.ALL to skip a filter. */
    fun search(query: String, category: String, status: String): List<Item> =
        items.filter { item ->
            (query.isBlank() || item.name.contains(query, ignoreCase = true)) &&
                    (category == Categories.ALL || item.category == category) &&
                    (status == Categories.ALL || item.type.label == status)
        }

    private fun save() {
        val array = JSONArray()
        items.forEach { i ->
            array.put(JSONObject().apply {
                put("id", i.id)
                put("name", i.name)
                put("description", i.description)
                put("location", i.location)
                put("date", i.date)
                put("contact", i.contact)
                put("category", i.category)
                put("type", i.type.name)
                put("claimed", i.claimed)
            })
        }
        prefs?.edit()?.putString(KEY_ITEMS, array.toString())?.apply()
    }

    private fun load() {
        items.clear()
        val json = prefs?.getString(KEY_ITEMS, null) ?: return
        try {
            val array = JSONArray(json)
            for (n in 0 until array.length()) {
                val o = array.getJSONObject(n)
                items.add(
                    Item(
                        id = o.getLong("id"),
                        name = o.getString("name"),
                        description = o.getString("description"),
                        location = o.getString("location"),
                        date = o.getString("date"),
                        contact = o.getString("contact"),
                        category = o.getString("category"),
                        type = ItemType.fromName(o.getString("type")),
                        claimed = o.optBoolean("claimed", false)
                    )
                )
            }
        } catch (e: JSONException) {
            items.clear()
        }
    }
}