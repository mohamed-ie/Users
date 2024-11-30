import com.android.build.gradle.LibraryExtension
import com.build_logic.convention.configureKotlinAndroid
import com.build_logic.convention.configureKotlinAndroidCompose
import com.build_logic.convention.utils.androidTestImplementation
import com.build_logic.convention.utils.library
import com.build_logic.convention.utils.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.plugin.compose")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.getByType<LibraryExtension>().apply {
            configureKotlinAndroidCompose(this)
            configureKotlinAndroid(this)
        }

        dependencies {
            testImplementation(library("junit"))
            testImplementation(library("mockito.core"))
            testImplementation(library("mockito.kotlin"))
            testImplementation(library("kotlinx.coroutines.test"))
            androidTestImplementation(library("androidx.junit"))
            androidTestImplementation(library("androidx.ui.test.junit4"))
        }
    }
}