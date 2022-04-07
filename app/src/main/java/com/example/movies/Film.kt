package com.example.movies

data class Film(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)