package com.seek.assignment.core.model

import com.seek.assignment.core.service.LanguageService

data class StringKeyItem(private val key: StringKeyInterface.Key) {
    fun format(languageService: LanguageService, vararg params: String) =
        languageService.getResourceString(this, *params)

    override fun toString(): String {
        return key.key
    }
}