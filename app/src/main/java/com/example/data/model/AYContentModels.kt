package com.example.data.model

data class MinistryLevel(
    val id: String,
    val name: String,
    val ministryGroup: String, // "Adventurers", "Pathfinders", "Ambassadors", "Master Guide"
    val ageGroup: String,
    val colorHex: Long,
    val description: String,
    val requirements: List<RequirementItem>
)

data class RequirementItem(
    val id: String,
    val category: String, // "General", "Spiritual", "Service", "Fitness", "Nature", "Outdoor"
    val title: String,
    val details: String
)

data class HonorItem(
    val id: String,
    val name: String,
    val category: String, // "Nature", "Recreation", "Spiritual", "Health & Science", "Arts & Crafts", "Vocational"
    val skillLevel: Int, // 1, 2, 3
    val summary: String,
    val requirements: List<String>
)

data class AYSong(
    val id: String,
    val title: String,
    val group: String, // "AY Youth", "Pathfinders", "Adventurers"
    val lyrics: String
)

data class AYPledge(
    val id: String,
    val title: String,
    val category: String, // "AY", "Pathfinder", "Adventurer"
    val text: String,
    val explanation: String
)

data class KnotGuideItem(
    val id: String,
    val name: String,
    val category: String, // "Joining", "Loop", "Hitch", "Stopper"
    val description: String,
    val primaryUse: String,
    val steps: List<String>
)

data class DrillCommand(
    val id: String,
    val command: String,
    val response: String,
    val category: String, // "Stationary", "Marching", "Formation"
    val description: String
)

data class RemnantHubResource(
    val title: String,
    val url: String,
    val description: String,
    val category: String
)
