DROP TABLE IF EXISTS cuenta cascade;

CREATE TABLE cuenta (
id BIGSERIAL PRIMARY KEY,
numero_cuenta VARCHAR(255) NOT NULL,
tipo_cuenta VARCHAR(255) NOT NULL,
saldo_inicial NUMERIC(19, 2) NOT NULL,
estado VARCHAR(255) NOT NULL,
 cliente_id VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS movimiento_cuenta cascade;

CREATE TABLE movimiento_cuenta (
id BIGSERIAL PRIMARY KEY,
fecha TIMESTAMP NOT NULL,
 tipo_movimiento VARCHAR(255) NOT NULL,
 valor NUMERIC(19, 2) NOT NULL,
  saldo NUMERIC(19, 2) NOT NULL,
 saldo_anterior NUMERIC(19, 2) NOT NULL,
  cuenta BIGINT NOT NULL,
FOREIGN KEY (cuenta) REFERENCES cuenta(id)
);

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
 ('1234567890', 'AHORROS', 1000.00, 'TRUE', 'C001234567'),
 ('2345678901', 'CORRIENTE', 1500.50, 'TRUE', 'C002345678'),
 ('3456789012', 'AHORROS', 2000.75, 'TRUE', 'C003456789'),
  ('4567890123', 'CORRIENTE', 2500.00, 'TRUE', 'C004567890'),
 ('5678901234', 'AHORROS', 3000.25, 'TRUE', 'C005678901'),
 ('6789012345', 'CORRIENTE', 3500.50, 'TRUE', 'C006789012'),
 ('7890123456', 'AHORROS', 4000.75, 'TRUE', 'C007890123'),
  ('8901234567', 'CORRIENTE', 4500.00, 'TRUE', 'C008901234'),
   ('9012345678', 'AHORROS', 5000.25, 'TRUE', 'C009012345'),
('0123456789', 'CORRIENTE', 5500.50, 'TRUE', 'C010123456');

SELECT * FROM cuenta;