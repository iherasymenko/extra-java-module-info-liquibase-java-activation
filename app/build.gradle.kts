plugins {
    application
    id("org.gradlex.extra-java-module-info") version("1.11")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hsqldb:hsqldb:2.7.4")
    implementation("org.liquibase:liquibase-core:4.31.1")
    implementation("com.mattbertolini:liquibase-slf4j:5.1.0")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    components {
        withModule("org.liquibase:liquibase-core") {
            allVariants {
                withDependencies {
                    removeIf { it.name in setOf("opencsv", "jaxb-api", "commons-collections4", "commons-text")}
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
    failOnAutomaticModules = true
    module("org.liquibase:liquibase-core", "liquibase.core") {
        closeModule()
        requiresTransitive("java.sql")
        requires("java.desktop")
        requires("java.logging")
        requires("java.naming")
        requires("java.xml")
        requires("org.apache.commons.io")
        requires("org.apache.commons.lang3")
        requires("org.yaml.snakeyaml")
        exports("liquibase")
        exports("liquibase.analytics")
        exports("liquibase.analytics.configuration")
        exports("liquibase.configuration")
        exports("liquibase.database.jvm")
        exports("liquibase.exception")
        exports("liquibase.logging")
        exports("liquibase.logging.core")
        exports("liquibase.resource")
        exports("liquibase.ui")

        opens("www.liquibase.org.xml.ns.dbchangelog")

        ignoreServiceProvider("liquibase.license.LicenseService")
        uses("liquibase.change.Change")
        uses("liquibase.changelog.ChangeLogHistoryService")
        uses("liquibase.changelog.visitor.ValidatingVisitorGenerator")
        uses("liquibase.changeset.ChangeSetService")
        uses("liquibase.command.CommandStep")
        uses("liquibase.command.LiquibaseCommand")
        uses("liquibase.configuration.AutoloadedConfigurations")
        uses("liquibase.configuration.ConfigurationValueProvider")
        uses("liquibase.configuration.ConfiguredValueModifier")
        uses("liquibase.database.Database")
        uses("liquibase.database.DatabaseConnection")
        uses("liquibase.database.LiquibaseTableNames")
        uses("liquibase.database.jvm.ConnectionPatterns")
        uses("liquibase.datatype.LiquibaseDataType")
        uses("liquibase.diff.DiffGenerator")
        uses("liquibase.diff.compare.DatabaseObjectComparator")
        uses("liquibase.diff.output.changelog.ChangeGenerator")
        uses("liquibase.executor.Executor")
        uses("liquibase.io.OutputFileHandler")
        uses("liquibase.lockservice.LockService")
        uses("liquibase.logging.LogService")
        uses("liquibase.logging.mdc.CustomMdcObject")
        uses("liquibase.logging.mdc.MdcManager")
        uses("liquibase.parser.ChangeLogParser")
        uses("liquibase.parser.LiquibaseSqlParser")
        uses("liquibase.parser.NamespaceDetails")
        uses("liquibase.parser.SnapshotParser")
        uses("liquibase.precondition.Precondition")
        uses("liquibase.report.ShowSummaryGenerator")
        uses("liquibase.resource.PathHandler")
        uses("liquibase.serializer.ChangeLogSerializer")
        uses("liquibase.serializer.SnapshotSerializer")
        uses("liquibase.servicelocator.ServiceLocator")
        uses("liquibase.snapshot.SnapshotGenerator")
        uses("liquibase.sqlgenerator.SqlGenerator")
        uses("liquibase.structure.DatabaseObject")
    }
}