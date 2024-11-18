import com.build_logic.convention.utils.implementation
import com.build_logic.convention.utils.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("users.android.library.compose")
                apply("users.koin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                implementation(project(":core:ui"))
                implementation(project(":core:common"))

                implementation(library("koin.androidx.compose"))
                implementation(library("kotlinx-serialization-json"))
            }
        }
    }
}