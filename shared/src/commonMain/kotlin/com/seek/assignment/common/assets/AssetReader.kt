package com.seek.assignment.common.assets

expect class AssetReader() {
    fun getJsonString(filename: String): String
}