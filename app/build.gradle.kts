plugins {
    application
    id("org.gradlex.extra-java-module-info") version("1.11")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.liquibase:liquibase-core:4.31.1")
    components {
        withModule("org.liquibase:liquibase-core") {
            allVariants {
                withDependencies {
                    removeIf { it.group == "javax.xml.bind" }
                    add("jakarta.xml.bind:jakarta.xml.bind-api:2.3.3")
                }
            }
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainModule = "org.example.app"
    mainClass = "org.example.App"
}

extraJavaModuleInfo {
    failOnMissingModuleInfo = false
    failOnAutomaticModules = false
}