# MiniGameAPI
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)
[![Java CI with Maven](https://github.com/TehSteel/MiniGameAPI/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/TehSteel/MiniGameAPI/actions/workflows/maven.yml)

> MiniGameAPI Java library designed to simplify the development of mini-games within Minecraft plugins.


## Table of Contents
- [Getting Started](#getting-started)
- [Projects & Examples](#projects)
- [License](#license)

## Getting Started
You may need to compile it from the source before using it.
```xml
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>

    <dependency>
        <groupId>dev.tehsteel</groupId>
        <artifactId>minigameapi</artifactId>
        <!-- Make sure it's up-to-date -->
        <version>VERSION</version>
        <scope>compile</scope>
    </dependency>
```

### Initialize the Library

You must initialize the lib before start using the MiniGameLib in your project.
```java
MiniGameLib.setPlugin(this);
```

## Projects
- [SpleefGame](https://github.com/TehSteel/SpleefGame)

## License
This project is licensed under the [MIT License](LICENSE) - see the [LICENSE](LICENSE) file for details.
