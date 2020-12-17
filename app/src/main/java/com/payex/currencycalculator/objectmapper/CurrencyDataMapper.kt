package com.payex.currencycalculator.objectmapper

import com.payex.currencycalculator.model.CurrencyData
import com.payex.currencycalculator.model.CurrencyInformation
import dagger.Module
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

/**
 * My original attempts where using either Jackson or converter-simplexml for deserializing the xml fetched from the endpoint.
 * Running out of time so this functionality will be mocked for now
 */
@Module
class CurrencyDataMapper @Inject constructor() {

    fun mapToCurrencyData(CurrencyInfoResponse: String): CurrencyData {
        val currencyInformationList = listOf(
            CurrencyInformation("NOK", BigDecimal.valueOf(1.0)),
            CurrencyInformation("AUD", BigDecimal.valueOf(6.5422)),
            CurrencyInformation("BGN", BigDecimal.valueOf(536.94)),
            CurrencyInformation("BRL", BigDecimal.valueOf(1.6901)),
            CurrencyInformation("CAD", BigDecimal.valueOf(6.7551)),
            CurrencyInformation("CHF", BigDecimal.valueOf(970.47)),
            CurrencyInformation("CNY", BigDecimal.valueOf(131.26)),
            CurrencyInformation("CZK", BigDecimal.valueOf(40.076)),
            CurrencyInformation("DKK", BigDecimal.valueOf(141.15)),
            CurrencyInformation("EUR", BigDecimal.valueOf(10.5015)),
            CurrencyInformation("GBP", BigDecimal.valueOf(11.6619)),
            CurrencyInformation("HKD", BigDecimal.valueOf(1.1061)),
            CurrencyInformation("HRK", BigDecimal.valueOf(139.43)),
            CurrencyInformation("HUF", BigDecimal.valueOf(2.9559)),
            CurrencyInformation("I44", BigDecimal.valueOf(111.24)),
            CurrencyInformation("IDR", BigDecimal.valueOf(0.060724)),
            CurrencyInformation("ILS", BigDecimal.valueOf(2.6396)),
            CurrencyInformation("INR", BigDecimal.valueOf(11.653)),
            CurrencyInformation("JPY", BigDecimal.valueOf(8.322)),
            CurrencyInformation("KRW", BigDecimal.valueOf(0.7846)),
            CurrencyInformation("MXN", BigDecimal.valueOf(43.32)),
            CurrencyInformation("MYR", BigDecimal.valueOf(2.125)),
            CurrencyInformation("NZD", BigDecimal.valueOf(6.1437)),
            CurrencyInformation("PHP", BigDecimal.valueOf(17.86)),
            CurrencyInformation("PKR", BigDecimal.valueOf(5.358)),
            CurrencyInformation("PLN", BigDecimal.valueOf(2.364)),
            CurrencyInformation("RON", BigDecimal.valueOf(215.66)),
            CurrencyInformation("RUB", BigDecimal.valueOf(11.759)),
            CurrencyInformation("SEK", BigDecimal.valueOf(103.63)),
            CurrencyInformation("SGD", BigDecimal.valueOf(6.4716)),
            CurrencyInformation("THB", BigDecimal.valueOf(28.729)),
            CurrencyInformation("TRY", BigDecimal.valueOf(110.74)),
            CurrencyInformation("TWD", BigDecimal.valueOf(30.45)),
            CurrencyInformation("TWI", BigDecimal.valueOf(123.32)),
            CurrencyInformation("USD", BigDecimal.valueOf(8.5755)),
            CurrencyInformation("XDR", BigDecimal.valueOf(12.40271)),
            CurrencyInformation("ZAR", BigDecimal.valueOf(0.584)),
            CurrencyInformation("BYN", BigDecimal.valueOf(3.4147)),
            CurrencyInformation("BDT", BigDecimal.valueOf(10.11)),
            CurrencyInformation("MMK", BigDecimal.valueOf(0.6319)),
            CurrencyInformation("ISK", BigDecimal.valueOf(6.76))
        )
        return CurrencyData(
            timePeriod = Calendar.getInstance().time,
            currency = currencyInformationList
        )
    }
}