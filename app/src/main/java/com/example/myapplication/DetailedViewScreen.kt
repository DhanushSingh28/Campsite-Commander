package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.view.Gravity
import android.graphics.Color
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat

private val itemNames = arrayOf("Tent", "Marshmallows", "Flashlight")
private val categories = arrayOf("Shelter", "Food","Safety")
private val quantities = arrayOf("1", "3", "2")
private val comments = arrayOf("4_person waterproof", "For S'mores(Megaa size), "Check Batteries")

class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

            val btnShowData = findViewById<Button>(R.id.btnShowData)
            val tableLayout = findViewById<TableLayout>(R.id.tableLayoutItems)

            // 2. Set button activation
            btnShowData.setOnClickListener {

                // Clear previous data to prevent duplicates
                tableLayout.removeViews(1, tableLayout.childCount - 1)

                // 3. Loop through arrays and populate the graph
                for (i in itemNames.indices) {
                    val row = TableRow(this)
                    val rowParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                    row.layoutParams = rowParams
                    row.setPadding(8, 16, 8, 16)

                    // Optional: Alternating row colors
                    if (i % 2 == 0) {
                        row.setBackgroundColor(Color.parseColor("#F9F9F9"))
                    }

                    // Add Item Name
                    row.addView(createTextView(itemNames[i]))
                    // Add Category
                    row.addView(createTextView(categories[i]))
                    // Add Quantity
                    row.addView(createTextView(quantities[i]))
                    // Add Comments
                    row.addView(createTextView(comments[i]))

                    // Add row to table
                    tableLayout.addView(row)
                }
            }

            // Helper function to format text for the table cells
            private fun createTextView(textValue: String): TextView {
                return TextView(this).apply {
                    text = textValue
                    setPadding(8, 8, 8, 8)
                    gravity = Gravity.CENTER_VERTICAL
                }
            }
        }
    }