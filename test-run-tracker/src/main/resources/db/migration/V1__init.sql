CREATE TABLE suite (
                       id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name        TEXT        NOT NULL UNIQUE,
                       owner       TEXT        NOT NULL,
                       created_at  TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE test_run (
                          id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          suite_id    BIGINT      NOT NULL REFERENCES suite (id) ON DELETE CASCADE,
                          status      TEXT        NOT NULL DEFAULT 'QUEUED'
                              CHECK (status IN ('QUEUED', 'RUNNING', 'PASSED', 'FAILED')),
                          started_at  TIMESTAMPTZ,
                          finished_at TIMESTAMPTZ,
                          version     BIGINT      NOT NULL DEFAULT 0
);

CREATE TABLE test_result (
                             id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             run_id      BIGINT      NOT NULL REFERENCES test_run (id) ON DELETE CASCADE,
                             test_name   TEXT        NOT NULL,
                             outcome     TEXT        NOT NULL CHECK (outcome IN ('PASSED', 'FAILED', 'SKIPPED')),
                             duration_ms INTEGER     NOT NULL CHECK (duration_ms >= 0)
);