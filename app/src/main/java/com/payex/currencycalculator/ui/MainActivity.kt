package com.payex.currencycalculator.ui

import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.payex.currencycalculator.R
import com.payex.currencycalculator.components.AppComponent
import com.payex.currencycalculator.components.DaggerAppComponent
import com.payex.currencycalculator.service.CurrencyConverter
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.math.BigDecimal


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var amountTextInput: EditText
    lateinit var toCurrencySelector: Spinner
    lateinit var fromCurrencySelector: Spinner
    lateinit var feedbackText: TextView

    lateinit var applicationGraph: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        applicationGraph = DaggerAppComponent.builder().build()


        toCurrencySelector = findViewById(R.id.currency_to_selector)
        fromCurrencySelector = findViewById(R.id.currency_from_selector)
        amountTextInput = findViewById(R.id.amount_text_input)
        feedbackText = findViewById(R.id.feedback_text)

        ArrayAdapter.createFromResource(
            this,
            R.array.currency_name_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            toCurrencySelector.adapter = adapter
            toCurrencySelector.onItemSelectedListener = this
            fromCurrencySelector.adapter = adapter
            fromCurrencySelector.onItemSelectedListener = this
        }
    }

    // TODO: Everything in this function should be moved to a seperate function, and be called by the change input amount event as well
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {

        val amount: Double = amountTextInput.text.toString().toDoubleOrNull() ?: 0.0
        val fromCurr: String = fromCurrencySelector.selectedItem.toString()
        val toCurr: String = toCurrencySelector.selectedItem.toString()

        /*
        Apparently I don't know dagger well enough yet. Get the following error when I attempt to call the method through the AppComponent Dagger Graph
        error: Members injection methods may only return the injected type or void.
        public abstract com.payex.currencycalculator.model.CurrencyData mapToCurrencyData(@org.jetbrains.annotations.NotNull()
         */
        if (amount != null && fromCurr.isNotEmpty() && toCurr.isNotEmpty()) {
            val currencyConverter: CurrencyConverter = CurrencyConverter()
            val calculatedValue: BigDecimal = currencyConverter.convertCurrency(
                fromCurrency = fromCurr,
                toCurrency = toCurr,
                amount = BigDecimal.valueOf(amount)
            )

            println("Calculated value: $calculatedValue")
            feedbackText.text = calculatedValue.toString()

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}

