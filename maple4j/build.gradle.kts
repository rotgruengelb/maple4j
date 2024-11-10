group = "net.rotgruengelb"

object META {
    const val DESC = "A Java Implementation/Parser for the Maple Data Language."
    const val LICENCE = "MIT License"
    const val LICENCE_URL = "https://opensource.org/license/MIT"
    const val GH_REPO = "rotgruengelb/maple4j"
    const val DEV_ID = "rotgruengelb"
    const val DEV_NAME = "Daniel (aka. rotgruengelb)"
    const val DEV_EMAIL = "rotgrungelblab@gmail.com"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    withSourcesJar()
    withJavadocJar()
}

plugins {
    `java-library`
    `maven-publish`
    signing
    antlr
    idea
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

sourceSets {
    create("intTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val intTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
    antlr(libs.antlr)
    implementation(libs.antlr.runtime)
    implementation(libs.annotations)
    testImplementation(libs.junit)
    intTestImplementation(libs.bundles.testcontainers.junit)
    intTestImplementation(libs.bundles.junit.assertj)
}

tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments.addAll(listOf("-visitor"))
    outputDirectory = file("src/main/java/net/rotgruengelb/maple/internal/generated/")
}

tasks.compileJava {
    dependsOn(tasks.generateGrammarSource)
}

tasks.clean {
    delete("src/main/java/net/rotgruengelb/maple/internal/generated/")
}

val intTest = tasks.register<Test>("intTest") {
    description = "Runs integration tests."
    group = "verification"
    testClassesDirs = sourceSets["intTest"].output.classesDirs
    classpath = sourceSets["intTest"].runtimeClasspath
    shouldRunAfter(tasks.named("test"))
    useJUnitPlatform()
    testLogging {
        events("passed")
    }
}

tasks.check {
    dependsOn(intTest)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<Jar>("sourcesJar") {
    dependsOn(tasks.generateGrammarSource)
    from(sourceSets.main.get().allJava)
    from(tasks.generateGrammarSource.get().outputDirectory)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from("LICENSE") {
        rename { "${it}_${project.name}" }
    }
}

signing {
    if (System.getenv("GPG_SIGNING_KEY") != null && System.getenv("GPG_SIGNING_PASSPHRASE") != null) {
        useInMemoryPgpKeys(System.getenv("GPG_SIGNING_KEY"), System.getenv("GPG_SIGNING_PASSPHRASE"))
        sign(extensions.getByType<PublishingExtension>().publications)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])
            pom {
                name.set(project.name)
                description.set(META.DESC)
                url.set("https://github.com/${META.GH_REPO}")
                licenses {
                    license {
                        name.set(META.LICENCE)
                        url.set(META.LICENCE_URL)
                    }
                }
                developers {
                    developer {
                        id.set(META.DEV_ID)
                        name.set(META.DEV_NAME)
                        email.set(META.DEV_EMAIL)
                    }
                }
                scm {
                    url.set("https://github.com/${META.GH_REPO}.git")
                    connection.set("scm:git:git://github.com/${META.GH_REPO}.git")
                    developerConnection.set("scm:git:git://github.com/${META.GH_REPO}.git")
                }
                issueManagement {
                    url.set("https://github.com/${META.GH_REPO}/issues")
                }
            }
        }
    }
    repositories {
        if (!project.version.toString().endsWith("SNAPSHOT")) {
            maven {
                name = "rotgruengelb_Maven_Releases_Repository"
                url = uri("https://maven.rotgruengelb.net/releases")
                credentials.password = System.getenv("RGGM_PASSWORD") ?: ""
                credentials.username = System.getenv("RGGM_USERNAME") ?: ""
            }
        }
        maven {
            name = "rotgruengelb_Maven_Snapshots_Repository"
            url = uri("https://maven.rotgruengelb.net/snapshots")
            credentials.password = System.getenv("RGGM_PASSWORD") ?: ""
            credentials.username = System.getenv("RGGM_USERNAME") ?: ""
        }
    }
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to project.name, "Implementation-Version" to project.version
        )
    }
}

dependencyLocking {
    lockAllConfigurations()
}

rootProject.tasks.named("dependencies") {
    dependsOn(tasks.named("dependencies"))
}
