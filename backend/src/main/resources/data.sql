-- Seed idempotente: inserisce solo se la tabella e' vuota.
-- I dati modificati via API tra un restart e l'altro restano intatti.

INSERT INTO managers (name, email, phone, created_at)
SELECT * FROM (VALUES
  ('Mario Rossi', 'mario.rossi@example.com', '333-0001', CURRENT_TIMESTAMP),
  ('Luisa Bianchi', 'luisa.bianchi@example.com', '333-0002', CURRENT_TIMESTAMP)
) AS v(name, email, phone, created_at)
WHERE NOT EXISTS (SELECT 1 FROM managers);

INSERT INTO members (name, email, manager_id, created_at)
SELECT * FROM (VALUES
  ('Anna Verdi', 'anna@example.com', 1, CURRENT_TIMESTAMP),
  ('Paolo Neri', 'paolo@example.com', 1, CURRENT_TIMESTAMP),
  ('Sara Blu', 'sara@example.com', 2, CURRENT_TIMESTAMP)
) AS v(name, email, manager_id, created_at)
WHERE NOT EXISTS (SELECT 1 FROM members);

INSERT INTO entries (date, status, note, member_id)
SELECT * FROM (VALUES
  (CURRENT_DATE, 'CONFIRMED', 'Nota esempio', 1),
  (CURRENT_DATE, 'PENDING', NULL, 2),
  (CURRENT_DATE, 'CANCELLED', 'Cancellato', 3)
) AS v(date, status, note, member_id)
WHERE NOT EXISTS (SELECT 1 FROM entries);
