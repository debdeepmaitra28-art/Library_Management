# AURELIA architecture

The repository-root static client is GitHub Pages-ready and has no build step. Its sample account, catalogue state, loans, returns, history and requests are stored in browser localStorage. This is a front-end demo, not server-backed authentication or shared data.

## Java API (JDK 17+)
Compile from backend/java:
`javac -d out src/aurelia/LibraryServer.java`
Run:
`java -cp out aurelia.LibraryServer`
Set PORT to change the default 8080 port.

| Method | Route | Purpose |
|---|---|---|
| GET | /api/health | Health check |
| GET | /api/books | Catalogue and availability |
| GET | /api/loans | Active loans |
| POST | /api/loans | Borrow using form field bookId |
| DELETE | /api/loans/{id} | Return a loan |
| GET / POST | /api/requests | List or submit requests |

## Kotlin client
With the API running, compile from clients/kotlin:
`kotlinc src/main/kotlin/AureliaCli.kt -include-runtime -d aurelia-cli.jar`
`java -jar aurelia-cli.jar`
Set AURELIA_API to override the API root. The CLI is a separate client of the Java service.

## Boundaries and production work
The Java service uses in-memory state and resets at restart. The static browser demo and Java API do not share state. There is no persistent database, institutional login, server-side student identity, authorization model, or production-grade security. Before real use, add database migrations, SSO, role-based authorization, audit logging, backups, validation, tests, TLS and monitoring.
