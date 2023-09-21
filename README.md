# MiniGameAPI

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)
[![Java CI with Maven](https://github.com/TehSteel/MiniGameAPI/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/TehSteel/MiniGameAPI/actions/workflows/maven.yml)

> MiniGameAPI Java library designed to simplify the development of mini-games within Minecraft plugins.


## Table of Contents

- [Getting Started](#getting-started)
- [Project Status](#project-status)
- [License](#license)

## Getting Started
You may need to compile it from the source before using it.

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

        <dependency>
            <groupId>com.github.tehsteel</groupId>
            <artifactId>minigameapi</artifactId>
            <!-- Make sure it's up-to-date -->
            <version>VERSION</version>
            <scope>compile</scope>
        </dependency>
```

### Initialize the Library

You must perform an initialization step to start using the Mini Game Library in your Java project. This step involves setting up essential configurations or providing necessary information for the library to function correctly.

```java
MiniGameLib.setPlugin(this);
```

## Project Status
<b>This project is currently under development and may not be stable. Use at your own risk.</b>

## License
This project is licensed under the [MIT License](LICENSE) - see the [LICENSE](LICENSE) file for details.
