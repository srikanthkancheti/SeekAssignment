package com.seek.assignment.core.model

import com.seek.assignment.core.service.LanguageService

class PluralKeyItem(private val key: StringKeyInterface.Key) {
    fun format(languageService: LanguageService, quantity: Int, vararg params: String) =
        languageService.getResourcePlural(this, quantity, *params)

    override fun toString(): String {
        return key.key
    }
}