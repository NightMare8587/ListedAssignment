package com.example.listedassignment.Models

data class Data(
    val overall_url_chart: OverallUrlChart,
    val recent_links: List<LinksModel>,
    val top_links: List<LinksModel>
)