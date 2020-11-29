package com.example.projectmobilerestaurant.core.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object DecimalFormatUtils {

    private const val PATTERN_SIMPLE_DECIMAL = "##0.0"

    fun getDfSimple(): DecimalFormat {
        val dfs = getDfs().apply {
            decimalSeparator = '.'
        }
        return DecimalFormat(PATTERN_SIMPLE_DECIMAL, dfs)
    }

    private fun getDfs(): DecimalFormatSymbols = DecimalFormatSymbols()

}