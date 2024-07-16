package com.yomicepa.domain.models

enum class ServiceType(val title: String) {
    EXPRESS_SERVICE("Express Service"),
    FULL_SERVICE("Full Service");

    companion object {

        fun fromTitle(title: String) =
            values().associateBy(ServiceType::title)[title] ?: EXPRESS_SERVICE
    }
}