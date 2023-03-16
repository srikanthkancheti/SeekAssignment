package com.seek.assignment.core.service

import com.seek.assignment.core.model.PluralKeyItem
import com.seek.assignment.core.model.StringKeyItem
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.localizedStringWithFormat
import platform.Foundation.stringWithFormat

actual class LanguageServiceImpl actual constructor() {
    actual fun getResourceString(
        resourceKey: StringKeyItem?,
        vararg params: String
    ): String {
        resourceKey?.let {
           return getResourceFromBundle(resourceKey.toString(), null, *params)
        }

        return ""
    }

    actual fun getResourcePlural(
        resourceKey: PluralKeyItem?,
        quantity: Int,
        vararg params: String
    ): String {
        resourceKey?.let {
            return getResourceFromBundle(it.toString(), quantity, *params)
        }

        return ""
    }

    private fun getResourceFromBundle(
        resourceKey: String,
        quantity: Int?,
        vararg params: String
    ): String {
        try {
            var bundleString = NSBundle.mainBundle.localizedStringForKey(resourceKey, resourceKey, null)

            if (bundleString == resourceKey) {
                NSBundle.mainBundle.pathForResource("Base", ofType = "lproj")?.let { path ->
                    val bundle = NSBundle(path = path)
                    bundleString =
                        bundle.localizedStringForKey(bundleString, "[$bundleString]", null)
                }
            }

            var string = quantity?.let {
                NSString.localizedStringWithFormat(bundleString, it.toLong())
            } ?: NSString.stringWithFormat(bundleString)

            for (i in params.indices) {
                string = string.replace("{$i}", params[i])
            }

            return string
        } catch (e: Exception) {
            return "[$resourceKey]"
        }
    }
}
