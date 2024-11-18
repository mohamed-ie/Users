import com.build_logic.convention.utils.implementation
import com.build_logic.convention.utils.ksp
import com.build_logic.convention.utils.library
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.google.devtools.ksp")
        }

        extensions.configure<KspExtension> {
            arg("KOIN_DEFAULT_MODULE", "false")
            //Temporary fix for https://github.com/InsertKoinIO/koin/issues/1989
            arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
        }

        dependencies {
            implementation(platform(library("koin.bom")))
            implementation(library("koin.annotations"))
            implementation(library("koin.core"))
            ksp(library("koin.ksp.compiler"))
        }
    }
}