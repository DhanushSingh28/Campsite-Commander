package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.content.Intent;
import kotlin.reflect.KClass

//ST10505975 Dhanush Singh
data class Item(
    val name: String,
    val category: String,
    val quantity: Int,
    val comments: String
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val changeScreenButton = findViewById<Button>(R.id.detail_page)
        changeScreenButton.setOnClickListener {
            val intent = Intent(this,DetailedViewScreen::class.java)

            startActivity(intent)
        }

                val tableLayout = findViewById<TableLayout>(R.id.tableLayoutItems)
                val btnCalculate = findViewById<Button>(R.id.add_gear)
                val tvTotalOutput = findViewById<TextView>(R.id.tvTotalOutput)
//the loops were faster to implement here
                val itemList = listOf(
                    Item("Tent", "Shelter", 1, "4-person waterproof"),
                    Item("Marshmallows", "Food", 3, "For S'mores(Mega size)"),
                    Item("Flashlight", "Safety", 2, "Check Batteries(AA)")
                )

                for (item in itemList) {
                    val row = TableRow(this)
                    row.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )

                    val tvName = TextView(this).apply {
                        text = item.name
                        setPadding(16, 16, 16, 16)
                        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.0f)
                    }

                    val tvQty = TextView(this).apply {
                        text = item.quantity.toString()
                        setPadding(16, 16, 16, 16)
                        layoutParams = TableRow.LayoutParams(40, TableRow.LayoutParams.WRAP_CONTENT)
                    }

                    row.addView(tvName)
                    row.addView(tvQty)
                    tableLayout.addView(row)
                }

                btnCalculate.setOnClickListener {
                    var totalQuantity = 0

                    for (item in itemList) {
                        totalQuantity += item.quantity
                    }

                    tvTotalOutput.text = "Total Quantity: $\$totalQuantity"
        }
    }
}