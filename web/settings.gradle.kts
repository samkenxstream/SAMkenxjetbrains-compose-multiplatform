pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { 
            url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") 
        }
        maven {
            url = uri("https://packages.jetbrains.team/maven/p/ui/dev")
        }
    }

    resolutionStrategy {
        eachPlugin {
            println("REQ ${requested.id.id}")
            if (requested.id.id == "org.jetbrains.compose") {
                println("[build] compose core version: ${extra["COMPOSE_CORE_VERSION"]}")
                useModule("org.jetbrains.compose:org.jetbrains.compose.gradle.plugin:${extra["COMPOSE_CORE_VERSION"]}")
            } else if (requested.id.id == "org.jetbrains.kotlin.multiplatform") {
                useModule("org.jetbrains.kotlin.multiplatform:org.jetbrains.kotlin.multiplatform.gradle.plugin:1.5.10")
            }
        }
    }
}

fun module(name: String, path: String) {
    include(name)
    project(name).projectDir = file(path)
}

module(":web-core", "$rootDir/core")
module(":web-widgets", "$rootDir/widgets")
module(":web-integration-core", "$rootDir/integration-core")
module(":web-integration-widgets", "$rootDir/integration-widgets")
