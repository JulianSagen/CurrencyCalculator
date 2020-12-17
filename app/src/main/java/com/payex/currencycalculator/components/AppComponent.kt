package com.payex.currencycalculator.components

import com.payex.currencycalculator.network.NetworkModule
import com.payex.currencycalculator.objectmapper.CurrencyDataMapper
import dagger.Component

@Component(modules = [NetworkModule::class, CurrencyDataMapper::class, CurrencyDataMapper::class])
interface AppComponent{
//    fun convertCurrency(fromCurrency: String, toCurrency: String, amount: BigDecimal): BigDecimal

}

