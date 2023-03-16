package com.seek.assignment.core.service

import com.seek.assignment.core.model.PluralKeyItem
import com.seek.assignment.core.model.StringKeyItem

expect class LanguageServiceImpl() {
    fun getResourceString(
        resourceKey: StringKeyItem?,
        vararg params: String = emptyArray()
    ): String

    fun getResourcePlural(
        resourceKey: PluralKeyItem?,
        quantity: Int,
        vararg params: String = emptyArray()
    ): String
}

class LanguageService {
    private val impl = LanguageServiceImpl()
    fun getResourceString(resourceKey: StringKeyItem?) = impl.getResourceString(resourceKey)

    fun getResourceString(resourceKey: StringKeyItem?, vararg params: String) =
        impl.getResourceString(resourceKey, *params)

    fun getResourcePlural(resourceKey: PluralKeyItem?, quantity: Int) =
        impl.getResourcePlural(resourceKey, quantity)

    fun getResourcePlural(resourceKey: PluralKeyItem?, quantity: Int, vararg params: String) =
        impl.getResourcePlural(resourceKey, quantity, *params)
}

