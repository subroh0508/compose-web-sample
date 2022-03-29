plugins {
    kotlin("multiplatform")
    `node-conventions`
}

group = "net.subroh0508"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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
                implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
            }
        }
        named("jsTest") {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-js")
            }
        }
    }
}