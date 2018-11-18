DROP TABLE IF EXISTS DimPerfilPersonal;
CREATE TABLE IF NOT EXISTS DimPerfilPersonal (
id INT PRIMARY KEY ,
paisResidenciaActual VARCHAR (100) ,
estadoResidenciaActual VARCHAR (200) ,
ciudadResidenciaActual VARCHAR (200) ,
empresaActual VARCHAR (200) ,
cargoActual VARCHAR (200) ,
donante VARCHAR (1) ,
profesor VARCHAR (1) ,
fechaActualizacion DATE ,
fechaExpiracion DATE 

);
