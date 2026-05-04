-- Dati di esempio (H2 li carica all'avvio)
-- Rinomina i valori in base al tuo dominio

INSERT INTO managers (name, email, phone, created_at)
VALUES
  ('Mario Rossi', 'mario.rossi@example.com', '333-0001', CURRENT_TIMESTAMP),
  ('Luisa Bianchi', 'luisa.bianchi@example.com', '333-0002', CURRENT_TIMESTAMP);

INSERT INTO members (name, email, manager_id, created_at)
VALUES
  ('Anna Verdi', 'anna@example.com', 1, CURRENT_TIMESTAMP),
  ('Paolo Neri', 'paolo@example.com', 1, CURRENT_TIMESTAMP),
  ('Sara Blu', 'sara@example.com', 2, CURRENT_TIMESTAMP);

INSERT INTO entries (date, status, note, member_id)
VALUES
  (CURRENT_DATE, 'CONFIRMED', 'Nota esempio', 1),
  (CURRENT_DATE, 'PENDING', NULL, 2),
  (CURRENT_DATE, 'CANCELLED', 'Cancellato', 3);
