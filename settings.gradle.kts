rootProject.name = "MyApplication"

include(":androidApp")
include(":shared")
// include(":desktopApp")

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }

  plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
    id("org.jlleitschuh.gradle.ktlint").version("12.1.0")
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    mavenLocal()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}
