package com.zulfadar.core.model

data class LaunchDetail(
    val id: String,
    val missionName: String,
    val rocketName: String,
    val site: String,
    val missionPatchUrl: String?,
    val isBooked: Boolean
)


