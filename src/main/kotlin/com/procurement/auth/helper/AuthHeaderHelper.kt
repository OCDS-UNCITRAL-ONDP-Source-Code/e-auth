package com.procurement.auth.helper

import com.procurement.auth.exception.security.InvalidAuthHeaderTypeException
import com.procurement.auth.exception.security.NoSuchAuthHeaderException
import com.procurement.auth.model.AUTHORIZATION_PREFIX_BASIC
import com.procurement.auth.model.AUTHORIZATION_PREFIX_BEARER
import com.procurement.auth.model.HEADER_NAME_AUTHORIZATION
import com.procurement.auth.model.token.AuthTokenType
import javax.servlet.http.HttpServletRequest

fun HttpServletRequest.getBasicToken(): String {
    val header = this.getHeader(HEADER_NAME_AUTHORIZATION)
        ?: throw NoSuchAuthHeaderException(this, AuthTokenType.BASIC)
    if (!header.startsWith(AUTHORIZATION_PREFIX_BASIC)) {
        throw InvalidAuthHeaderTypeException(this, AuthTokenType.BASIC)
    }
    return header.substring(AUTHORIZATION_PREFIX_BASIC.length)
}

fun HttpServletRequest.getBearerToken(): String {
    val header = this.getHeader(HEADER_NAME_AUTHORIZATION)
        ?: throw NoSuchAuthHeaderException(this, AuthTokenType.BEARER)
    if (!header.startsWith(AUTHORIZATION_PREFIX_BEARER)) {
        throw InvalidAuthHeaderTypeException(this, AuthTokenType.BEARER)
    }
    return header.substring(AUTHORIZATION_PREFIX_BEARER.length)
}




