DROP TABLE IF EXISTS DimPerfilGrado;
CREATE TABLE IF NOT EXISTS DimPerfilGrado (
id INT PRIMARY KEY ,
beneficiarioQE VARCHAR (1) ,
gradoEgresado VARCHAR (200) ,
nivelGrado VARCHAR (20) ,
semestreGrado VARCHAR (100) 

);
