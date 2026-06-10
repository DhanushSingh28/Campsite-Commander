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
import android.content.Intent
import androidx.core.graphics.toColorInt
import androidx.core.view.WindowInsetsCompat

//the arrays gave me some difficulty
private val itemNames = arrayOf("Tent", "Marshmallows", "Flashlight")
private val categories = arrayOf("Shelter", "Food", "Safety")
private val quantities = arrayOf("1", "3", "2")
private val comments = arrayOf("4_person waterproof", "For S'mores(Megaa size)", "Check Batteries")

class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val changeScreenButton = findViewById<Button>(R.id.back_base)
        changeScreenButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnShowData = findViewById<Button>(R.id.btnShowData)
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutItems)

        btnShowData.setOnClickListener {
            tableLayout.removeViews(1, tableLayout.childCount - 1)

            for (i in itemNames.indices) {
                val row = TableRow(this)
                val rowParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                row.layoutParams = rowParams
                row.setPadding(8, 16, 8, 16)

                if (i % 2 == 0) {
                    row.setBackgroundColor("#F9F9F9".toColorInt())
                }

                row.addView(createTextView(itemNames[i]))
                row.addView(createTextView(categories[i]))
                row.addView(createTextView(quantities[i]))
                row.addView(createTextView(comments[i]))

                tableLayout.addView(row)
            }
        }
    }

    private fun createTextView(textValue: String): TextView {
        return TextView(this).apply {
            text = textValue
            setPadding(8, 8, 8, 8)
            gravity = Gravity.CENTER_VERTICAL
        }
    }
}
