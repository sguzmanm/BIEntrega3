DROP TABLE IF EXISTS DimFechaFin;
CREATE TABLE IF NOT EXISTS DimFechaFin (
id INT PRIMARY KEY ,
anhoEvento INT ,
semestreEvento VARCHAR(2) ,
cuatrimestreEvento VARCHAR(2) ,
mesEvento INT ,
diaEvento INT 

);
