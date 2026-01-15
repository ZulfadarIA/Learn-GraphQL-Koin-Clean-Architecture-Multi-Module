package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.mapper

import com.example.rocketreserver.LaunchListQuery
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch

fun LaunchListQuery.Launch.toDomainLaunchList(): Launch =
    Launch(
        id = id,
        missionName = mission?.name.orEmpty(),
        site = site.orEmpty(),
        missionPatchUrl = mission?.missionPatch
    )