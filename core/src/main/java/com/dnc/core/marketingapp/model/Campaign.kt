package com.dnc.core.marketingapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Campaign(
    @SerializedName("price")
    val price: Int,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("minListings")
    val minListings: Int?,
    @SerializedName("maxListings")
    val maxListings: Int?,
    @SerializedName("optimizationNo")
    val optimizationNo: Int?,
    @SerializedName("options")
    val listOfOptions: ArrayList<String>?
) : Serializable{

    fun getPrice(): String
        = if(currency != null) "$price $currency" else "$price EUR"

    fun getListings(): String
        = when(minListings){
            null, 0 -> "0"
            maxListings -> minListings.toString()
            else -> "$minListings - $maxListings"
        }

    fun getCampaignOptions(): String
        = listOfOptions?.joinToString(separator = ", ") ?: ""
}