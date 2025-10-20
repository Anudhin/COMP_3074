package com.Anuhdin_101516423.assignment1paycalc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.max


class MainActivity : AppCompatActivity() {

    private lateinit var etHours: EditText
    private lateinit var etRate: EditText
    private lateinit var etTax: EditText
    private lateinit var tvPay: TextView
    private lateinit var tvOvertime: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvTax: TextView
    private lateinit var btnCalculate: Button
    private lateinit var btnAbout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // <-- must match the XML you just made

        // Views
        etHours = findViewById(R.id.etHours)
        etRate = findViewById(R.id.etRate)
        etTax = findViewById(R.id.etTax)
        tvPay = findViewById(R.id.tvPay)
        tvOvertime = findViewById(R.id.tvOvertime)
        tvTotal = findViewById(R.id.tvTotal)
        tvTax = findViewById(R.id.tvTax)
        btnCalculate = findViewById(R.id.btnCalculate)
        btnAbout = findViewById(R.id.btnAbout)


        btnCalculate.setOnClickListener { calculateAndShow() }


        btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }

    private fun calculateAndShow() {
        val hours = etHours.text.toString().toDoubleOrNull()
        val rate = etRate.text.toString().toDoubleOrNull()
        var taxRate = etTax.text.toString().toDoubleOrNull()

        if (hours == null || rate == null || taxRate == null) {
            Toast.makeText(this, "Please enter valid numbers for all fields.", Toast.LENGTH_SHORT).show()
            return
        }


        if (taxRate > 1.0) taxRate /= 100.0

        val baseHours = minOf(hours, 40.0)
        val overtimeHours = max(0.0, hours - 40.0)

        val pay = baseHours * rate
        val overtimePay = overtimeHours * rate * 1.5
        val totalPay = pay + overtimePay
        val tax = pay * taxRate

        tvPay.text = "Pay: $${"%.2f".format(pay)}"
        tvOvertime.text = "Overtime Pay: $${"%.2f".format(overtimePay)}"
        tvTotal.text = "Total Pay: $${"%.2f".format(totalPay)}"
        tvTax.text = "Tax: $${"%.2f".format(tax)}"
    }
}
