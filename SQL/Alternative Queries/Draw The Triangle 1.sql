SET @P = 21;
SELECT REPEAT (" * ", @P := @P - 1) 
FROM information_schema.tables;