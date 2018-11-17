DROP TABLE IF EXISTS DimEgresado;
CREATE TABLE IF NOT EXISTS DimEgresado (
id INT PRIMARY KEY ,
edadEgresado INT ,
generoEgresado VARCHAR (1) ,
celularPrincipalEgresado VARCHAR (1) ,
emailEgresado VARCHAR (1) ,
nivelVinculacionEgresado VARCHAR(10) ,
indiceRelacionamiento FLOAT(2) ,
idPerfilPractica INT ,
idPerfilPersonal INT ,
idPerfilGrado INT 

);
