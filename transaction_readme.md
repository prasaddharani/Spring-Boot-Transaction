| Propagation Type       | Description                                                                                                                            | Common Use Case                             |
| ---------------------- | -------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------- |
| **REQUIRED (default)** | Joins the existing transaction if present; else creates a new one.                                                                     | Most common default.                        |
| **REQUIRES_NEW**       | Always starts a new transaction. Suspends any existing one.                                                                            | Logging, audit, or independent actions.     |
| **SUPPORTS**           | Joins if one exists; else executes non-transactionally.                                                                                | Optional transactional logic.               |
| **NOT_SUPPORTED**      | Suspends existing transaction and runs non-transactionally.                                                                            | Performance-heavy read-only operations.     |
| **NEVER**              | Must execute non-transactionally; throws exception if there’s an active transaction.                                                   | Enforcing non-transactional execution.      |
| **MANDATORY**          | Must execute inside an existing transaction; throws exception if none exists.                                                          | Ensuring caller provides transaction.       |
| **NESTED**             | Starts a nested transaction (savepoint-based) within an existing one. Rolls back to savepoint without affecting the outer transaction. | Partial rollback within large transactions. |


| Isolation            | Database Behavior                                         | Prevents                      |
| -------------------- | --------------------------------------------------------- | ----------------------------- |
| **DEFAULT**          | Uses database default (usually `READ_COMMITTED`).         | —                             |
| **READ_UNCOMMITTED** | Can read uncommitted (dirty) data.                        | None.                         |
| **READ_COMMITTED**   | Cannot read uncommitted data (no dirty reads).            | Dirty reads.                  |
| **REPEATABLE_READ**  | Ensures same row read gives same data within transaction. | Dirty + non-repeatable reads. |
| **SERIALIZABLE**     | Highest isolation — fully sequential execution.           | All read anomalies.           |
