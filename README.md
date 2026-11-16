# AURELIA — Library & Reading Management System

A student-first library interface with a curated catalogue, search and category filters, book details, borrowing and returns, due-date tracking, reading history, book requests and a librarian overview.

## Project structure
- `index.html`, `assets/css/styles.css`, `assets/js/app.js`: static browser application.
- `backend/java/src/aurelia/LibraryServer.java`: dependency-free Java 17 REST API.
- `clients/kotlin/src/main/kotlin/AureliaCli.kt`: Kotlin/JVM API client.
- `docs/ARCHITECTURE.md`: routes, boundaries and production notes.

## Browser demo
Open `index.html` or publish the repository root with GitHub Pages. The browser app stores demo activity in localStorage. It is not server-backed authentication or shared storage.

## Java API
Requires JDK 17+.
```sh
cd backend/java
javac -d out src/aurelia/LibraryServer.java
java -cp out aurelia.LibraryServer
```
The API listens on port 8080 by default; set `PORT` to change it.

## Kotlin client
Requires Kotlin/JVM. Start the Java API first.
```sh
cd clients/kotlin
kotlinc src/main/kotlin/AureliaCli.kt -include-runtime -d aurelia-cli.jar
java -jar aurelia-cli.jar
```
Set `AURELIA_API` to change the API root.

## Data boundaries
The browser demo uses localStorage and seeded data. The Java service uses in-memory data and resets when the process restarts. The browser and API do not share state. There is no persistent database, institutional login, or production authorization. Add those before handling real library records.
