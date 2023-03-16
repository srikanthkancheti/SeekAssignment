package com.seek.assignment.common.assets

import com.seek.assignment.core.service.Service

actual class AssetReader actual constructor(){
    actual fun getJsonString(filename: String): String {
        return try {
            val test = Service.applicationContext?.assets?.open("$filename.json")
                ?.bufferedReader()
                .use { it?.readText() ?: "" }
            test
        } catch (exception: Exception) {
            ""
        }
    }
}