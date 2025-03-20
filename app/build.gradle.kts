plugins {
    application
    id("org.gradlex.extra-java-module-info") version("1.11")
}

repositories {
    mavenCentral()
}

dependencies {
    /*
     * This dependency causes problems with task app:moduleDescriptorRecommendations.
     */
    implementation("org.liquibase:liquibase-core:4.31.1")

    /*
     * Enable this dependency and the java.activation module descriptor below because without those task
     * app:moduleDescriptorRecommendations fails with "Module java.activation not found, required by java.xml.bind".
     */
//    implementation("com.sun.activation:javax.activation:1.2.0")
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

    /*
     * Enable this module descriptor and the com.sun.activation:javax.activation dependency above because without those
     * task app:moduleDescriptorRecommendations fails with "Module java.activation not found, required by java.xml.bind".
     */
//    module("com.sun.activation:javax.activation", "java.activation") {
//        closeModule()
//        requiresTransitive("java.datatransfer")
//        requiresTransitive("java.desktop")
//        requires("java.logging")
//        exports("com.sun.activation.registries")
//        exports("com.sun.activation.viewers")
//    }
}