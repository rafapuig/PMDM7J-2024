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

rootProject.name = "PMDM7J"
include(":sumarapp")
include(":saludoapp")
include(":spinnerdemo")
include(":calculadora")
include(":statechange")
include(":permissiondemo")
include(":implicitintent")
include(":mysensors")
