package com.seek.assignment.core.service

import com.seek.assignment.core.model.PluralKeyItem
import com.seek.assignment.core.model.StringKeyItem

actual class LanguageServiceImpl actual constructor() {
    actual fun getResourceString(
        resourceKey: StringKeyItem?,
        vararg params: String
    ): String {
        try {
            resourceKey?.let { stringKey ->
                Service.applicationContext?.let { context ->
                    val resources = context.resources
                    val resourceId =
                        resources.getIdentifier(stringKey.toString(), "string", context.packageName)
                    var value = resources.getString(resourceId)

                    for (i in params.indices) {
                        value = value.replace("{$i}", params[i])
                    }

                    return value
                }
            }
        } catch (e: Exception) {
            return "[$resourceKey]"
        }

        return ""
    }

    actual fun getResourcePlural(
        resourceKey: PluralKeyItem?,
        quantity: Int,
        vararg params: String
    ): String {
        try {
            resourceKey?.let { stringKey ->
                Service.applicationContext?.let { context ->
                    val resources = context.resources
                    val resourceId =
                        resources.getIdentifier(stringKey.toString(), "plurals", context.packageName)
                    var value = resources.getQuantityString(resourceId, quantity)

                    for (i in params.indices) {
                        value = value.replace("{$i}", params[i])
                    }

                    return value
                }
            }
        } catch (e: Exception) {
            return "[$resourceKey]"
        }

        return ""
    }


}
