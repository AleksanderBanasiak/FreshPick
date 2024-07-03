package com.freshPick.FreshPick.common

data class PageResponse<T>(
    val content: List<T> = emptyList(),
    val number: Int = 0,
    val size: Int = 0,
    val totalElements: Long = 0,
    val totalPages: Int = 0,
    val first: Boolean = false,
    val last: Boolean = false
)