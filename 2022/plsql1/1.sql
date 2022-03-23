    DECLARE
        r number(2):=12;
        pi CONSTANT number(3,2):=3.14;
    BEGIN
        dbms_output.put_line(r || ' sugarú kör területe:' || r*r*pi);
    END;
    
    --SET SERVEROUTPUT ON
    
    
    