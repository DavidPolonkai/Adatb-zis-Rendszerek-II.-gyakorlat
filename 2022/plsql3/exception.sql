DECLARE
    a auto%rowtype;
    
BEGIN
    select * into a from auto where rsz='1';
    EXCEPTION
        WHEN NO_DATA_FOUND then
            dbms_output.put_line('Nincs ilyen');
END;


DECLARE
    e EXCEPTION;
    x number(2);
BEGIN
    if :x<0 then
        RAISE e;
    end if;
    
    dbms_output.put_line('Vége');
    EXCEPTION
        when e THEN 
            dbms_output.put_line('hiba');

END;

