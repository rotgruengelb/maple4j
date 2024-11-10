# maple4j
[![GH Actions Pipeline Status](https://github.com/rotgruengelb/maple4j-test/actions/workflows/1.pipeline.yml/badge.svg)](https://github.com/rotgruengelb/maple4j-test/actions/workflows/1.pipeline.yml)
[![Maven Central](https://img.shields.io/maven-central/v/net.rotgruengelb/maple4j.svg)](https://central.sonatype.com/artifact/net.rotgruengelb/maple4j)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://rotgruengelb.github.io/maple4j/javadoc/)

**A Java Parser for the Maple Data Language**, originally outlined by [EmmaTheMartian](https://github.com/EmmaTheMartian) in [EmmaTheMartian/maple](https://github.com/EmmaTheMartian/maple).

## Features 

- Clear exception reporting with a snippet of the input around the offending location.
- Simple, easy-to-use interface.

Uses [ANTLR](https://github.com/antlr/antlr4/) for parser and lexer generation.

## Usage

```java
String input = "message = 'Hello, World!'";
MapleMap map = Maple.parse(input);

System.out.println(map.getString("message"));
```

## Including maple4j

Maple4j is published to [Maven Central](https://central.sonatype.com/artifact/net.rotgruengelb/maple4j) and [rotgruengelb.net's Maven](https://maven.rotgruengelb.net).

**Add using Gradle:**
```gradle
repositories {
   mavenCentral()
}
dependencies {
   implementation("net.rotgruengelb:maple4j:${maple4j_version}")
}
```

## Exceptions
### Example
```java
Maple.parse("key = ");
```
this will result in an Exception like this:
```
MapleParseException: Unexpected token "<EOF>" at line 1, column 7.
Expected: {, [, a string, a number, or a boolean.

key = 
      ^
```

## Licence
Maple4j is licensed under the MIT License.

Parts of this project and structure were adapted from [thriving-dev/java-library-template](https://github.com/thriving-dev/java-library-template).
