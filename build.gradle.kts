plugins {
  // this is necessary to avoid the plugins to be loaded multiple times
  // in each subproject's classloader

  alias(libs.plugins.kotlin.multiplatform) apply false
  alias(libs.plugins.kotlinNativeCocoapods) apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.compose) apply false
  alias(libs.plugins.google.services) apply false
  alias(libs.plugins.firebase.crashlytics) apply false
  id("org.jlleitschuh.gradle.ktlint")
}

subprojects {
  apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent
  // Optionally configure plugin
  configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    version.set("1.0.1")
  }
}
