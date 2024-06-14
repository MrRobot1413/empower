package ua.mrrobot1413.empower.data.model

data class Beneficiary(
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val designationCode: String,
    val beneType: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val beneficiaryAddress: BeneficiaryAddress
)

data class BeneficiaryAddress(
    val firstLineMailing: String,
    val scndLineMailing: String?,
    val city: String,
    val zipCode: String,
    val stateCode: String,
    val country: String
)