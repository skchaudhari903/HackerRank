SET @P = 0;
SELECT REPEAT (" * ", @P := @P + 1) 
FROM information_schema.tables
where @P < 20;