package com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.mapper

import com.example.rocketreserver.LaunchListQuery
import com.zulfadar.core.model.Launch

fun LaunchListQuery.Launch.toDomainLaunchList(): Launch =
    Launch(
        id = id,
        missionName = mission?.name.orEmpty(),
        site = site.orEmpty(),
        missionPatchUrl = mission?.missionPatch
    )