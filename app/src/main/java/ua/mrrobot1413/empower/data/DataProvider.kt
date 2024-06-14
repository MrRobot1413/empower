package ua.mrrobot1413.empower.data

import android.content.Context
import org.json.JSONArray
import ua.mrrobot1413.empower.R
import ua.mrrobot1413.empower.data.model.Beneficiary
import ua.mrrobot1413.empower.data.model.BeneficiaryAddress

class DataProvider(private val context: Context) {

    fun getBeneficiaries(): List<Beneficiary> {
        val jsonString = context.resources.openRawResource(R.raw.beneficiaries).bufferedReader().use { it.readText() }
        return parseBeneficiaries(jsonString)
    }

    private fun parseBeneficiaries(jsonString: String): List<Beneficiary> {
        val beneficiaries = mutableListOf<Beneficiary>()
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val addressObject = jsonObject.getJSONObject("beneficiaryAddress")
            val address = BeneficiaryAddress(
                firstLineMailing = addressObject.getString("firstLineMailing"),
                scndLineMailing = addressObject.optString("scndLineMailing", null),
                city = addressObject.getString("city"),
                zipCode = addressObject.getString("zipCode"),
                stateCode = addressObject.getString("stateCode"),
                country = addressObject.getString("country")
            )
            val beneficiary = Beneficiary(
                firstName = jsonObject.getString("firstName"),
                middleName = jsonObject.optString("middleName", null),
                lastName = jsonObject.getString("lastName"),
                designationCode = jsonObject.getString("designationCode"),
                beneType = jsonObject.getString("beneType"),
                socialSecurityNumber = jsonObject.getString("socialSecurityNumber"),
                dateOfBirth = jsonObject.getString("dateOfBirth"),
                phoneNumber = jsonObject.getString("phoneNumber"),
                beneficiaryAddress = address
            )
            beneficiaries.add(beneficiary)
        }

        return beneficiaries
    }
}