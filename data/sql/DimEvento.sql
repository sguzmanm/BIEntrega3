DROP TABLE IF EXISTS DimEvento;
CREATE TABLE IF NOT EXISTS DimEvento (
id INT PRIMARY KEY ,
nombreEvento VARCHAR (200) ,
categoriaEvento VARCHAR (200) ,
tipoEvento VARCHAR(5) ,
codigoEvento VARCHAR (100) 

);
