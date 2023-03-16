package com.seek.assignment.core.model

import kotlinx.serialization.Serializable

//REMARK: This is a mix of how the error response work between V1 and V2 api endpoints. When we only use V2 endpoints, clean this up.
@Serializable
data class ErrorResponse(
    var message: String, // V1 and V2
    val code: Int? = 0, // V1
    val detailedErrorCode: Int? = null, // V1
    val detailedErrorMessage: String? = null, // V1
    val oneAccountError: String? = null, // V1
    val error: String? = "", // V2
    val detail: String? = "", // V2
)

@Serializable
data class DeltaError(
    val code: Int? = null,
    val reason: String? = null,
    val error: String? = null
)