package ua.mrrobot1413.empower.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Divider
import ua.mrrobot1413.empower.R
import ua.mrrobot1413.empower.data.DataProvider
import ua.mrrobot1413.empower.data.model.Beneficiary
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    private val dataProvider by lazy { DataProvider(applicationContext) }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(dataProvider)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        val scrollView = ScrollView(this)
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        viewModel.getBeneficiaries()

        viewModel.list.observe(this) {
            println(it)
            updateUI(it, linearLayout)
        }

        scrollView.addView(linearLayout)
        setContentView(scrollView)
    }

    private fun updateUI(beneficiaries: List<Beneficiary>, linearLayout: LinearLayout) {
        linearLayout.removeAllViews()
        beneficiaries.forEach { beneficiary ->
            val textView = TextView(this).apply {
                text =
                    "${beneficiary.firstName} ${beneficiary.lastName}, ${beneficiary.beneType}, ${
                        getDesignation(beneficiary.designationCode)
                    }"
                textSize = 18f
                setPadding(40, 40, 40, 40)
                setOnClickListener {
                   createAlertDialog(beneficiary)
                }
            }
            linearLayout.addView(textView)
        }
    }

    private fun createAlertDialog(beneficiary: Beneficiary) {
        val formattedDate = formatDate(beneficiary.dateOfBirth)
        val alert = AlertDialog.Builder(this@MainActivity)
            .setTitle("Beneficiary details")
            .setMessage(
                "Name: ${beneficiary.firstName} ${beneficiary.middleName ?: ""} ${beneficiary.lastName}\n" +
                        "Type: ${beneficiary.beneType}\n" +
                        "Designation: ${getDesignation(beneficiary.designationCode)}\n" +
                        "SSN: ${beneficiary.socialSecurityNumber}\n" +
                        "Date of Birth: ${formattedDate}\n" +
                        "Phone: ${beneficiary.phoneNumber}\n" +
                        "Address: ${beneficiary.beneficiaryAddress.firstLineMailing} ${beneficiary.beneficiaryAddress.scndLineMailing ?: ""}\n" +
                        "${beneficiary.beneficiaryAddress.city}, ${beneficiary.beneficiaryAddress.stateCode} ${beneficiary.beneficiaryAddress.zipCode}, ${beneficiary.beneficiaryAddress.country}"
            ).create()

        alert.show()
    }

    private fun formatDate(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("MMddyyyy", Locale.US)
            val outputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val date = inputFormat.parse(dateString)
            if(date != null) outputFormat.format(date) else "Unknown"
        } catch (e: Exception) {
            dateString
        }
    }

    private fun getDesignation(code: String): String {
        return when (code) {
            "P" -> "Primary"
            "C" -> "Contingent"
            else -> "Unknown"
        }
    }
}
