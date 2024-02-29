plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization").version("1.9.21")
}

kotlin {
    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    //Generating BuildConfig for multiplatform
    val buildConfigGenerator by tasks.registering(Sync::class) {
        val packageName = "secrets"
        val secretProperties = readPropertiesFromFile("secrets.properties")
        from(
            resources.text.fromString(
                """
        |package $packageName
        |
        |object BuildConfig {
        |  const val API_KEY = "${secretProperties.getPropertyValue("API_KEY")}"
        |}
        |
      """.trimMargin()
            )
        )
        {
            rename { "BuildConfig.kt" } // set the file name
            into(packageName) // change the directory to match the package
        }
        into(layout.buildDirectory.dir("generated-src/kotlin/"))
    }

    val ktorVersion = "2.3.7"
    val coroutinesVersion = "1.7.3"
    val voyagerVersion = "1.0.0"
    val koinVersion = "3.5.3"
    val napierVersion = "2.7.1"

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir(
                // convert the task to a file-provider
                buildConfigGenerator.map { it.destinationDir }
            )
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("media.kamel:kamel-image:0.9.1")

                // Navigator
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")

                implementation("io.insert-koin:koin-core:$koinVersion")

                implementation("io.github.aakira:napier:$napierVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")

                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
            }

        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

/**
 * Property File name example secrets.properties
 * two local file can be created
 * secrets.properties (can be pushed to Git) as template to show the keys,
 * secrets.local.properties with actual values (should be added to .gitignore)
 *
 * Properties file content should be like this.
 * key=value
 * key2=value2
 */
fun readPropertiesFromFile(fileName: String): Map<String, String> {
    val parts = fileName.split('.')
    val localPropertyFileName = if (parts.size >= 2) {
        val nameWithoutExtension = parts.dropLast(1).joinToString(".")
        val extension = parts.last()
        "$nameWithoutExtension.local.$extension"
    } else {
        fileName
    }
    val isLocalFileExists= File(project.rootDir,localPropertyFileName).exists()
    val fileContent = try {
        File(project.rootDir,if (isLocalFileExists) localPropertyFileName else fileName).readText()
    } catch (e: Exception) {
        e.printStackTrace()
        return emptyMap()
    }

    val properties = mutableMapOf<String, String>()

    fileContent.lines().forEach { line ->
        val keyValuePair = line.split('=')
        if (keyValuePair.size == 2) {
            properties[keyValuePair[0].trim()] = keyValuePair[1]
        }
    }
    return properties
}

/**
 * If System.env value exists it will be returned value which can be useful for CI/CD pipeline
 */
fun Map<String, String>.getPropertyValue(key: String): String? {
    val envValue = System.getenv(key)
    if (envValue.isNullOrEmpty().not()) return envValue
    return this.getOrDefault(key, null)
}
