import org.jetbrains.dokka.gradle.DokkaTask
import kotlin.text.set

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.dokka)
}

android {
    namespace = "com.rossyn.onboarding.design"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rossyn.onboarding.design"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    dokkaPlugin(libs.android.documentation.plugin)
}

tasks.withType<DokkaTask>().configureEach {
    moduleName.set("Onboarding (${project.android.defaultConfig.versionName})")
    outputDirectory.set(file("../docs/design-1"))

    dokkaSourceSets {
        configureEach {
            displayName.set("Onboarding")

            sourceRoots.from(file("src/main/java/${project.android.defaultConfig.applicationId}"))

            perPackageOption {
                matchingRegex.set("android.*")
                suppress.set(true)
            }

            perPackageOption {
                matchingRegex.set("androidx.*")
                suppress.set(true)
            }

            documentedVisibilities.set(
                setOf(
                    org.jetbrains.dokka.DokkaConfiguration.Visibility.PUBLIC,
                    org.jetbrains.dokka.DokkaConfiguration.Visibility.PRIVATE
                )
            )

            suppressGeneratedFiles.set(true)
            skipEmptyPackages.set(true)
        }
    }
}