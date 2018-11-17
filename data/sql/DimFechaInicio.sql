DROP TABLE IF EXISTS DimFechaInicio;
CREATE TABLE IF NOT EXISTS DimFechaInicio (
id INT PRIMARY KEY ,
anhoEvento INT ,
semestreEvento VARCHAR(2) ,
cuatrimestreEvento VARCHAR(2) ,
mesEvento INT ,
diaEvento INT 

);
