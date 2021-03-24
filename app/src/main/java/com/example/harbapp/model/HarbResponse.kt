package com.example.harbapp.model

data class HarbResponse(
    val id: String,
    val name: String,
    val image: String,
    val lat: Float,
    val lon: Float,
    val isPriceHidden: Boolean,
    val isFree: Boolean,
    val canBook: Boolean,
    val cashOnlyBookings: Boolean,
    val notActivated: Boolean,
    val translations: List<Translations>,
    val acceptBankPayments: Boolean,
    val acceptEpayPayments: Boolean,
    val acceptGoCardlessPayments: Boolean,
    val acceptBankPaymentsIban: String,
    val bookOneDayOnly: Boolean,)

data class Translations(val id: Int, val name: String, val content: String, val locale: String)
