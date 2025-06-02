CREATE DATABASE IF NOT EXISTS db_produtos;
USE db_produtos;

CREATE TABLE IF NOT EXISTS tb_categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tamanho VARCHAR(50),
    categoria VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS tb_produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    unidade VARCHAR(20),
    quantidade INT NOT NULL,
    quantidade_minima INT,
    quantidade_maxima INT,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES tb_categorias(id)
);

INSERT INTO tb_categorias (nome, tamanho, categoria) VALUES 
('Bebidas', 'pequeno', 'Alimentos'),
('Laticínios', 'pequeno', 'Alimentos'),
('Limpeza', 'medio', 'Higiene'),
('Hortifruti', 'grande', 'Alimentos'),
('Petiscos', 'pequeno', 'Alimentos');

INSERT INTO tb_produtos (nome, preco, unidade, quantidade, quantidade_minima, quantidade_maxima, id_categoria) VALUES
('Refrigerante Cola', 5.99, 'unidade', 100, 20, 200, 1),
('Leite Integral', 4.50, 'caixa', 50, 10, 100, 2),
('Sabão em Pó', 12.90, 'pacote', 80, 15, 150, 3),
('Banana Prata', 3.20, 'kg', 30, 10, 60, 4),
('Batata Chips', 6.75, 'pacote', 40, 5, 80, 5);
