package com.payex.currencycalculator.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import java.math.BigDecimal
import java.util.*




@JsonRootName("xml")
data class CurrencyData(
    @set:JsonProperty("Header")
    var timePeriod: Date?,
    var currency: List<CurrencyInformation> = ArrayList()
)

data class CurrencyInformation(
    @set:JsonProperty("QUOTE_CUR")
    var shortName: String,
    @set:JsonProperty("OBS_VALUE")
    var obsValue: BigDecimal
)