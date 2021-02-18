plugins {
    java
    kotlin("jvm") version "1.4.30"
}

group = "cn.awalol"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.fasterxml.jackson.core","jackson-core","2.12.1")
    implementation("com.fasterxml.jackson.core","jackson-annotations","2.12.1")
    implementation("com.fasterxml.jackson.core","jackson-databind","2.12.1")
    compile("org.apache.httpcomponents","httpclient","4.5.13")
//    compile("com.alibaba","fastjson","1.2.75")

    /*compileOnly("org.projectlombok:lombok","1.18.16")
    annotationProcessor("org.projectlombok:lombok","1.18.16")

    testCompileOnly("org.projectlombok","lombok","1.18.16")
    testAnnotationProcessor("org.projectlombok","lombok","1.18.16")*/
    testCompile("junit", "junit", "4.12")
}
