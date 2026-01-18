pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LearnGraphQLwithCleanArchitectureAndKoin"
include(":app")
include(":core-domain")
include(":core-data")
include(":core-network")
include(":core-storage")
include(":feature-launch")
include(":feature-login")
include(":feature-launchdetail")
include(":core-ui")
