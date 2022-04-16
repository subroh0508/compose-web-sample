plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    `node-conventions`
}

group = "net.subroh0508"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser {
            webpackTask {
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        named("jsMain") {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation(Libraries.decompose)
            }
        }
        named("jsTest") {
            dependencies {

            }
        }
    }
}