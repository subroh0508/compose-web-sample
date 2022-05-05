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
                sourceMaps = false
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

                implementation(npm("@material/button", "^14.0.0"))
                implementation(npm("@material/card", "^14.0.0"))
                implementation(npm("@material/ripple", "^14.0.0"))
                implementation(npm("@material/theme", "^14.0.0"))
                implementation(npm("@material/typography", "^14.0.0"))

                implementation(devNpm("sass", "^1.51.0"))
                implementation(devNpm("sass-loader", "^12.6.0"))
                implementation(devNpm("extract-loader", "^5.1.0"))
                implementation(devNpm("file-loader", "^6.2.0"))
                implementation(devNpm("autoprefixer", "^10.4.7"))
                implementation(devNpm("postcss-loader", "^6.2.1"))
            }
        }
        named("jsTest") {
            dependencies {

            }
        }
    }
}