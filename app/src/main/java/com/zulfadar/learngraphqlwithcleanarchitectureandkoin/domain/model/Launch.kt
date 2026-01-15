package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model

data class Launch(
    val id: String,
    val missionName: String,
    val site: String,
    val missionPatchUrl: String?
)
