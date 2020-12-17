package com.payex.currencycalculator.service

import com.payex.currencycalculator.model.CurrencyData
import com.payex.currencycalculator.network.NetworkModule
import com.payex.currencycalculator.objectmapper.CurrencyDataMapper
import dagger.Module
import dagger.Provides
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import javax.inject.Inject

@Module
class CurrencyConverter @Inject constructor() {

    // Ideally this should be initialized asynchronously a single time, and stored in permanent application state.
    // With a check which only updates it if the date has changed
    private var currencyData: CurrencyData? = null


    // @Inject lateinit
    private var networkModule: NetworkModule = NetworkModule()
    // @Inject lateinit
    private var currencyDataMapper: CurrencyDataMapper = CurrencyDataMapper()

    @Provides
    fun convertCurrency(fromCurrency: String, toCurrency: String, amount: BigDecimal): BigDecimal {
        if (currencyData == null
            || currencyData!!.timePeriod != null
            && currencyData!!.timePeriod!! == Calendar.getInstance().time
        ){
            fetchCurrencyData()
        }

        val valueCurrFrom = fetchPriceOfCurr(fromCurrency)
        val valueCurrTo = fetchPriceOfCurr(toCurrency)

        return calculate(amount, valueCurrFrom, valueCurrTo)
    }

    private fun fetchCurrencyData(){
        val response = networkModule.fetchCurrencyValues()
        this.currencyData = currencyDataMapper.mapToCurrencyData(response)
        println("")
    }

    private fun fetchPriceOfCurr(name: String) : BigDecimal{
        // Todo: Needs some more null safety here
        return this.currencyData!!.currency.filter { currency -> currency.shortName.equals(name) }.first().obsValue
    }

    fun calculate(amount: BigDecimal, valueCurrFrom: BigDecimal, valueCurrTo: BigDecimal): BigDecimal{
        val valueInNok = amount.multiply(valueCurrFrom)
        return valueInNok.divide(valueCurrTo, RoundingMode.HALF_UP)
    }
}
