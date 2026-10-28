import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

if (!file(".git").exists()) {
    // Leaf start - project setup
    val errorText = """
        
        =====================[ ERROR ]=====================
         The MafiaLeaf project directory is not a properly
         cloned Git repository.
         
         In order to build MafiaLeaf from source you must
         clone the repository using Git, not download a
         code zip.
         
         See https://github.com/PaperMC/Paper/blob/main/CONTRIBUTING.md
         for further information on building Paper forks.
        ===================================================
    """.trimIndent()
    // Leaf end - project setup
    error(errorText)
}

rootProject.name = "mafialeaf"

for (name in listOf("mafialeaf-api", "mafialeaf-server")) {
    val projName = name.lowercase(Locale.ENGLISH)
    include(projName)
}
