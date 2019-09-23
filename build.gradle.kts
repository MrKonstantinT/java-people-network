
plugins {
    java
}

repositories {
    jcenter()
}

dependencies {
    compile("com.google.guava:guava:28.1-jre")
    compile("org.apache.commons:commons-csv:1.6")
    testCompile("junit:junit:4.12")
}

tasks.test {
    useJUnit()
    testLogging {
        events("passed", "failed", "skipped", "standard_error", "standard_out")
    }
}
