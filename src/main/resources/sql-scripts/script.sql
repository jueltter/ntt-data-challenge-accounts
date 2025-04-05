drop table if exists cuenta;

CREATE TABLE cuenta (
id BIGSERIAL PRIMARY KEY,
numero_cuenta VARCHAR(255) NOT NULL,
tipo_cuenta VARCHAR(255) NOT NULL,
saldo_inicial NUMERIC(19, 2) NOT NULL,
estado VARCHAR(255) NOT NULL
);

INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado) VALUES
('1234567890', 'savings', 1000.00, 'active'),
('2345678901', 'checking', 1500.50, 'active'),
('3456789012', 'savings', 2000.75, 'inactive'),
('4567890123', 'checking', 2500.00, 'active'),
('5678901234', 'savings', 3000.25, 'inactive'),
('6789012345', 'checking', 3500.50, 'active'),
('7890123456', 'savings', 4000.75, 'inactive'),
('8901234567', 'checking', 4500.00, 'active'),
('9012345678', 'savings', 5000.25, 'inactive'),
('0123456789', 'checking', 5500.50, 'active');

select * from cuenta;