package com.example.mirefri

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.Locale

class ControladorIdioma(private val ct: Context) {
    fun updateResources(code: String) {
        val locale = Locale(code)
        Locale.setDefault(locale)

        val resources: Resources = ct.resources
        val configuration: Configuration = resources.configuration
        configuration.locale = locale

        //@Suppress("DEPRECATION")
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}
