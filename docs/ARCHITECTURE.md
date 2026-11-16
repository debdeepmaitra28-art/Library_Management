# PageNest architecture

The repository-root static client is GitHub Pages-ready and has no build step. Its sample account, catalogue state, loans, returns, history and requests are stored in browser localStorage. This is a front-end demo, not server-backed authentication or shared data.

## Java API (JDK 17+)
Compile from backend/java:
`javac -d out src/aurelia/LibraryServer.java`
Run:
`java -cp out pagenest.LibraryServer`
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
`kotlinc src/main/kotlin/AureliaCli.kt -include-runtime -d pagenest-cli.jar`
`java -jar pagenest-cli.jar`
Set PAGENEST_API to override the API root. PAGENEST_API remains supported as a compatibility fallback. The CLI is a separate client of the Java service.

## Boundaries and production work
The Java service uses in-memory state and resets at restart. The static browser demo and Java API do not share state. There is no persistent database, institutional login, server-side student identity, authorization model, audit logging, backups, validation, tests, TLS or monitoring. Before real use, add those production safeguards.
