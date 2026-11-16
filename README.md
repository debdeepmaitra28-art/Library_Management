# AURELIA — Library & Reading Management System

AURELIA is a student-first library experience with a responsive catalogue, book discovery, borrowing desk, due-date tracking, reading history, and book requests.

## Project structure

- `index.html`, `assets/css/styles.css`, `assets/js/app.js` — GitHub Pages-ready browser application.
- `backend/java/` — dependency-free Java HTTP API and in-memory library service.
- `clients/kotlin/` — Kotlin librarian CLI that consumes the Java API.
- `docs/` — architecture and run instructions.

## Run the browser application

Open `index.html` in a browser or publish the repository root with GitHub Pages. The browser demo stores its sample account and activity in localStorage. It is a front-end demonstration, not server-backed authentication or shared storage.

## Run the Java API

Requires JDK 17 or later.

```bash
cd backend/java
javac -d out src/aurelia/*.java
java -cp out aurelia.LibraryServer
```

The API listens on port 8080 by default. Set `PORT` to change it.

## Run the Kotlin client

Requires Kotlin/JVM 1.9+ and a running Java API.

```bash
cd clients/kotlin
kotlinc src/main/kotlin/AureliaCli.kt -include-runtime -d aurelia-cli.jar
java -jar aurelia-cli.jar
```

Set `AURELIA_API` if the API is not at `http://localhost:8080/api`.

## Scope and data boundaries

The browser demo uses localStorage and seeded catalogue data. The Java API currently uses in-memory data and resets when the process restarts. The Kotlin tool is an API client. Production deployment would require persistent storage, institutional identity integration, authorization hardening, backups, and operational hosting.

## Main workflows

- Browse, search, and filter a curated catalogue.
- Inspect book details and availability.
- Borrow available copies and return active loans.
- Track due dates and borrowing history.
- Submit and review book requests.
- Use the librarian view to inspect loans, catalogue inventory, and requests.
