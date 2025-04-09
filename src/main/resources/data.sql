INSERT INTO conta (id, nome, tipo) VALUES (1, 'Conta Corrente', 'Corrente');
INSERT INTO conta (id, nome, tipo) VALUES (2, 'Cartão de Crédito', 'Cartão');
INSERT INTO conta (id, nome, tipo) VALUES (3, 'Investimentos', 'Investimento');

INSERT INTO transacao (descricao, valor, data, categoria, conta_id) VALUES 
('Salário', 5000.00, '2025-03-01', 'Receita', 1),
('Aluguel', 1500.00, '2025-03-05', 'Moradia', 1),
('Supermercado', 600.00, '2025-03-10', 'Alimentação', 1),
('Academia', 100.00, '2025-03-12', 'Saúde', 1),
('Fatura Cartão', 1200.00, '2025-03-15', 'Cartão', 2),
('Dividendos', 300.00, '2025-03-18', 'Rendimento', 3);
