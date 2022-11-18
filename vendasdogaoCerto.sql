create database vendas_dogao;

use vendas_dogao;

create table cliente (
codigo_cliente INT NOT NULL AUTO_INCREMENT,
nome VARCHAR (45) NOT NULL,
endereco varchar (45) NOT NULL,
telefone varchar (45) NOT NULL,
PRIMARY KEY (codigo_cliente));

create table produtos (
codigo_produto int not null primary key AUTO_INCREMENT,
descricao_produto varchar (70)  not null,
valor_compra decimal (10,2) not null,
valor_venda decimal (10,2) not null,
quantidade_estoque int not null,
estoque_minimo int not null);

create table vendas (
numero_venda int not null primary key AUTO_INCREMENT,
data_venda date not null,
data_vencimento date not null,
tipo_de_venda varchar(20) not null,
codigo_cliente int not null); 

ALTER TABLE produtos AUTO_INCREMENT=1;


ALTER TABLE vendas ADD FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo_cliente);

create table itens_de_venda (
quantidade_vendida int not null,
preco_pago decimal(10,2) not null,
numero_venda int not null,
codigo_produto int not null);

ALTER TABLE itens_de_venda ADD FOREIGN KEY (numero_venda) REFERENCES vendas(numero_venda);
ALTER TABLE itens_de_venda ADD FOREIGN KEY (codigo_produto) REFERENCES produtos(codigo_produto);
  
insert into produtos (descricao_produto, valor_compra, valor_venda, quantidade_estoque, estoque_minimo) values 
( 'dogao completo',  2.10, 6.10, 50, 10 ); 
 


DELIMITER $$
CREATE PROCEDURE valida_preco(IN valor_compra DECIMAL(10,2), valor_venda DECIMAL(10,2), OUT valido BOOLEAN)
BEGIN
	IF valor_venda < valor_compra THEN
		SET valido = FALSE;
	ELSE
		SET valido = TRUE;
	END IF;
END $$
DELIMITER ;
/*OK*/

DELIMITER $$
CREATE PROCEDURE valida_vista(IN data_venda date, data_vencimento date, codigo_cliente int, OUT valido BOOLEAN)
BEGIN
	IF data_venda = data_vencimento  AND codigo_cliente = -1 THEN
		SET valido = TRUE;
	ELSE
		SET valido = FALSE;
	END IF;
END $$
DELIMITER ;
/*OK*/

DELIMITER $$
CREATE PROCEDURE valida_prazo(IN data_venda date, data_vencimento date, OUT valido BOOLEAN)
BEGIN
	IF data_venda < data_vencimento   THEN
		SET valido = TRUE;
	ELSE
		SET valido = FALSE;
	END IF;
END $$
DELIMITER ;
/*OK*/

DELIMITER $$
CREATE PROCEDURE valida_estoque(IN quantidade_estoque int, estoque_minimo int, OUT valido BOOLEAN)
BEGIN
	IF quantidade_estoque < estoque_minimo THEN
		SET valido = FALSE;
	ELSE
		SET valido = TRUE;
	END IF;
END $$
DELIMITER ;
/*OK*/

DELIMITER $$
CREATE PROCEDURE valida_venda(IN quantidade_vendida int, OUT valido BOOLEAN)
BEGIN
	IF quantidade_vendida < 1 THEN
		SET valido = FALSE;
	ELSE
		SET valido = TRUE;
	END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE retorna_preco_venda(IN codigo_produto int, OUT preco_venda decimal(10,2))
BEGIN
	select valor_venda as vv into preco_venda from produtos where produtos.codigo_produto = codigo_produto;
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE valida_preco_pago(IN preco_pago decimal(10,2), quantidade_vendida int, preco_venda decimal(10,2),  OUT valido BOOLEAN)
BEGIN
	IF preco_pago = quantidade_vendida * preco_venda THEN
		SET valido = TRUE;
	ELSE
		SET valido = FALSE;
	END IF;
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE retira_estoque(IN codigo_produto int, quantidade_vendida int)
BEGIN
	declare sla int;
    declare quantidade int;
    select produtos.quantidade_estoque into quantidade from produtos where produtos.codigo_produto = codigo_produto;
    set sla = quantidade - quantidade_vendida;
	update produtos set produtos.quantidade_estoque = sla where produtos.codigo_produto = codigo_produto;
END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER verifica_produto BEFORE INSERT ON produtos
FOR EACH ROW
BEGIN
DECLARE valido2 BOOLEAN;
call valida_estoque(NEW.quantidade_estoque, NEW.estoque_minimo, valido2);
if valido2 = FALSE THEN
SIGNAL SQLSTATE '45000' SET message_text = 'DEU RUIM5.';
END IF;
END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER verifica_produto2 BEFORE UPDATE ON produtos
FOR EACH ROW
BEGIN
DECLARE valido2 BOOLEAN;
call valida_estoque(NEW.quantidade_estoque, NEW.estoque_minimo, valido2);
if valido2 = FALSE THEN
SIGNAL SQLSTATE '45000' SET message_text = 'DEU RUIM4.';
END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER verifica_tipo_venda BEFORE INSERT ON vendas
FOR EACH ROW
BEGIN
DECLARE valido1 BOOLEAN;
if new.tipo_de_venda ='vista' THEN
call valida_vista(NEW.data_venda, NEW.data_vencimento, NEW.codigo_cliente, valido1);

else if NEW.tipo_de_venda ='prazo' THEN
call valida_prazo(NEW.data_venda, NEW.data_vencimento, valido1);
else
SIGNAL SQLSTATE '45000' SET message_text = 'DEU RUIM3.';
END IF;
END IF;
IF valido1 = FALSE THEN
SIGNAL SQLSTATE '45000' SET message_text = 'DEU RUIM2 DE NOVO.';
END IF;
END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER verifica_venda BEFORE INSERT ON itens_de_venda
FOR EACH ROW
BEGIN
DECLARE valido1 BOOLEAN;
DECLARE preco_venda DECIMAL;
call retorna_preco_venda (NEW.codigo_produto, preco_venda);
call valida_venda (NEW.quantidade_vendida, valido1);
if valido1= FALSE THEN
SIGNAL SQLSTATE '45000' SET message_text = 'DEU RUIM1.';
else 
call retira_estoque(NEW.codigo_produto,  NEW.quantidade_vendida);
END IF;
END $$
DELIMITER ;

insert into cliente values (-1, 'clienteNRegistrado', 'clienteNRegistrado', '11111111');
insert into vendas (data_venda, data_vencimento, tipo_de_venda, codigo_cliente) values ("1998-10-22", "1998-10-22", "vista", -1);
