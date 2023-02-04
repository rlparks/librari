# librari

A simple virtual library that helps you read your e-books,
track your reading progress, and compile information about your collection.

## Build Instructions

### Requirements

* Java 11+
* Visual Studio Code
* Extension Pack for Java v0.25.7

### How to Build

1. Make sure that both the `JAVA_HOME` environment variable and `maven.executable.path` VS Code
setting are set to point to your versions of Java and Maven, respectively.

Example:
```
JAVA_HOME=C:\Program Files\Java\jdk-19

maven.executable.path=C:\Users\<username>\.m2\wrapper\dists\apache-maven-3.6.3-bin\1iopthnavndlasol9gbrbg6bf2\apache-maven-3.6.3\bin\mvn
```

2. Clone this repository using the below command and open the project in VS Code.

```
$ git clone https://github.com/rlparks/librari.git
```

3. In the bottom left part of VS Code, expand the **Maven** menu.
4. Expand the following options: **librari > Plugins > javafx**.
5. Press the play button next to the **run** subcommand.

## Project Notes

### Scope/Goals

* ✅ Responsive and easy-to-use UI
* ✅ Built-in e-book reader
* ✅ Progress tracker
* ✅ Easily acquire Title, Author, Year, Page No., etc. about books
* ✅ Limit number of API calls to once per program run

### Limitations

* Inconvenient to find and parse .epub files, so we are using .txt mock-ups
* Open Library API does not have covers for all titles in database
* Time consuming to style UI according to our mock-up

### Mockups

**TODO: Add Mockup Images When Finished**
